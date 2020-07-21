package com.example.mvpwl.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpwl.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{

    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initPresenter();
        if (mPresenter != null)
            mPresenter.bindView(this);
        initView();
        initData();
        initListener();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    public abstract void initPresenter();

    public abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.destroy();
            mPresenter = null;
        }
    }
}
