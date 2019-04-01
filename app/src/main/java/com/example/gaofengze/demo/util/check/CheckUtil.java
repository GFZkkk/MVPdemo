package com.example.gaofengze.demo.util.check;

/**
 * Created by gaofengze on 2018/12/4
 */
public class CheckUtil {
    public static boolean isNotEmptyString(String str){
        return str != null && str.length()>0;
    }

    public static boolean isTel(String tel){
        String regex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        return tel.matches(regex);
    }

    public static boolean isTelP(String phone){
        return phone.matches("^(0?9)\\d{8}$");
    }
}
