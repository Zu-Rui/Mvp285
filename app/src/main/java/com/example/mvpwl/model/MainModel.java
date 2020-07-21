package com.example.mvpwl.model;

import com.example.mvpwl.api.ApiService;
import com.example.mvpwl.base.BaseModel;
import com.example.mvpwl.bean.FuliBean;
import com.example.mvpwl.net.BaseObserver;
import com.example.mvpwl.net.HttpUtils;
import com.example.mvpwl.net.MainCallBack;
import com.example.mvpwl.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void initHttp(MainCallBack mainCallBack){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.base_Url, ApiService.class);
        Observable<FuliBean> observable = apiserver.getFuli();
        observable.compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FuliBean>(this) {
                    @Override
                    protected void onSuccess(FuliBean fuliBean) {
                        mainCallBack.onSuccess(fuliBean);
                    }

                    @Override
                    protected void error(String err) {
                        mainCallBack.onFail("网络错误"+err);
                    }
                });


//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ApiService.base_Url)
//                .build();
//        ApiService service = retrofit.create(ApiService.class);
//        Observable<FuliBean> observable = service.getFuli();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<FuliBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(FuliBean fuliBean) {
//                        mainCallBack.onSuccess(fuliBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mainCallBack.onFail("网络错误"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
