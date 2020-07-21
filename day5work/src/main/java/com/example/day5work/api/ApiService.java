package com.example.day5work.api;

import com.example.day5work.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String base_Url = "http://c.m.163.com/nc/";
    @GET("article/headline/T1348647909107/0-20.html")
    Observable<HomeBean> getBean();
}
