package com.example.day05work2.net;

import com.example.day05work2.bean.HomeBean;

public interface HomeCallBack {
    void onSuccess(HomeBean homeBean);
    void onFail(String str);
}
