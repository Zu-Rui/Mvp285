package com.example.day06woek;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day06woek.adapter.HomeAdapter;
import com.example.day06woek.base.BaseActivity;
import com.example.day06woek.bean.HomeBean;
import com.example.day06woek.presenter.HomePresenter;
import com.example.day06woek.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeView {

    @BindView(R.id.toolbarr)
    Toolbar toolbarr;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.T1348654151579Bean> list;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnListener(new HomeAdapter.onListener() {
            @Override
            public void onlistener(int position) {
                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                HomeBean.T1348654151579Bean bean = list.get(position);
                intent.putExtra("a",bean.getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initHttp();
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new HomeAdapter(this, list);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void onSuccess(HomeBean homeBean) {
        List<HomeBean.T1348654151579Bean> beans = homeBean.getT1348654151579();
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onToast(String str) {

    }
}
