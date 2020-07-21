package com.example.day06woek.presenter;

import com.example.day06woek.base.BasePresenter;
import com.example.day06woek.bean.HomeBean;
import com.example.day06woek.model.HomeModel;
import com.example.day06woek.net.HomeCallBack;
import com.example.day06woek.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> implements HomeCallBack {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        addModel(homeModel);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        mView.onSuccess(homeBean);
    }

    @Override
    public void onFail(String str) {
        mView.onToast(str);
    }

    public void initHttp(){
        homeModel.initHttp(this);
    }
}
