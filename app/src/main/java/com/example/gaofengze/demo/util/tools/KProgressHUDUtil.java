package com.example.gaofengze.demo.util.tools;

import android.content.Context;

import com.example.gaofengze.demo.data.App;
import com.kaopiz.kprogresshud.KProgressHUD;


/**
 * Created by gaofengze on 2018/11/14
 */
public class KProgressHUDUtil {
    private static KProgressHUD hud;

    public static void loadWait(Context context){
        if(App.activityList != null){
            loadFinish();
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f)
                    .show();
        }
    }

    public static void loadFinish(){
        if (hud != null){
            hud.dismiss();
            hud = null;
        }
    }
}
