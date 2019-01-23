package com.example.gaofengze.demo.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.gaofengze.demo.data.App;

/**
 * Created by gaofengze on 2019/a1/4
 */

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = App.mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = App.mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getWidth(){
        WindowManager wm = (WindowManager)App.getmContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels;
    }

    public static int getHeight(){
        WindowManager wm = (WindowManager)App.getmContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        return dm.heightPixels;
    }



}
