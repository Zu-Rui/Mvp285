package com.example.mvpwl.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {

    public V mView;//V层的mView
    public ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter(){
        initModel();//处理model
    }

    public abstract void initModel();
    public void addModel(BaseModel model){
        models.add(model);
    }


    public void bindView(V View){
        this.mView = View;
    }

    public void destroy(){
        mView = null;
        for (int i = 0; i < models.size(); i++) {
            models.get(i).disponse();//解除网络请求，网络请求自然毁掉

        }
    }
}
