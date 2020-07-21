package com.example.day05work2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day05work2.adapter.HomeAdapter;
import com.example.day05work2.base.BaseActivity;
import com.example.day05work2.bean.HomeBean;
import com.example.day05work2.presenter.HomePresenter;
import com.example.day05work2.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeView {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_o)
    Button btnO;
    @BindView(R.id.btn_t)
    Button btnT;
    @BindView(R.id.btn_s)
    Button btnS;
    private ArrayList<HomeBean.T1348647909107Bean> beans;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.initHttp();
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        beans = new ArrayList<>();
        adapter = new HomeAdapter(this, beans);
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
    public void setData(HomeBean homeBean) {
        List<HomeBean.T1348647909107Bean> list = homeBean.getT1348647909107();
        beans.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onToast(String str) {

    }



    @OnClick({R.id.btn_o, R.id.btn_t, R.id.btn_s})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_o:
                adapter.check = true;
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_t:
                ArrayList<HomeBean.T1348647909107Bean> arrayList = new ArrayList<>();
                for (int i = 0; i < beans.size(); i++) {
                    HomeBean.T1348647909107Bean t1348647909107Bean = beans.get(i);
                    if (!t1348647909107Bean.onShow()){
                        arrayList.add(t1348647909107Bean);
                    }
                }
                beans.clear();
                beans.addAll(arrayList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_s:
                adapter.check = false;
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
