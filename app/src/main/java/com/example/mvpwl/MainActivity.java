package com.example.mvpwl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpwl.adapter.FuliAdapter;
import com.example.mvpwl.base.BaseActivity;
import com.example.mvpwl.bean.FuliBean;
import com.example.mvpwl.presenter.MainPresenter;
import com.example.mvpwl.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_t)
    Button btnT;
    private ArrayList<FuliBean.ResultsBean> list;
    private FuliAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPresenter.initHttp();
    }

    @Override
    public void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new FuliAdapter(this, list);
        recycler.setAdapter(adapter);
    }

    @Override
    public void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setData(FuliBean fuliBean) {
        List<FuliBean.ResultsBean> results = fuliBean.getResults();
        list.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onToast(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_t})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_t:
                Intent intent = new Intent(this, CollectFragmentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
