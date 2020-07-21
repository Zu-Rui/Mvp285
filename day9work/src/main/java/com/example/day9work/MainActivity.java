package com.example.day9work;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day9work.adapter.VpAdapter;
import com.example.day9work.base.BaseActivity;
import com.example.day9work.bean.HomeBean;
import com.example.day9work.fragment.HomeFragment;
import com.example.day9work.fragment.UpDownFragment;
import com.example.day9work.presenter.HomePresenter;
import com.example.day9work.view.HomeView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeView {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    private VpAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new UpDownFragment());

        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.home);
        tab.getTabAt(1).setText("我的").setIcon(R.drawable.up);




    }

    @Override
    public void setData(HomeBean homeBean) {}

    @Override
    protected void initPresenter() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onToast(String str) {

    }

}
