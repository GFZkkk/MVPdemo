package com.gaofengze.demo.adapter;

import com.gaofengze.demo.base.adapter.BaseRecyclerViewAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * created by gfz on 2020-01-03
 */

public abstract class BaseCalendarAdapter<T> extends BaseRecyclerViewAdapter<T> {

    /**
     * 显示的月的数量
     */
    private int monthNum;
    /**
     * 日历开始时间
     */
    private String startDate;
    /**
     * 日历结束时间
     */
    private String endDate;
    /**
     * 现在的时间
     */
    private String nowDate;
    /**
     * 日历的数据
     */
    private List<List<T>> monthList;
    /**
     * 当前展示的月份
     */
    private int focusMonth;
    /**
     * 所需时间格式
     */
    private static String shortDateFormatStr = "yyyy-MM-dd";

    public BaseCalendarAdapter(String beginDate, String endDate) {
        this(beginDate,endDate, getCurStandardShortDate());
    }

    public BaseCalendarAdapter(String startDate, String endDate, String nowDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.nowDate = nowDate;
        int startMonth = Integer.parseInt(startDate.split("-")[1]);
        int endMonth = Integer.parseInt(endDate.split("-")[1]);
        int nowMonth = Integer.parseInt(nowDate.split("-")[1]);
        monthNum = (12 + endMonth - startMonth) % 12 + 1;

        if (compareTo(nowDate,startDate) == AFTER){
            focusMonth = 0;
        }else if(compareTo(nowDate,endDate) == BEFORE){
            focusMonth = monthNum - 1;
        }else{
            focusMonth = (12 + nowMonth - startMonth) % 12;
        }

        setNeedAutoFilterEmptyData(false);
    }

    /**
     * 展示日历
     */
    public void show() {
        if (monthList == null){
            loadMonthList();
        }
        refresh(monthList.get(focusMonth));
    }

    /**
     * 获取当前显示的年月
     */
    public String getDateTime() {
        String[] date = startDate.split("-");
        if (date.length < 3) return "";
        int year = Integer.valueOf(date[0]);
        int month = Integer.valueOf(date[1]) + focusMonth;
        if (month > 12) {
            month -= 12;
            year++;
        }
        return String.format("%s年%s月", year, month);
    }

    /**
     * 看下一个月
     */
    public void laterMonth() {
        if (haveNext()) {
            focusMonth++;
            show();
        }
    }

    /**
     * 看上一个月
     */
    public void preMonth() {
        if (havePre()) {
            focusMonth--;
            show();
        }
    }

    /**
     * 是否还有前一个月的数据
     */
    public boolean havePre(){
        return focusMonth > 0;
    }

    /**
     * 是否还有下一个月的数据
     */
    public boolean haveNext(){
        return focusMonth < monthNum - 1;
    }

    /**
     * 加载每个月的数据
     */
    private void loadMonthList() {
        if (startDate != null) {
            String[] date = startDate.split("-");
            if (date.length < 3) return;
            int year = Integer.valueOf(date[0]);
            int startMonth = Integer.valueOf(date[1]);
            monthList = new ArrayList<>();
            for (int i = 0; i < monthNum; i++) {
                int month = startMonth + i;
                monthList.add(getDayList(year + (month - 1) / 12, (month - 1) % 12 + 1));
            }
        }
    }

    /**
     * 得到每个月的天数的列表
     */
    private List<T> getDayList(int year, int month) {
        List<T> dayList = new ArrayList<>();
        Calendar dateTime = Calendar.getInstance();
        dateTime.set(year, month - 1, 1);
        //补足空白天数
        for (int i = 1; i < dateTime.get(Calendar.DAY_OF_WEEK); i++) {
            dayList.add(null);
        }
        //加上有效天数
        for (int i = 0; i < getMonthDay(year, month); i++) {
            dayList.add(getCalendarBean(year, month, i + 1));
        }
        return dayList;
    }

    public abstract T getCalendarBean(int year, int month, int day);

    /**
     * 比较日期用到的标示
     */
    final int ERROR = 2;
    final int BEFORE = -1;
    final int AFTER = 1;
    final int SAME = 0;

    /**
     * 判断 2019-08-04 与 2020-07-06 的先后关系
     * return 2020-07-06 是否在 2019-08-04 之后
     */
    int compareTo(String date1, String date2){
        if (date1 == null || date2 == null){
            return ERROR;
        }
        String []dateTime1 = date1.split("-");
        String []dateTime2 = date2.split("-");
        if (dateTime1.length < 3 || dateTime2.length < 3){
            return ERROR;
        }
        for (int i = 0; i < dateTime1.length; i++) {
            int time1 = Integer.valueOf(dateTime1[i]);
            int time2 = Integer.valueOf(dateTime2[i]);
            if (time1 < time2){
                return AFTER;
            }else if(time1 > time2){
                return BEFORE;
            }
        }
        return SAME;
    }

    /**
     * 获取某个月的天数
     */
    private int getMonthDay(int year, int month) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ? 29 : 28;
            default:
                return 31;
        }
    }

    /**
     * 获取展示的月的数量
     */
    public int getMonthNum() {
        return monthNum;
    }

    /**
     * 获取日历开始日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 获取日历结束日期
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 获取现在的日期
     */
    public String getNowDate() {
        return nowDate;
    }

    public static String getCurStandardShortDate(){
        DateFormat df = new SimpleDateFormat(shortDateFormatStr, Locale.getDefault());
        return df.format(new Date());
    }
}

