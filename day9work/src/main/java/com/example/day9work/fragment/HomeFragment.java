package com.example.day9work.fragment;

import android.content.Intent;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day9work.R;
import com.example.day9work.UpLoadActivity;
import com.example.day9work.adapter.HomeAdapter;
import com.example.day9work.base.BaseFragment;
import com.example.day9work.bean.HomeBean;
import com.example.day9work.presenter.HomePresenter;
import com.example.day9work.view.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<HomeBean.DataBean.DatasBean> list;
    private HomeAdapter adapter;

    @Override
    protected void initListener() {
        adapter.setOnClickListener(new HomeAdapter.onClickListener() {
            @Override
            public void onclick(int position) {
                startActivity(new Intent(getActivity(), UpLoadActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initHttp();
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(),RecyclerView.VERTICAL));
        list = new ArrayList<>();
        adapter = new HomeAdapter(getActivity(), list);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {

        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setData(HomeBean homeBean) {

        HomeBean.DataBean data = homeBean.getData();
        list.addAll(data.getDatas());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onToast(String str) {

    }
}
