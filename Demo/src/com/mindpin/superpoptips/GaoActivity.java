package com.mindpin.superpoptips;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.mindpin.superpoptips.PopTipView.ArrowLocation;

public class GaoActivity extends Activity implements View.OnClickListener,
		PopTipView.OnPopTipViewClickedListener {

	private PopTipView mRedPopTipView;
	private PopTipView mGreenPopTipView;
	private PopTipView mBluePopTipView;
	private PopTipView mPurplePopTipView;
	private PopTipView mOrangePopTipView;
	private PopTipView mBlueDarkPopTipView;
	private PopTipView mGreenDarkPopTipView;
	private PopTipRelativeLayout mPopTipFrameLayout;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPopTipFrameLayout = (PopTipRelativeLayout) findViewById(R.id.activity_main_poptipframelayout);
		findViewById(R.id.activity_main_redtv).setOnClickListener(this);
		findViewById(R.id.activity_main_greentv).setOnClickListener(this);
		findViewById(R.id.activity_main_bluetv).setOnClickListener(this);
		findViewById(R.id.activity_main_purpletv).setOnClickListener(this);
		findViewById(R.id.activity_main_orangetv).setOnClickListener(this);
//		mPopTipFrameLayout.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (mRedPopTipView != null) {
//					mRedPopTipView.hide();
//					mRedPopTipView = null;
//				}
//				if (mGreenPopTipView != null) {
//					mGreenPopTipView.hide();
//					mGreenPopTipView = null;
//				}
//				if (mBluePopTipView != null) {
//					mBluePopTipView.hide();
//					mBluePopTipView = null;
//				}
//				if (mPurplePopTipView != null) {
//					mPurplePopTipView.hide();
//					mPurplePopTipView = null;
//				}
//				if (mOrangePopTipView != null) {
//					mOrangePopTipView.hide();
//					mOrangePopTipView = null;
//				}
//				if (mBlueDarkPopTipView != null) {
//					mBlueDarkPopTipView.hide();
//					mBlueDarkPopTipView = null;
//				}
//				if (mGreenDarkPopTipView != null) {
//					mGreenDarkPopTipView.hide();
//					mGreenDarkPopTipView = null;
//				}
//
//				return false;
//			}
//		});

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addRedPopTipView();
			}
		}, 500);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addGreenPopTipView();
			}
		}, 700);
		//
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addOrangePopTipView();
			}
		}, 900);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addBluePopTipView();
			}
		}, 1100);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addPurplePopTipView();
			}
		}, 1300);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addBlueDarkPopTipView();
			}
		}, 1500);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addGreenDarkPopTipView();
			}
		}, 1500);
		//
	}
    //添加一个背景为红色的泡泡浮动框
	private void addRedPopTipView() {

		mRedPopTipView = new PopTipView(this, findViewById(R.id.activity_main_redtv));
		
		Log.v("mandy", "after show");
		mRedPopTipView.set_pop_click_listener(this);
		mRedPopTipView.set_width(100);
		mRedPopTipView.set_height(50);
		mRedPopTipView.set_arrow_location(ArrowLocation.top_left);
		mRedPopTipView.set_arrow_width(20);
		mRedPopTipView.set_arrow_point_offset(0, 0);
		// mRedPopTipView.setArrowPointOffset(50,50);
		mRedPopTipView.set_pop_text("A beautiful Button");
		mRedPopTipView.set_backgroud(getResources().getColor(R.color.holo_red));
		mPopTipFrameLayout.show(mRedPopTipView);

	}

	private void addGreenPopTipView() {
		mGreenPopTipView = new PopTipView(this, findViewById(R.id.activity_main_greentv));
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
		

	}

	private void addBluePopTipView() {

		mBluePopTipView = new PopTipView(this, findViewById(R.id.activity_main_bluetv));
//		mBluePopTipView = mPopTipFrameLayout
//				.showPopTipForView(findViewById(R.id.activity_main_bluetv));
		mBluePopTipView.set_pop_click_listener(this);

		mBluePopTipView.set_width(100);
		mBluePopTipView.set_height(70);
		mBluePopTipView.set_arrow_location(ArrowLocation.left_top);
		mBluePopTipView.set_arrow_width(30);
		mBluePopTipView.set_pop_text("Moarrrr buttons!");
		mBluePopTipView
				.set_backgroud(getResources().getColor(R.color.holo_blue));
		mPopTipFrameLayout.show(mBluePopTipView);
	}

	private void addBlueDarkPopTipView() {
		mBlueDarkPopTipView = new PopTipView(this, findViewById(R.id.activity_main_orangetv));
//		mBlueDarkPopTipView = mPopTipFrameLayout
//				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mBlueDarkPopTipView.set_pop_click_listener(this);

		mBlueDarkPopTipView.set_width(100);
		mBlueDarkPopTipView.set_height(70);
		mBlueDarkPopTipView.set_arrow_location(ArrowLocation.top);
		mBlueDarkPopTipView.set_arrow_width(20);
		mBlueDarkPopTipView.set_pop_text("bule dark!");
		mBlueDarkPopTipView.set_backgroud(getResources().getColor(
				R.color.holo_blue_bright));
		mPopTipFrameLayout.show(mBlueDarkPopTipView);

	}

	private void addGreenDarkPopTipView() {
		mGreenDarkPopTipView = new PopTipView(this, findViewById(R.id.activity_main_orangetv));
//		mGreenDarkPopTipView = mPopTipFrameLayout
//				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mGreenDarkPopTipView.set_pop_click_listener(this);

		mGreenDarkPopTipView.set_width(100);
		mGreenDarkPopTipView.set_height(70);
		mGreenDarkPopTipView.set_arrow_location(ArrowLocation.right_top);
		mGreenDarkPopTipView.set_arrow_width(30);
		mGreenDarkPopTipView.set_pop_text("green dark!");
		mGreenDarkPopTipView.set_backgroud(getResources().getColor(
				R.color.holo_green_dark));

		mPopTipFrameLayout.show(mGreenDarkPopTipView);
	}

	private void addPurplePopTipView() {

		mPurplePopTipView = new PopTipView(this, findViewById(R.id.activity_main_purpletv));
//		mPurplePopTipView = mPopTipFrameLayout
//				.showPopTipForView(findViewById(R.id.activity_main_purpletv));
		mPurplePopTipView.set_pop_click_listener(this);
		mPurplePopTipView.set_width(100);
		mPurplePopTipView.set_height(70);
		mPurplePopTipView.set_arrow_location(ArrowLocation.bottom);
		mPurplePopTipView.set_arrow_width(30);
		mPurplePopTipView.set_arrow_point_offset(-50, 0);
		// mPurplePopTipView.setPopText("Moarrrr buttons!");
		mPurplePopTipView.set_content_view(LayoutInflater.from(this).inflate(
				R.layout.custom_poptip, null));
		mPurplePopTipView.set_backgroud(getResources().getColor(
				R.color.holo_purple));
		mPopTipFrameLayout.show(mPurplePopTipView);
	}

	private void addOrangePopTipView() {

		mOrangePopTipView = new PopTipView(this, findViewById(R.id.activity_main_orangetv));
//		mOrangePopTipView = mPopTipFrameLayout
//				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mOrangePopTipView.set_pop_click_listener(this);
		Log.v("mandy",
				"dimen: " + getResources().getDimension(R.dimen.arrow_width));

		mOrangePopTipView.set_width(100);
		mOrangePopTipView.set_height(70);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.bottom);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left);
		mOrangePopTipView.set_arrow_location(ArrowLocation.bottom_left);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.bottom_right);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left_top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left_bottom);

		// mOrangePopTipView.setArrowLocation(ArrowLocation.right_top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.right_bottom);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.right);
		mOrangePopTipView.set_arrow_width(30);
		mOrangePopTipView.set_pop_text("Tap me!");
		mOrangePopTipView.set_backgroud(getResources().getColor(
				R.color.holo_orange));
		mPopTipFrameLayout.show(mOrangePopTipView);
		
	}

	@Override
	public void onClick(final View view) {
		int id = view.getId();
		if (id == R.id.activity_main_redtv) {
			if (mRedPopTipView == null) {
				addRedPopTipView();
			} else {
				mRedPopTipView.hide();
				mPopTipFrameLayout.removeView(view);
				mRedPopTipView = null;
				
			}

		} else if (id == R.id.activity_main_greentv) {
			if (mGreenPopTipView == null) {
				addGreenPopTipView();
			} else {
				mGreenPopTipView.hide();
				mGreenPopTipView = null;
			}

		} else if (id == R.id.activity_main_bluetv) {
			if (mBluePopTipView == null) {
				addBluePopTipView();
			} else {
				mBluePopTipView.hide();
				mBluePopTipView = null;
			}

		} else if (id == R.id.activity_main_purpletv) {
			if (mPurplePopTipView == null) {
				addPurplePopTipView();
			} else {
				mPurplePopTipView.hide();
				mPurplePopTipView = null;
			}

		} else if (id == R.id.activity_main_orangetv) {
			if (mOrangePopTipView == null) {
				addOrangePopTipView();
				addBlueDarkPopTipView();
				addGreenDarkPopTipView();
			} else {
				mOrangePopTipView.hide();
				mBlueDarkPopTipView.hide();
				mGreenDarkPopTipView.hide();
				mOrangePopTipView = null;
				mBlueDarkPopTipView = null;
				mGreenDarkPopTipView = null;
			}

		}
	}

	@Override
	public void onPopTipViewClicked(final PopTipView popTipView) {
		if (mRedPopTipView == popTipView) {
			mRedPopTipView = null;
		} else if (mGreenPopTipView == popTipView) {
			mGreenPopTipView = null;
		} else if (mBluePopTipView == popTipView) {
			mBluePopTipView = null;
		} else if (mPurplePopTipView == popTipView) {
			mPurplePopTipView = null;
		} else if (mOrangePopTipView == popTipView) {
			mOrangePopTipView = null;
		} else if (mGreenDarkPopTipView == popTipView) {
			mGreenDarkPopTipView = null;
		} else if (mBlueDarkPopTipView == popTipView) {
			  mBlueDarkPopTipView = null;
		} 
	}
}
