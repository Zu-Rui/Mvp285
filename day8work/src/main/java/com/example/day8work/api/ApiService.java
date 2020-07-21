package com.example.day8work.api;

import com.example.day8work.bean.HomeBean;
import com.example.day8work.bean.ItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String base_Url = "https://api.yunxuekeji.cn/";
    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<HomeBean> getHome();

    @GET("yunxue_app_api/teacher/getTeacherPower/{page}")
    Observable<ItemBean> getData1(@Path("page") int page);

}
