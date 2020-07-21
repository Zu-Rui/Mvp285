package com.example.day5work.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {

    public V mView;
    public ArrayList<BaseModel> models = new ArrayList<>();

    public void BasePresenter(){
        initModel();
    }

    public abstract void initModel();
    public void addModel(BaseModel model){
        models.add(model);
    }

    public void bindView(V View){
        this.mView =View;
    }

    public void destroy(){
        mView=null;
        for (int i = 0; i < models.size(); i++) {
            models.get(i).disponse();
        }
    }

}
