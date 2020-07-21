package com.example.mvpwl.presenter;

import com.example.mvpwl.base.BasePresenter;
import com.example.mvpwl.bean.FuliBean;
import com.example.mvpwl.model.MainModel;
import com.example.mvpwl.net.MainCallBack;
import com.example.mvpwl.view.MainView;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {


    private MainModel mainModel;

    @Override
    public void initModel() {
        mainModel = new MainModel();
        addModel(mainModel);
    }

    @Override
    public void onSuccess(FuliBean fuliBean) {
        mView.setData(fuliBean);
    }

    @Override
    public void onFail(String str) {
        mView.onToast(str);
    }

    public void initHttp() {
        mainModel.initHttp(this);
    }

}
