package com.example.gaofengze.demo.data;

import java.lang.annotation.Retention;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by gfz on 2018/10/17
 */
public class Contanst {
    public static String phone = "+886 227551808";

    @Retention(SOURCE)
    @IntDef({PAGE_All,PAGE_DOOR,PAGE_WINE,PAGE_LAMP,PAGE_MONITOR})
    public @interface IOTPage {}
    public static final int PAGE_All = 0;
    public static final int PAGE_DOOR = 1;
    public static final int PAGE_WINE = 2;
    public static final int PAGE_LAMP = 3;
    public static final int PAGE_MONITOR = 4;
//    灯光(00:关闭/01:打开); 门禁(01:打开); 智能柜(00:打开);
    @Retention(SOURCE)
    @StringDef({OPEN_DOOR,OPEN_LAMP,OPEN_WINE,CLOSE_LAMP})
    public @interface IOTControl {}
    public static final String OPEN_DOOR = "01";
    public static final String OPEN_LAMP = "01";
    public static final String OPEN_WINE = "00";
    public static final String CLOSE_LAMP = "00";


}
