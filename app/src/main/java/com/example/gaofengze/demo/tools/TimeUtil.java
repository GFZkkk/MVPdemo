package com.example.gaofengze.demo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gfz on 2018/10/17
 */
public class TimeUtil {
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * token是否到期
     * @param time 到期时间
     * @return 是否可用
     */
    public static boolean isEnable(String time){
        return !time.equals("") && new Date().before(new Date(Long.valueOf(time)-36000000));
    }
    public static String getTime(Long l){
        return sf.format(l);
    }
    public static String getTime(Date date){
        return sfDate.format(date);
    }
}
