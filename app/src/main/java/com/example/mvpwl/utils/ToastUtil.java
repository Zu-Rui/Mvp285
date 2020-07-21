package com.example.mvpwl.utils;

import android.widget.Toast;

import com.example.mvpwl.base.BaseApp;

public class ToastUtil {
    public static void showShort(String msg){
        Toast.makeText(BaseApp.sBaseApp,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg){
        Toast.makeText(BaseApp.sBaseApp,msg,Toast.LENGTH_LONG).show();
    }
}
