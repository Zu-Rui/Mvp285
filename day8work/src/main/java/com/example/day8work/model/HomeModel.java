package com.example.day8work.model;

import com.example.day8work.api.ApiService;
import com.example.day8work.base.BaseModel;
import com.example.day8work.base.BaseObserver;
import com.example.day8work.bean.HomeBean;
import com.example.day8work.net.HomeCallBack;
import com.example.day8work.net.HttpUtils;
import com.example.day8work.net.RxUtils;

import io.reactivex.Observable;

public class HomeModel extends BaseModel {
    public void initHttp(HomeCallBack callBack){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.base_Url, ApiService.class);
        Observable<HomeBean> observable = apiserver.getHome();
        observable.compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBean>(this) {
                    @Override
                    protected void onSuccess(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail("网络错误"+err);
                    }
                });
    }
}
