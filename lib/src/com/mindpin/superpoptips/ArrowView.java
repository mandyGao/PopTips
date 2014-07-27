package com.mindpin.superpoptips;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class ArrowView extends View {

	private int color;
	private int secondlyLineX;
	private int secondlyLineY;
	private int thirdLineX;
	private int thirdLineY;
	private int startX = 10;
	private int startY = 0;
	

	public ArrowView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ArrowView(Context context, AttributeSet attrt) {
		super(context, attrt);

	}

	public ArrowView(Context context, AttributeSet attrt, int defStyle) {
		super(context, attrt, defStyle);

	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSecondlyLineX() {
		return secondlyLineX;
	}

	public void setSecondlyLineX(int secondlyLineX) {
		this.secondlyLineX = secondlyLineX;
	}

	public int getSecondlyLineY() {
		return secondlyLineY;
	}

	public void setSecondlyLineY(int secondlyLineY) {
		this.secondlyLineY = secondlyLineY;
	}

	public int getThirdLineX() {
		return thirdLineX;
	}

	public void setThirdLineX(int thirdLineX) {
		this.thirdLineX = thirdLineX;
	}

	public int getThirdLineY() {
		return thirdLineY;
	}

	public void setThirdLineY(int thirdLineY) {
		this.thirdLineY = thirdLineY;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}
	Paint paint = new Paint();
	Path path2 = new Path();
	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		canvas.drawColor(Color.TRANSPARENT);
		paint.setAntiAlias(true);
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(3);

        //画第一个顶点
		path2.moveTo(startX, startY);
	    //画第二条边
		path2.lineTo(secondlyLineX, secondlyLineY);
		 //画第三条边
		path2.lineTo(thirdLineX, thirdLineY);

		path2.close();
		canvas.drawPath(path2, paint);

	}

}
