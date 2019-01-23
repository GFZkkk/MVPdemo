package com.example.gaofengze.demo.tools;


import com.example.gaofengze.demo.R;
import com.example.gaofengze.demo.data.App;

/**
 * Created by gaofengze on 2019/1/15
 */
public class NetWorkHelper {

    public static void showError(String code){
        switch (code){
            /*case NetWorkCode.logout: ToastUtils.toast(App.mContext.getResources().getString(R.string.error_logout));
                LoginHelper.logout();
                break;*/
            default:ToastUtils.toast(App.mContext.getResources().getString(R.string.error_network));
        }
    }
}
