package com.example.mvpwl.net;

import com.example.mvpwl.bean.FuliBean;

public interface MainCallBack {
    void onSuccess(FuliBean fuliBean);
    void onFail(String str);
}
