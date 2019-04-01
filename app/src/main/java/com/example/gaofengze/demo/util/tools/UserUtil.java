package com.example.gaofengze.demo.util.tools;


import com.example.gaofengze.demo.model.bean.Login;

import java.util.Date;

import static com.example.gaofengze.demo.data.NetWorkData.TOKEN;


/**
 * Created by gaofengze on 2019/1/12
 */
public class UserUtil {
    /**
     * 保存用户信息
     */
    public static void saveUserInfo(Login login , String pwd){
        saveUserInfo(login);
        SPUtil.putString("pwd",pwd);
    }

    /**
     * 保存用户信息
     */
    public static void saveUserInfo(Login login ){
        SPUtil.putString("tel",login.getUserPhone());
        SPUtil.putString("level",login.getRoleId());
        SPUtil.putString("token",login.getToken());
        SPUtil.putString("expiryTime", String.valueOf(login.getExpiryTime().getTime()));
    }

    /**
     * token是否可用
     */
    public static boolean isEnable(){
        String expiryTime = SPUtil.getString("expiryTime","");
        if("".equals(expiryTime)
                || "".equals(SPUtil.getString("tel",""))
                || "".equals(SPUtil.getString("level",""))
                || "".equals(SPUtil.getString("token",""))){
            return false;
        }
        return new Date().before(new Date(Long.valueOf(expiryTime)-36000000));
    }

    /**
     * 获取token
     */
    public static String getToken(){
        return TOKEN+SPUtil.getString("token","");
    }

}
