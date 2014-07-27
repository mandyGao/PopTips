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

	private void addRedPopTipView() {

		mRedPopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_redtv));
		mRedPopTipView.setOnPopTipViewClickedListener(this);
		mRedPopTipView.setWidth(100);
		mRedPopTipView.setHeight(50);
		mRedPopTipView.setArrowLocation(ArrowLocation.top_left);
		mRedPopTipView.setArrowWidth(30);
		// mRedPopTipView.setArrowPointOffset(50,50);
		mRedPopTipView.setPopText("A beautiful Button");
		mRedPopTipView.setBackgroud(getResources().getColor(R.color.holo_red));

	}

	private void addGreenPopTipView() {

		mGreenPopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_greentv));
		mGreenPopTipView.setOnPopTipViewClickedListener(this);
		mGreenPopTipView.setWidth(100);
		mGreenPopTipView.setHeight(50);
		mGreenPopTipView.setArrowLocation(ArrowLocation.top_right);
		mGreenPopTipView.setArrowWidth(30);
		mGreenPopTipView.setPopText("Another beautiful Button!");
		mGreenPopTipView.setBackgroud(getResources().getColor(
				R.color.holo_green));

	}

	private void addBluePopTipView() {

		mBluePopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_bluetv));
		mBluePopTipView.setOnPopTipViewClickedListener(this);

		mBluePopTipView.setWidth(100);
		mBluePopTipView.setHeight(70);
		mBluePopTipView.setArrowLocation(ArrowLocation.left_top);
		mBluePopTipView.setArrowWidth(30);
		mBluePopTipView.setPopText("Moarrrr buttons!");
		mBluePopTipView
				.setBackgroud(getResources().getColor(R.color.holo_blue));

	}

	private void addBlueDarkPopTipView() {

		mBlueDarkPopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mBlueDarkPopTipView.setOnPopTipViewClickedListener(this);

		mBlueDarkPopTipView.setWidth(100);
		mBlueDarkPopTipView.setHeight(70);
		mBlueDarkPopTipView.setArrowLocation(ArrowLocation.top);
		mBlueDarkPopTipView.setArrowWidth(30);
		mBlueDarkPopTipView.setPopText("bule dark!");
		mBlueDarkPopTipView.setBackgroud(getResources().getColor(
				R.color.holo_blue_bright));

	}

	private void addGreenDarkPopTipView() {

		mGreenDarkPopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mGreenDarkPopTipView.setOnPopTipViewClickedListener(this);

		mGreenDarkPopTipView.setWidth(100);
		mGreenDarkPopTipView.setHeight(70);
		mGreenDarkPopTipView.setArrowLocation(ArrowLocation.right_top);
		mGreenDarkPopTipView.setArrowWidth(30);
		mGreenDarkPopTipView.setPopText("green dark!");
		mGreenDarkPopTipView.setBackgroud(getResources().getColor(
				R.color.holo_green_dark));

	}

	private void addPurplePopTipView() {

		mPurplePopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_purpletv));
		mPurplePopTipView.setOnPopTipViewClickedListener(this);
		mPurplePopTipView.setWidth(100);
		mPurplePopTipView.setHeight(70);
		mPurplePopTipView.setArrowLocation(ArrowLocation.bottom);
		mPurplePopTipView.setArrowWidth(30);
		// mPurplePopTipView.setPopText("Moarrrr buttons!");
		mPurplePopTipView.setContentView(LayoutInflater.from(this).inflate(
				R.layout.custom_poptip, null));
		mPurplePopTipView.setBackgroud(getResources().getColor(
				R.color.holo_purple));

	}

	private void addOrangePopTipView() {

		mOrangePopTipView = mPopTipFrameLayout
				.showPopTipForView(findViewById(R.id.activity_main_orangetv));
		mOrangePopTipView.setOnPopTipViewClickedListener(this);
		Log.v("mandy",
				"dimen: " + getResources().getDimension(R.dimen.arrow_width));

		mOrangePopTipView.setWidth(100);
		mOrangePopTipView.setHeight(70);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.bottom);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left);
		mOrangePopTipView.setArrowLocation(ArrowLocation.bottom_left);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.bottom_right);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left_top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.left_bottom);

		// mOrangePopTipView.setArrowLocation(ArrowLocation.right_top);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.right_bottom);
		// mOrangePopTipView.setArrowLocation(ArrowLocation.right);
		mOrangePopTipView.setArrowWidth(30);
		mOrangePopTipView.setPopText("Tap me!");
		mOrangePopTipView.setBackgroud(getResources().getColor(
				R.color.holo_orange));

	}

	@Override
	public void onClick(final View view) {
		int id = view.getId();
		if (id == R.id.activity_main_redtv) {
			if (mRedPopTipView == null) {
				addRedPopTipView();
			} else {
				mRedPopTipView.hide();
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
