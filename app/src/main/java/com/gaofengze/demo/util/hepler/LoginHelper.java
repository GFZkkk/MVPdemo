package com.gaofengze.demo.util.hepler;


import android.content.Intent;

import com.gaofengze.demo.callBack.CallBack;
import com.gaofengze.demo.data.App;
import com.gaofengze.demo.model.Impl.LoginImpl;
import com.gaofengze.demo.ui.activity.LoginActivity;
import com.gaofengze.demo.util.tools.DeBugUtil;
import com.gaofengze.demo.util.tools.SPUtil;


/**
 * Created by gaofengze on 2018/12/10
 */
public class LoginHelper {
    private static boolean isAutoLogin = false;
    public static void autoLogin(){
        if(isAutoLogin) return ;
        DeBugUtil.error("尝试自动登陆");
        String tel = SPUtil.getString("tel","");
        String pwd = SPUtil.getString("pwd","");
        if("".equals(tel)||"".equals(pwd)){
            logout();
            return ;
        }
        LoginImpl login = new LoginImpl();
        login.pwdLogin(tel, pwd, new CallBack() {
            @Override
            public void success() {

            }

            @Override
            public void onSubscribe() {
                isAutoLogin = true;
            }

            @Override
            public void error(String code, String err) {
                logout();
            }

            @Override
            public void onFailure(String code) {
                logout();
            }

            @Override
            public void onComplete() {
                isAutoLogin = false;
            }
        });

    }

    public static void logout(){
        SPUtil.putString("token","");
        App.getmContext().startActivity(new Intent(App.getmContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
