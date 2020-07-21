package com.example.day5work.net;

import com.example.day5work.bean.HomeBean;

public interface MainCallBack {
    void onSuccess(HomeBean homeBean);
    void onFail(String error);
}
