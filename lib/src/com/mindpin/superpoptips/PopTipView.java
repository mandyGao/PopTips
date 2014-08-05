package com.mindpin.superpoptips;

import java.util.ArrayList;
import java.util.Collection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

@SuppressLint({ "NewApi", "ViewConstructor" })
public class PopTipView extends LinearLayout implements
		ViewTreeObserver.OnPreDrawListener, View.OnClickListener {

	public static final String TRANSLATION_Y_COMPAT = "translationY";
	public static final String TRANSLATION_X_COMPAT = "translationX";
	public static final String SCALE_X_COMPAT = "scaleX";
	public static final String SCALE_Y_COMPAT = "scaleY";
	public static final String ALPHA_COMPAT = "alpha";

	private ArrowView mArrowView;
	private ViewGroup mContentHolder;
	private TextView mTextContent;
	private View clickableView;

	private int mRelativeMasterViewY;

	private int mRelativeMasterViewX;

	private OnPopTipViewClickedListener mListener;
	private int bodyWidth;
	private int bodyHeight;
	private ArrowLocation arrowLocation;
	private int arrowWidth;
	private int offsetX;
	private int offsetY;
	private ObjectAnimator anim = null;

	public PopTipView(final Context context, View clickableView) {
		super(context);
		this.clickableView = clickableView;
		init();
	}

	private void init() {
		setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		setOrientation(VERTICAL);
		LayoutInflater.from(getContext()).inflate(R.layout.poptip, this, true);
		mArrowView = (ArrowView) findViewById(R.id.arrowView);
		mContentHolder = (ViewGroup) findViewById(R.id.poptip_contentholder);
		mTextContent = (TextView) findViewById(R.id.poptip_contenttv);
		setOnClickListener(this);
		getViewTreeObserver().addOnPreDrawListener(this);
	}

	// 三角箭头相对于方框的位置，一共十二种
	enum ArrowLocation {
		left_top, // 三角箭头位于方框的左侧上方
		left_bottom, // 三角箭头位于方框的左侧下方
		right_top, // 三角箭头位于方框的右侧上方
		right_bottom, // 三角箭头位于方框的右侧下方
		top_left, // 三角箭头位于方框的上侧左方
		top_right, // 三角箭头位于方框的上侧右方
		bottom_left, // 三角箭头位于方框的下侧左方
		bottom_right, // 三角箭头位于方框的下侧右方
		top, // 三角箭头位于方框的上侧居中
		bottom, // 三角箭头位于方框的下侧居中
		left, // 三角箭头位于方框的左侧居中
		right; // 三角箭头位于方框的右侧居中
	}

	// 设置基准点相对于 clickableView 左上角的偏移量
	// 以此来计算基准点的位置
	// 基准点即浮动框显示时，三角形箭头尖端所处的位置
	// 偏移量的单位是 dp
	public void set_arrow_point_offset(int offset_x, int offset_y) {
		offsetX = DensityUtil.dip2px(getContext(), offsetX);
		offsetY = DensityUtil.dip2px(getContext(), offsetY);
		this.offsetX = offset_x;
		this.offsetY = offset_y;

	}

	// 三角箭头相对于方框的位置
	// 从 arrowLocation 的十二种之中选一种
	public void set_arrow_location(ArrowLocation arrowLocation) {

		this.arrowLocation = arrowLocation;

	};

	// 当确定了基准点的位置，和三角形箭头相对于方框的位置时
	// 也就同时确定了三角形箭头和方框的位置
	// 举例来说，当 arrowLocation 指定为 left_top 时
	// 三角箭头位于方框的左侧上方，而三角形箭头的尖端位于基准点
	// 那么三角形和方框应该位于基准点的右侧偏下

	// 设置方框矩形宽度（单位为dp）
	public void set_width(int width) {
		width = DensityUtil.dip2px(getContext(), width);
		this.bodyWidth = width;
	}

	// 设置方框矩形高度（单位为dp）
	public void set_height(int height) {
		height = DensityUtil.dip2px(getContext(), height);
		this.bodyHeight = height;
	}

	// 设置三角形箭头边的长度（单位为dp）
	// 当 arrowLocation 是 top, bottom, left, right 时，该值为直角等腰三角形最长边的高
	// 当 arrowLocation 是其他的八个斜角值时，该值为直角三角形的直角边长
	public void set_arrow_width(int arrowWidth) {
		arrowWidth = DensityUtil.dip2px(getContext(), arrowWidth);
		this.arrowWidth = arrowWidth;
	}

	@Override
	public boolean onPreDraw() {
		Log.v("mandy", "on pre draw");
		getViewTreeObserver().removeOnPreDrawListener(this);

		applyToolTipPosition();
		return true;
	}

	// 以 int 表示颜色
	public void set_backgroud(final int bg_color) {
		mArrowView.setColor(bg_color);
		mContentHolder.setBackgroundColor(bg_color);
	}

	/**
	 * 以十六进制的字符串来表示颜色
	 * 
	 * @param bg_color
	 */
	public void set_backgroud(String bg_color) {
		set_backgroud(Color.parseColor(bg_color));
	}

	/**
	 * 矩形框内默认的text
	 */
	public void set_pop_text(String text) {
		mTextContent.setText(text);
	}

	@SuppressLint("NewApi")
	private void applyToolTipPosition() {

		Log.v("mandy", "applyToolTipPosition");

		final int[] masterViewScreenPosition = new int[2];
		clickableView.getLocationOnScreen(masterViewScreenPosition);
		//
		// final Rect viewDisplayFrame = new Rect();
		// mView.getWindowVisibleDisplayFrame(viewDisplayFrame);
		//
		final int[] parentViewScreenPosition = new int[2];
		((View) getParent()).getLocationOnScreen(parentViewScreenPosition);

		final int masterViewWidth = clickableView.getWidth();
		final int masterViewHeight = clickableView.getHeight();

		mRelativeMasterViewX = masterViewScreenPosition[0]
				- parentViewScreenPosition[0];
		mRelativeMasterViewY = masterViewScreenPosition[1]
				- parentViewScreenPosition[1];

		final int relativeMasterViewCenterX = mRelativeMasterViewX
				+ masterViewWidth / 2;

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				bodyWidth, bodyHeight);
		RelativeLayout.LayoutParams arrowlayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		int longSide = 0; // 长的直角边
		int shortSide = 0; // 短的直角边
		int bevelSide = 0; // 斜边
		int bottomSide = 0;// 底边
		int defaultX = 0;
		int defaultY = 0;
		String translation = null;

		switch (arrowLocation) {
		case top_left:
			longSide = arrowWidth;
			shortSide = arrowWidth;
			drawTriangleArrow(0, 0, 0, longSide, shortSide, longSide);

			layoutParams.setMargins(0, longSide, 0, 0);
			arrowlayoutParams.height = arrowWidth;
			arrowlayoutParams.width = mArrowView.getStartX() + shortSide;
			// 设置矩形框的位置
			defaultX = relativeMasterViewCenterX;
			defaultY = mRelativeMasterViewY + masterViewHeight;

			translation = "translationY";

			break;
		case top_right:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(shortSide, 0, 0, longSide, shortSide, longSide);

			layoutParams.setMargins(0, longSide, 0, 0);
			arrowlayoutParams.height = arrowWidth;
			arrowlayoutParams.width = shortSide;

			setArrowViewLocation(bodyWidth - shortSide, 0);

			// 设置矩形框的位置
			defaultX = relativeMasterViewCenterX - bodyWidth;
			defaultY = mRelativeMasterViewY + masterViewHeight;

			translation = "translationY";

			break;
		case top:

			//
			bevelSide = arrowWidth;
			bottomSide = (int) (Math.sqrt(2) * bevelSide);
			shortSide = bottomSide / 2;

			// longSide = (int) Math.sqrt((Math.pow(bevelSide, 2) - Math.pow(
			// shortSide, 2)));
			longSide = bottomSide / 2;

			drawTriangleArrow(shortSide, 0, 0, longSide, bottomSide, longSide);

			layoutParams.setMargins(0, longSide, 0, 0);

			arrowlayoutParams.height = longSide;
			arrowlayoutParams.width = bottomSide;

			setArrowViewLocation((bodyWidth - bottomSide) / 2, 0);
			// 设置矩形框的位置
			defaultX = relativeMasterViewCenterX - bodyWidth / 2;
			defaultY = mRelativeMasterViewY + masterViewHeight;

			translation = "translationY";

			break;
		case bottom:

			bevelSide = arrowWidth;
			bottomSide = (int) (Math.sqrt(2) * bevelSide);
			shortSide = bottomSide / 2;
			longSide = bottomSide / 2;

			// 画三角形
			drawTriangleArrow(0, 0, shortSide, longSide, bottomSide, 0);

			arrowlayoutParams.height = longSide;
			arrowlayoutParams.width = bottomSide;
			arrowlayoutParams.setMargins(0, bodyHeight, 0, 0);
			// 设置三角形的位置
			setArrowViewLocation((bodyWidth - bottomSide) / 2, 0);

			// 设置矩形框的位置
			defaultX = relativeMasterViewCenterX - bodyWidth / 2;
			defaultY = mRelativeMasterViewY - bodyHeight - longSide;

			translation = "translationY";

			break;

		case bottom_left:
			longSide = arrowWidth;
			shortSide = arrowWidth;
			drawTriangleArrow(0, 0, 0, longSide, shortSide, 0);

			arrowlayoutParams.height = longSide;
			arrowlayoutParams.width = shortSide;
			arrowlayoutParams.setMargins(0, bodyHeight, 0, 0);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX + masterViewWidth / 3;
			defaultY = mRelativeMasterViewY - bodyHeight - longSide;

			translation = "translationY";
			break;
		case bottom_right:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(0, 0, shortSide, longSide, shortSide, 0);

			arrowlayoutParams.height = longSide;
			arrowlayoutParams.width = shortSide;
			arrowlayoutParams.setMargins(0, bodyHeight, 0, 0);

			// 设置三角形的位置
			setArrowViewLocation(bodyWidth - shortSide, 0);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX + masterViewWidth / 3 * 2
					- bodyWidth;
			defaultY = mRelativeMasterViewY - bodyHeight - longSide;

			translation = "translationY";

			break;
		case left_top:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(0, 0, longSide, shortSide, longSide, 0);

			layoutParams.setMargins(longSide, 0, 0, 0);

			arrowlayoutParams.height = shortSide;
			arrowlayoutParams.width = longSide;

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX + masterViewWidth;
			defaultY = mRelativeMasterViewY + masterViewHeight / 3;

			translation = "translationX";

			break;
		case left_bottom:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(0, shortSide, longSide, shortSide, longSide, 0);

			layoutParams.setMargins(longSide, 0, 0, 0);

			arrowlayoutParams.height = shortSide;
			arrowlayoutParams.width = longSide;
			// 设置三角形的位置
			setArrowViewLocation(0, bodyHeight - shortSide);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX + masterViewWidth;
			defaultY = mRelativeMasterViewY + masterViewHeight / 3 * 2
					- bodyHeight;

			translation = "translationX";

			break;
		case left:

			bevelSide = arrowWidth;
			bottomSide = (int) (Math.sqrt(2) * bevelSide);
			shortSide = bottomSide / 2;
			longSide = bottomSide / 2;

			drawTriangleArrow(0, shortSide, longSide, bottomSide, longSide, 0);

			layoutParams.setMargins(longSide, 0, 0, 0);

			arrowlayoutParams.height = bottomSide;
			arrowlayoutParams.width = longSide;
			// 设置三角形的位置
			setArrowViewLocation(0, (bodyHeight - bottomSide) / 2);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX + masterViewWidth;
			defaultY = mRelativeMasterViewY + masterViewHeight / 2 - bodyHeight
					/ 2;
			translation = "translationX";

			break;
		case right_top:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(0, 0, 0, shortSide, longSide, 0);

			arrowlayoutParams.height = shortSide;
			arrowlayoutParams.width = longSide;
			arrowlayoutParams.addRule(RelativeLayout.RIGHT_OF,
					R.id.poptip_contentholder);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX - bodyWidth - longSide;
			defaultY = mRelativeMasterViewY + masterViewHeight / 3;

			translation = "translationX";

			break;
		case right_bottom:
			longSide = arrowWidth;
			shortSide = arrowWidth;

			drawTriangleArrow(0, 0, 0, shortSide, longSide, shortSide);

			arrowlayoutParams.height = shortSide;
			arrowlayoutParams.width = longSide;
			arrowlayoutParams.addRule(RelativeLayout.RIGHT_OF,
					R.id.poptip_contentholder);
			arrowlayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,
					R.id.poptip_contentholder);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX - bodyWidth - longSide;
			defaultY = mRelativeMasterViewY + masterViewHeight / 3 * 2
					- bodyHeight;
			translation = "translationX";

			break;
		case right:

			bevelSide = arrowWidth;
			bottomSide = (int) (Math.sqrt(2) * bevelSide);
			shortSide = bottomSide / 2;
			longSide = bottomSide / 2;

			drawTriangleArrow(0, 0, 0, bottomSide, longSide, shortSide);
			arrowlayoutParams.height = bottomSide;
			arrowlayoutParams.width = longSide;
			arrowlayoutParams.addRule(RelativeLayout.RIGHT_OF,
					R.id.poptip_contentholder);
			arrowlayoutParams
					.setMargins(0, (bodyHeight - bottomSide) / 2, 0, 0);

			// 设置矩形框的位置
			defaultX = mRelativeMasterViewX - bodyWidth - longSide;
			defaultY = mRelativeMasterViewY + masterViewHeight / 2 - bodyHeight
					/ 2;

			translation = "translationX";

			break;

		default:
			break;
		}
		// 在offsetX或者offsetY是0的情况下，就使用默认的位置，如果可点击控件的左上角坐标加上基准点的值为负值的话，就认为是超出屏幕范围了，这个时候取坐标值为0.
		setPopTipLocation(
				offsetX == 0 ? defaultX : Math.max(0, mRelativeMasterViewX
						+ offsetX),
				offsetY == 0 ? defaultY : Math.max(0, mRelativeMasterViewY
						+ offsetY));

		if (TRANSLATION_Y_COMPAT.equals(translation)) {

			setAnimator(
					TRANSLATION_Y_COMPAT,
					mRelativeMasterViewY,
					offsetY == 0 ? defaultY : Math.max(0, mRelativeMasterViewY
							+ offsetY));

		} else {

			setAnimator(
					TRANSLATION_X_COMPAT,
					mRelativeMasterViewX,
					offsetX == 0 ? defaultX : Math.max(0, mRelativeMasterViewX
							+ offsetX));

		}
		mArrowView.setLayoutParams(arrowlayoutParams);
		mContentHolder.setLayoutParams(layoutParams);
		
		ObjectAnimator alpha = ObjectAnimator.ofFloat(this, ALPHA_COMPAT, 0, 0.5f,1);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(anim).with(alpha);
		animatorSet.setDuration(500);
		animatorSet.setInterpolator(new DecelerateInterpolator());
		animatorSet.start();
	}

	private void setAnimator(String propertyName, float from, float to) {
		anim = ObjectAnimator.ofFloat(this, propertyName, from, to);
	}

	private void drawTriangleArrow(int startX, int startY, int secondlyLineX,
			int secondlyLineY, int thirdLineX, int thirdLineY) {

		mArrowView.setStartX(startX);
		mArrowView.setStartY(startY);
		mArrowView.setSecondlyLineX(secondlyLineX);
		mArrowView.setSecondlyLineY(secondlyLineY);
		mArrowView.setThirdLineX(thirdLineX);
		mArrowView.setThirdLineY(thirdLineY);

	}

	private void setPopTipLocation(int x, int y) {
		setX(x);
		setY(y);
	
	}

	@SuppressLint("NewApi")
	private void setArrowViewLocation(int x, int y) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mArrowView.setX(x);
			mArrowView.setY(y);
		} else {
			ViewHelper.setX(mArrowView, x);
			ViewHelper.setY(mArrowView, y);

		}

	}

	// 注册浮动框点击事件
	public void set_pop_click_listener(
			final OnPopTipViewClickedListener listener) {
		mListener = listener;
	}

	// 设置浮动框上要显示的内容子 View
	public void set_content_view(View view) {
		mContentHolder.removeAllViews();
		mContentHolder.addView(view);
	}

	// 隐藏浮动框
	@SuppressLint("NewApi")
	public void hide() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
			setX(params.leftMargin);
			setY(params.topMargin);
			params.leftMargin = 0;
			params.topMargin = 0;
			setLayoutParams(params);
		}

		Collection<Animator> animators = new ArrayList<Animator>(5);
		// animators.add(ObjectAnimator.ofInt(this, TRANSLATION_Y_COMPAT,
		// (int) getY(), mRelativeMasterViewY + clickableView.getHeight()
		// / 2 - getHeight() / 2));
		// animators.add(ObjectAnimator.ofInt(this, TRANSLATION_X_COMPAT,
		// (int) getX(), mRelativeMasterViewX + clickableView.getWidth()
		// / 2 - mWidth / 2));
		// animators.add(ObjectAnimator.ofFloat(this, SCALE_X_COMPAT, 1, 0));
		// animators.add(ObjectAnimator.ofFloat(this, SCALE_Y_COMPAT, 1, 0));

		animators.add(ObjectAnimator.ofFloat(this, ALPHA_COMPAT, 1, 0.5f));
		animators.add(ObjectAnimator.ofFloat(this, ALPHA_COMPAT, 0.5f, 0f));

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(animators);
		animatorSet.addListener(new DisappearanceAnimatorListener());
		animatorSet.start();

	}

	@Override
	public void onClick(final View view) {
		hide();

		if (mListener != null) {
			mListener.onPopTipViewClicked(this);
		}
	}

	/**
	 * Convenience method for setting X.
	 */
	@SuppressLint("NewApi")
	@Override
	public void setX(final float x) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			super.setX(x);
		} else {
			ViewHelper.setX(this, x);
		}
	}


	/**
	 * Convenience method for setting Y.
	 */
	@SuppressLint("NewApi")
	@Override
	public void setY(final float y) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			super.setY(y);
		} else {
			ViewHelper.setY(this, y);
		}
	}

	public interface OnPopTipViewClickedListener {
		void onPopTipViewClicked(PopTipView toolTipView);
	}

	private class DisappearanceAnimatorListener extends AnimatorListenerAdapter {

		@Override
		public void onAnimationStart(final Animator animation) {
		}

		@Override
		public void onAnimationEnd(final Animator animation) {
			if (getParent() != null) {
				((ViewManager) getParent()).removeView(PopTipView.this);
			}
		}

		@Override
		public void onAnimationCancel(final Animator animation) {
		}

		@Override
		public void onAnimationRepeat(final Animator animation) {
		}
	}
}
