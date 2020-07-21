package com.example.day8work.net;

import com.example.day8work.bean.HomeBean;

public interface HomeCallBack {
    void onSuccess(HomeBean homeBean);
    void onFail(String error);
}
