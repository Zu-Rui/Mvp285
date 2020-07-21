package com.example.day8myview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BallView extends View {
    private final Paint mPaint;
    private float mMoveX = 60;//球心的开始位置
    private float mMoveY = 60;
    private boolean isUp = false;

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isUp != true) {
            mPaint.setColor(Color.YELLOW);
            canvas.drawCircle(mMoveX,mMoveY,60,mPaint);
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE){//当手指移动式获得最新的坐标
            isUp = false;
            mMoveX = event.getX();
            mMoveY = event.getY();
        }else if(event.getAction() == MotionEvent.ACTION_UP){

            isUp = true;
        }
        //在新的位置重新绘制小球
        invalidate();
        return true;
    }
}
