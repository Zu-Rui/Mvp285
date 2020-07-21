package com.example.day6touchevent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTouchmeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        mTouchmeBtn = (Button) findViewById(R.id.btn_touchme);
        mTouchmeBtn.setOnClickListener(this);

        mTouchmeBtn.setOnTouchListener(new View.OnTouchListener() {
            //当触摸此按钮时会  回调此匿名内部类监听器对象的onTouch方法
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();//得到当前的触摸动作
                if (action == MotionEvent.ACTION_DOWN) {
                    Log.i("TAG", "onTouch: 按下按钮");
                } else if (action == MotionEvent.ACTION_MOVE) {
                    Log.i("TAG", "onTouch: 在按钮上滑动");
                }else if (action == MotionEvent.ACTION_UP) {
                    Log.i("TAG", "onTouch: 在按钮上抬起");
                }else {
                    Log.i("TAG", "onTouch: 结束");
                }

                //返回 假 表示触摸事件没有消费，会继续往下传递，生成点击和长按事件
                // 如果返回true 的话表示消费了，到此为止 点击和长按时间不会产生
                return false;//false不能随便改
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_touchme:
                Log.i("TAG", "onClick: 点击到了");
                Toast.makeText(this, "点击了此按钮", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.i("111", "activity  -------  dispatchTouchEvent: "+b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.i("111", "activity  -------  dispatchTouchEvent: "+b);
        return b;
    }
}
