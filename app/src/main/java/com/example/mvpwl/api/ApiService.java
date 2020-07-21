package com.example.mvpwl.api;

import com.example.mvpwl.bean.FuliBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String base_Url = "https://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/13")
    Observable<FuliBean> getFuli();
}
