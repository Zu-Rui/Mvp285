package com.example.mvpwl.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    public P mPresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        unbinder = ButterKnife.bind(this, view);

        initPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initListener();
        return view;
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//解除绑定
        //销毁P
        mPresenter.destroy();
        mPresenter = null;
    }
}
