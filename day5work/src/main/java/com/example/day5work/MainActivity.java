package com.example.day5work;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5work.adapter.HomeAdapter;
import com.example.day5work.base.BaseActivity;
import com.example.day5work.bean.HomeBean;
import com.example.day5work.model.MainModel;
import com.example.day5work.presenter.MainPresenter;
import com.example.day5work.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.recyler)
    RecyclerView recyler;
    private List<HomeBean.T1348647909107Bean> list;
    private HomeAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPresenter.getString();
    }

    @Override
    public void initView() {

        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new HomeAdapter(this, list);
        recyler.setAdapter(adapter);
    }

    @Override
    public void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    public void setData(HomeBean homeBean) {
        List<HomeBean.T1348647909107Bean> beans = homeBean.getT1348647909107();
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void Toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
}
