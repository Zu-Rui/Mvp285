package com.example.mvpwl.model;

import com.example.mvpwl.base.BaseModel;
import com.example.mvpwl.net.CollectCallback;

public class CollectModel extends BaseModel {
    public void getString(CollectCallback collectCallback){
        try {
            String str = "22222222222222----通过p和m";
            collectCallback.onSuccess(str);
        } catch (Exception e) {
            e.printStackTrace();
            collectCallback.onFail(e.getMessage());
        }
    }
}
