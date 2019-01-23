package com.example.gaofengze.demo.tools;

import android.content.Context;
import android.os.Handler;

import com.example.gaofengze.demo.data.App;


/**
 * Created by gfz on 2018/10/10
 */
public class UIUtils {
    public static Context getContext(){
        return App.mContext;
    }
    public static Handler getHandler(){
        return App.mHandlers;
    }
    public static void runOnUIThread(Runnable runnable){
        if( isInMainThread()){
            runnable.run();
        }else{
            getHandler().post(runnable);
        }
    }
    public static boolean isInMainThread(){
        return App.mainThreadId == android.os.Process.myTid();
    }

}
