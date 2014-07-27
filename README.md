PopTips
=======

A popupwindow with arrow,the arrow is dynamic


PopTips 是一个带三角形的弹出框，三角形的位置有12种：

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
	
而且可以设置三角形的边长、弹出框的背景色、宽度和长度等等。

使用方法：
1、在XML 布局文件中添加PopTipRelativeLayout（com.mindpin.superpoptips.PopTipRelativeLayout）,并设置它的长和宽为match_parent，并且确保这个view在屏幕的最上层。
2、在代码中获得PopTipRelativeLayout，通过它来显示popTips。

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






 
