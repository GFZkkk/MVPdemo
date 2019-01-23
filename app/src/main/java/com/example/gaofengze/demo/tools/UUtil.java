package com.example.gaofengze.demo.tools;

import android.view.View;

/**
 * Created by gaofengze on 2019/a1/3
 */
public class UUtil {
    public static float getMoney(String money){
        if(money == null || "".equals(money)){
            return 0.0f;
        }
        String[] s = money.split("\\.");
        if(s.length == 1){
            return  Float.parseFloat(s[0]);
        }else{
            return  Float.parseFloat(money);
        }
    }
    /**
     *将布尔转换为是否显示
     */
    public static int getVisibilityByBoolean(boolean f){
        return f ? View.VISIBLE : View.INVISIBLE;
    }

    public static int getReverseVisibility(int status){
        return status == View.VISIBLE ? View.INVISIBLE : View.VISIBLE;
    }

}
