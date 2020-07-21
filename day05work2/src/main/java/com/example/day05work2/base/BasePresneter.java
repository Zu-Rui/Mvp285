package com.example.day05work2.base;

import java.util.ArrayList;

public abstract class BasePresneter<V extends BaseView> {

    public V mView;
    public ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresneter(){
        initModel();
    }

    protected abstract void initModel();
    public void addModel(BaseModel model){
        models.add(model);
    }

    public void bindView(V View){
        this.mView = View;
    }

    public void destroy(){
        mView = null;
        for (int i = 0; i < models.size(); i++) {
            models.get(i).dispose();
        }
    }

}
