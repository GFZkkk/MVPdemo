package com.gaofengze.demo.util.hepler;


import com.gaofengze.demo.R;
import com.gaofengze.demo.data.App;
import com.gaofengze.demo.data.NetWorkData;
import com.gaofengze.demo.util.tools.ToastUtils;

/**
 * Created by gaofengze on 2019/1/15
 */
public class NetWorkErrorHelper {

    public static void showError(String code){
        switch (code){
            /*case NetWorkData.logout: ToastUtils.toast(App.mContext.getResources().getString(R.string.error_logout));
                LoginHelper.logout();
                break;*/
            case NetWorkData.serverError:

                break;
            default:
                ToastUtils.toast(App.mContext.getResources().getString(R.string.error_network));
        }
    }
}
