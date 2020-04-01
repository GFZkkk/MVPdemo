package com.gaofengze.demo.util.tools;

import android.content.Context;

import com.gaofengze.demo.data.App;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by gaofengze on 2018/11/14
 */
public class KProgressHUDUtil {
    private KProgressHUD hud;

    private KProgressHUDUtil(){
    }

    private static class LazyHolder{

        private static final KProgressHUDUtil INSTANCE = new KProgressHUDUtil();
    }

    public static KProgressHUDUtil getInstance(){
        return LazyHolder.INSTANCE;
    }

    public void loadWait(Context context){
        if(App.activityList != null){
            loadFinish();
            hud = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f)
                    .show();
        }
    }

    public void loadFinish(){
        if (hud != null){
            hud.dismiss();
            hud = null;
        }
    }
}
