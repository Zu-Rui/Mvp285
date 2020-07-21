package com.example.day05work2.model;

import com.example.day05work2.api.ApiService;
import com.example.day05work2.base.BaseModel;
import com.example.day05work2.bean.HomeBean;
import com.example.day05work2.net.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel extends BaseModel {
    public void initHttp(HomeCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.base_Url)
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Observable<HomeBean> observable = service.getBean();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail("网络错误"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
