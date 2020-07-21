package com.example.day06woek.api;

import com.example.day06woek.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String base_Url = "http://c.m.163.com/nc/";
    @GET("article/list/T1348654151579/0-20.html")
    Observable<HomeBean> getBean();
}
