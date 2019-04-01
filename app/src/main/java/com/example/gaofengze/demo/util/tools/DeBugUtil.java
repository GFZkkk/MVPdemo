package com.example.gaofengze.demo.util.tools;

import android.util.Log;

/**
 * Created by gfz on 2018/10/17
 */
public class DeBugUtil {
    private static final String TAG = "system.out:";
    private static final boolean DEBUG = true;

    public static void toast(String msg){
        if(DEBUG){
            ToastUtils.toast(msg);
        }
    }

    public static void debug(String msg){
        if(DEBUG){
            Log.d(TAG,msg);
        }
    }

    public static void info(String msg){
        if(DEBUG){
            Log.i(TAG,msg);
        }
    }

    public static void error(String msg){
        if(DEBUG){
            Log.e(TAG,msg);
        }
    }
}
