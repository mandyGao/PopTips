##浮动框泡泡组件 ##

**浮动框泡泡组件** 是一个由用户点击view而触发一个浮动提示框，此浮动框泡泡组件是由矩形框和三角形箭头组成

##所涉及到的类API说明如下：##

此类用于是浮动框组件的父类，通过show的方法可以浮动框组件类PopTipView添加界面上   

    public class PopTipRelativeLayout extends RelativeLayout {
    
    public PopTipRelativeLayout(final Context context) {
        super(context);
    }

    public PopTipRelativeLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public PopTipRelativeLayout(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }
      //显示浮动框
      public void show(final View view) {
        addView(view);
    }
}

PopTipView类是实现浮动框泡泡组件最主要的类


    public class **PopTipView** extends LinearLayout implements

		ViewTreeObserver.OnPreDrawListener, View.OnClickListener {
      //初始化类，并把可点击view传进来
     public PopTipView(final Context context, View clickableView) {
		super(context);
		this.clickableView = clickableView;
		init();
	}
    //三角箭头相对于方框的位置，一共十二种
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
    //如果传的值为0，那么就用默认位置
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
     // 以 int 表示颜色
	public void set_backgroud(final int bg_color) {
		mArrowView.setColor(bg_color);
		mContentHolder.setBackgroundColor(bg_color);
	}
    //以十六进制的字符串来表示颜色
	public void set_backgroud(String bg_color) {
		set_backgroud(Color.parseColor(bg_color));
	}
    /**
     * 矩形框内默认的text
     */
	public void set_pop_text(String text) {
		mTextContent.setText(text);
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
    //隐藏浮动框
    public void hide() {
      //浮动框的消失，启动消失动画
   }

###使用方法：###
-  在自己的布局文件中增加com.mindpin.superpoptips.PopTipRelativeLayout设置宽和高为 match_parent, 并确保这个view处于最上层。
- 在代码中找到这个PopTipRelativeLayout类，通过show的方法把PopTipView显示出来，在show之前可以设置属性。
###例子：###


    <RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/activity_main_redtv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true" />

    <com.mindpin.superpoptips.PopTipRelativeLayout
        android:id="@+id/activity_main_poptipframelayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    </RelativeLayout>

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

    PopTipRelativeLayout mPopTipFrameLayout = (PopTipRelativeLayout) findViewById(R.id.activity_main_poptipframelayout);

    PopTipView  mGreenPopTipView = new PopTipView(this, findViewById(R.id.activity_main_greentv));
		mGreenPopTipView.set_pop_click_listener(this);
		mGreenPopTipView.set_width(100);
		mGreenPopTipView.set_height(50);
		mGreenPopTipView.set_arrow_location(ArrowLocation.top_right);
		mGreenPopTipView.set_arrow_width(30);
		mGreenPopTipView.set_pop_text("Another beautiful Button!");
		mGreenPopTipView.set_backgroud(getResources().getColor(
				R.color.holo_green));
		mPopTipFrameLayout
		.show(mGreenPopTipView);

###效果图：###

![](http://blog.csdn.net/gaolixiao/article/details/38240601)
