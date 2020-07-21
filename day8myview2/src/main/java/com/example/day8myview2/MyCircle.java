package com.example.day8myview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyCircle extends View {

    private final Paint mPaint;

    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);

        //准备画笔
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);//设置颜色
        mPaint.setStrokeWidth(15);//设置笔的宽度
        mPaint.setStyle(Paint.Style.STROKE);//空心圆
        mPaint.setAntiAlias(true);//去掉锯齿;
    }

    @Override
    protected void onDraw(Canvas canvas) {
                    //sk
        canvas.drawCircle(100,100,50,mPaint);

        super.onDraw(canvas);
    }
}
