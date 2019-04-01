package com.gaofengze.demo.util.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gaofengze on 2018/12/5
 */
public class CalendarUtil {
    private int beginYear;
    /*非闰年天数*/
    private int[] monthDayN = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /*闰年天数*/
    private int[] monthDayL = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public CalendarUtil(int beginYear) {
        this.beginYear = beginYear;
    }

    public List<String> getYear(){
        List<String> list = new ArrayList<>();
        for (int i = beginYear; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
            list.add(i+"");
        }
        return list;
    }

    public List<String> getMonth(){
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i+"月");
        }
        return list;
    }

    public List<String> getDay(Calendar calendar){
        List<String> list = new ArrayList<>();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int[] monthDay = getMonthDay(year);
        int beginDay = getOneDayOfWeek(calendar);
        for (int i = 1; i <= 42; i++) {
            int d;
            if (i<=beginDay){
                if(month == 0){
                    d = getMonthDay(year-1)[11]+i-beginDay;
                }else{
                    d = monthDay[month-1]+i-beginDay;
                }
            }else if (i>monthDay[month]+beginDay){
                d = i-(monthDay[month]+beginDay);
            }else{
                d = i-beginDay;
            }
            list.add(String.valueOf(d));
        }
        return list;
    }

    /**
     * 获取某年的月份数组
     */
    private int[] getMonthDay(int year){
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) ? monthDayL : monthDayN;
    }

    /**
     * 得到以星期日为起始的本月第一天之前的天数
     */
    public int getOneDayOfWeek(Calendar calendar){
        return (7 + calendar.get(Calendar.DAY_OF_MONTH) - (calendar.get(Calendar.DAY_OF_WEEK) % 7)) % 7;
    }
}
