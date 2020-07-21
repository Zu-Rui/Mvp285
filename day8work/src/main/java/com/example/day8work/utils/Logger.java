package com.example.day8work.utils;

import android.util.Log;

import com.example.day8work.base.Constants;

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){//打印的开关
            Log.d(tag, "logD: "+msg);
        }
    }

    public static void print(String msg){
        if (Constants.isDebug){
            System.out.print(msg);
        }
    }
}
