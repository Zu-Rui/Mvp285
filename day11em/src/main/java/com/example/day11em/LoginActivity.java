package com.example.day11em;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameEt;
    private EditText mPwdEt;
    private Button mLoginBtn;
    private Button mRegistBtn;
    private LinearLayout mLlEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mNameEt = (EditText) findViewById(R.id.et_name);
        mPwdEt = (EditText) findViewById(R.id.et_pwd);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
        mRegistBtn = (Button) findViewById(R.id.btn_regist);
        mRegistBtn.setOnClickListener(this);
        mLlEt = (LinearLayout) findViewById(R.id.et_ll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            default:
                break;
        }
    }

    private void login() {
        String name = mNameEt.getText().toString();
        String pwd = mPwdEt.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            EMClient.getInstance().login(name, pwd, new EMCallBack() {
                @Override
                public void onSuccess() {
                    //成功
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            //记录登录的账号，在后面的页面中显示

                        }
                    });
                }

                @Override
                public void onError(int i, String s) {
                    //失败
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"退出登录");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 1) {
            EMClient.getInstance().logout(true);
            Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
