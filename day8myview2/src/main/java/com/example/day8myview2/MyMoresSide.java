package com.example.day8myview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyMoresSide extends View {

    private final Paint mPaint;
    private Path mPath;

    public MyMoresSide(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setAntiAlias(true);//去掉锯齿
        mPaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPath = new Path();//实例化历经对象
        //创建三个点
        int x1 = 50, y1 = 50;
        int x2 = 150, y2 = 50;
        int x3 = 100, y3 = 100;
        //路径从第一个点开始
        mPath.moveTo(x1,y1);
        //添加路径
        mPath.lineTo(x1,y1);
        mPath.lineTo(x2,y2);
        mPath.lineTo(x3,y3);
        mPath.lineTo(x1,y1);

        canvas.drawPath(mPath,mPaint);//开始画

        super.onDraw(canvas);
    }
}
