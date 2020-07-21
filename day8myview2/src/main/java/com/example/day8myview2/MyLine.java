package com.example.day8myview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 一条线
 * 1.准备画笔 通常在构造方法中准备
 */

public class MyLine extends View {

    private final Paint mPaint;

    public MyLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        //准备画笔
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);//设置颜色
        mPaint.setStrokeWidth(15);//设置笔的宽度
        mPaint.setAntiAlias(true);//去掉锯齿;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int fitstX = 50;//fst
        int firstY = 100;
        int secondX = 300;//skd
        int secondY = 100;
        canvas.drawLine(fitstX,firstY,secondX,secondY,mPaint);
        super.onDraw(canvas);
    }
}
