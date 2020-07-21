package com.example.mvpwl.presenter;

import com.example.mvpwl.base.BasePresenter;
import com.example.mvpwl.model.CollectModel;
import com.example.mvpwl.net.CollectCallback;
import com.example.mvpwl.view.CollectView;

public class CollectPresenter extends BasePresenter<CollectView> implements CollectCallback {


    private CollectModel model;

    @Override
    public void initModel() {
        model = new CollectModel();
        addModel(model);
    }

    @Override
    public void onSuccess(String str) {
        mView.setName(str);
    }

    public void getString(){
        model.getString(this);
    }

    @Override
    public void onFail(String str) {
        mView.onToast(str);
    }
}
