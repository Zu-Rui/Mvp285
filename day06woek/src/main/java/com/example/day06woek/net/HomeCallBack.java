package com.example.day06woek.net;

import com.example.day06woek.bean.HomeBean;

public interface HomeCallBack {
    void onSuccess(HomeBean homeBean);
    void onFail(String str);
}
