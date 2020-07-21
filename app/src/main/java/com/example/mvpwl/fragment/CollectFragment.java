package com.example.mvpwl.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpwl.R;
import com.example.mvpwl.base.BaseFragment;
import com.example.mvpwl.presenter.CollectPresenter;
import com.example.mvpwl.view.CollectView;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvName.setText("1111111");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new  CollectPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collect;
    }

    @Override
    public void setName(String str) {
        tvName.setText(str);
    }

    @Override
    public void onToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mPresenter.getString();
                break;
        }
    }
}
