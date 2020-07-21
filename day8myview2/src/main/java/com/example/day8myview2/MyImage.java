package com.example.day8myview2;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyImage extends View {

    private final Paint mPaint;

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 0, 0, mPaint);
        super.onDraw(canvas);
    }
}
