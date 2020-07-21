package com.example.day8work;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day8work.adapter.HomeAdapter;
import com.example.day8work.base.BaseActivity;
import com.example.day8work.bean.HomeBean;
import com.example.day8work.presenter.HomePresenter;
import com.example.day8work.view.HomeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeView {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.BodyBean.ResultBean> list;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnListener(new HomeAdapter.onListener() {
            @Override
            public void onclick(int position) {
                HomeBean.BodyBean.ResultBean bean = list.get(position);
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("data",bean);
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
    public void getView(HomeBean homeBean) {
        List<HomeBean.BodyBean.ResultBean> beans = homeBean.getBody().getResult();
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onToast(String str) {
        Log.i("111", "错误: "+str);
    }
}
