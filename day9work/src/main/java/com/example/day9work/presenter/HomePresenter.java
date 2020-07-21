package com.example.day9work.presenter;

import com.example.day9work.base.BasePresenter;
import com.example.day9work.bean.HomeBean;
import com.example.day9work.model.HomeModel;
import com.example.day9work.net.HomeCallBack;
import com.example.day9work.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> implements HomeCallBack {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        addModel(homeModel);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        mView.setData(homeBean);
    }

    @Override
    public void onFail(String error) {
        mView.onToast(error);
    }

    public void initHttp(){
        homeModel.initHttp(this);
    }

}
