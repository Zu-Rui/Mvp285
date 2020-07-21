package com.example.day5work.presenter;

import com.example.day5work.base.BasePresenter;
import com.example.day5work.bean.HomeBean;
import com.example.day5work.model.MainModel;
import com.example.day5work.net.MainCallBack;
import com.example.day5work.view.MainView;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {

    private MainModel mainModel;

    @Override
    public void initModel() {
        mainModel = new MainModel();
        addModel(mainModel);
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        mView.setData(homeBean);
    }

    @Override
    public void onFail(String error) {
        mView.Toast(error);
    }

    public void getString(){
        mainModel.initHttp(this);
    }
}
