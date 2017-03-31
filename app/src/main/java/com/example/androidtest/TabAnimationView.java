package com.example.androidtest;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TabAnimationView extends View {

	private Paint mPaint;
	private float strokeWidth = 5.0f;
	private int height;
	private int width;

	public TabAnimationView(Context context) {
		super(context);
		init();
	}

	public TabAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TabAnimationView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(0xFFd60d25);
		mPaint.setStrokeJoin(Join.ROUND);
		mPaint.setStrokeCap(Cap.ROUND);
		mPaint.setStrokeWidth(strokeWidth);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		height = View.MeasureSpec.getSize(heightMeasureSpec);    
	    width = View.MeasureSpec.getSize(widthMeasureSpec);    
	    Log.i("king", "height "+height+" width "+width);
	    setMeasuredDimension(width,60);  
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		for (int i = 0; i < 20; i++) {

			float startX = i * strokeWidth + 2 * i;
			float stopX = i * strokeWidth + 2 * i;
			float startY = 50;
			float stopY;
			if (8 < i && i < 13) {
				stopY = getRandom(20) + 20;
			} else if ((0 <= i && i < 4) || (15 < i && i <= 19)) {
				stopY = getRandom(10)+1;
			} else {
				stopY = getRandom(15) + 10;
			}
			canvas.drawLine(startX, startY, stopX, (startY - stopY), mPaint);
		}

	}

	private int getRandom(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}

}
