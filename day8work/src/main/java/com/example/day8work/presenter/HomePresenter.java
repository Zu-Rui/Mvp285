package com.example.day8work.presenter;

import com.example.day8work.base.BasePresenter;
import com.example.day8work.bean.HomeBean;
import com.example.day8work.model.HomeModel;
import com.example.day8work.net.HomeCallBack;
import com.example.day8work.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> implements HomeCallBack {


    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        addModel(homeModel);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        mView.getView(homeBean);
    }

    @Override
    public void onFail(String error) {
        mView.onToast(error);
    }

    public void initHttp(){
        homeModel.initHttp(this);
    }
}
