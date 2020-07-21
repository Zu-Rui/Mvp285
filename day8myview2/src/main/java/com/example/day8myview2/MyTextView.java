package com.example.day8myview2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    //重写三个构造方法 固定写法，底层需要用到

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写ondraw进行重画组件   Canvas表示 画布
     * @param canvas
     */

    @Override           //康wos
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);//设置画布的颜色
//        canvas.drawColor(getResources().getColor(R.color.blue));
        canvas.rotate(45,getMeasuredWidth()/10,getMeasuredHeight()/10);//旋转45度
        super.onDraw(canvas);
    }
}
