package com.example.day05work2.api;

import com.example.day05work2.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String base_Url = "http://c.m.163.com/nc/";
    @GET("article/headline/T1348647909107/0-20.html")
    Observable<HomeBean> getBean();
}
