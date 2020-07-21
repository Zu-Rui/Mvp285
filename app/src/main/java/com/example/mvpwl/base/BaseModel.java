package com.example.mvpwl.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    //这是一个容器RxJava专门用来放Disposable调用dispose（）可以吧容器的网络订阅取消
    public CompositeDisposable mDisposable = null;
    //由M层把自己的网络订阅起来方便取消
    public void addDisposable(Disposable disposable){
        if (mDisposable == null) {
            synchronized (this){
                if (mDisposable == null)
                    mDisposable = new CompositeDisposable();
            }
        }
        //添加
        mDisposable.add(disposable);
    }
    //取消订阅
    public void disponse(){
        mDisposable.dispose();
    }
    //移除
    public void removerDisposable(Disposable disposable){
        if (mDisposable != null) {
            mDisposable.remove(disposable);
        }
    }
}
