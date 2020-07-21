package com.example.day11em;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameEt;
    private EditText mPwdEt;
    private Button mRegistBtn;
    private LinearLayout mLlEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        mNameEt = (EditText) findViewById(R.id.et_name_regist);
        mPwdEt = (EditText) findViewById(R.id.et_pwd_regist);
        mRegistBtn = (Button) findViewById(R.id.btn_regist_regist);
        mRegistBtn.setOnClickListener(this);
        mLlEt = (LinearLayout) findViewById(R.id.et_ll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                regist();
                break;
            default:
                break;
        }
    }

    //环信注册账号
    private void regist() {
        String name = mNameEt.getText().toString().trim();
        String pwd = mPwdEt.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {//非空进行注册
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //注册
                        EMClient.getInstance().createAccount(name,pwd);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                RegistActivity.this.finish();//关闭注册页面回到登录界面
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            });
        }else {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
