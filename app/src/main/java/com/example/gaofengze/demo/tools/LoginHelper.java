package com.example.gaofengze.demo.tools;


import android.content.Intent;

import com.example.gaofengze.demo.callBack.CallBack;
import com.example.gaofengze.demo.data.App;
import com.example.gaofengze.demo.model.Impl.LoginImpl;
import com.example.gaofengze.demo.ui.activity.LoginActivity;


/**
 * Created by gaofengze on 2018/12/10
 */
public class LoginHelper {
    public static boolean autoLogin(){
        String tel = SPUtil.getString("tel","");
        String pwd = SPUtil.getString("pwd","");
        if("".equals(tel)||"".equals(pwd)){
            return false;
        }
        return false;
    }

    public static void logout(){
        App.getmContext().startActivity(new Intent(App.getmContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
