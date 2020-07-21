package com.example.day9work.net;

import com.example.day9work.bean.HomeBean;

public interface HomeCallBack {
    void onSuccess(HomeBean homeBean);
    void onFail(String error);
}
