package com.example.day05work2.presenter;

import com.example.day05work2.base.BasePresneter;
import com.example.day05work2.bean.HomeBean;
import com.example.day05work2.model.HomeModel;
import com.example.day05work2.net.HomeCallBack;
import com.example.day05work2.view.HomeView;

public class HomePresenter extends BasePresneter<HomeView> implements HomeCallBack {


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
    public void onFail(String str) {
        mView.onToast(str);
    }

    public void initHttp(){
        homeModel.initHttp(this);
    }
}
