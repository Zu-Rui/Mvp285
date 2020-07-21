package com.example.day5work.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {

    public CompositeDisposable mDisposabl = null;

    public void addDisposable(Disposable disposable){
        if (mDisposabl == null){
            synchronized (BaseModel.class){
                if (mDisposabl == null) {
                    mDisposabl = new CompositeDisposable();
                }
            }
        }
        mDisposabl.add(disposable);
    }

    public void disponse(){
        mDisposabl.dispose();
    }

    public void removerDisposable(Disposable disposable){
        if (mDisposabl!=null) {
            mDisposabl.remove(disposable);
        }
    }

}
