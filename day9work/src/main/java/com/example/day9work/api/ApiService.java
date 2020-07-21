package com.example.day9work.api;

import com.example.day9work.bean.HomeBean;
import com.example.day9work.bean.UpLoadBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

public interface ApiService {
    String base_Url = "https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<HomeBean> getBean();

//    String base_Down = "http://cdn.banmi.com/";
//    @GET("banmiapp/apk/banmi_330.apk")

    String up_Load = "http://yun918.cn/";

    @POST("study/public/file_upload.php")
    @Multipart
    Observable<UpLoadBean> upload(@Part("key") RequestBody requestBody, @Part MultipartBody.Part file);

    String down_Load = "https://dl.hdslb.com";
    @GET("mobile/latest/iBiliPlayer-bili.apk?t=1589783162000&spm_id_from=333.47.b_646f776e6c6f61642d6c696e6b.1")
    @Streaming
    Observable<ResponseBody> download();


}
