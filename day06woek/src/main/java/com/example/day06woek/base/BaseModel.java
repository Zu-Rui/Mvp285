package com.example.day06woek.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {

    public CompositeDisposable mDisposable = null;

    public void addDisposable (Disposable disposable){
        if (mDisposable == null) {
            synchronized (this){
                if (mDisposable == null) {
                    mDisposable = new CompositeDisposable();
                }
            }
        }
        //添加
        mDisposable.add(disposable);
    }

    //取消订阅
    public void dispose(){
        mDisposable.dispose();
    }
    //移除
    public void removerDisposable(Disposable disposable){
        if (mDisposable != null) {
            mDisposable.remove(disposable);
        }
    }

}
