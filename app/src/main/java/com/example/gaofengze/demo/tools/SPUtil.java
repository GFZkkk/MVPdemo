package com.example.gaofengze.demo.tools;

import android.content.SharedPreferences;

import com.example.gaofengze.demo.data.App;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SPUtil {
    private static final String SP_NAME = "userdata";

    public static SharedPreferences getSP(){
        return App.getInstance().getSharedPreferences(SP_NAME,MODE_PRIVATE);
    }

    /**
     * 保存一个String类型的值！
     */
    public static void putString(String key, String value) {
        getSP().edit().putString(key, value).apply();
    }

    /**
     * 获取String的value
     */
    public static String getString(String key, String defValue) {
        return getSP().getString(key, defValue);
    }

    /**
     * 保存一个Boolean类型的值！
     */
    public static void putBoolean(String key, Boolean value) {
        getSP().edit().putBoolean(key, value).apply();
        getSP().getAll();
    }

    /**
     * 获取boolean的value
     */
    public static boolean getBoolean(String key, Boolean defValue) {
        return getSP().getBoolean(key, defValue);
    }

    /**
     * 保存一个int类型的值！
     */
    public static void putInt(String key, int value) {
        getSP().edit().putInt(key, value).apply();
    }

    /**
     * 获取int的value
     */
    public static int getInt(String key, int defValue) {
        return getSP().getInt(key, defValue);
    }

    /**
     * 保存一个float类型的值！
     */
    public static void putFloat(String key, float value) {
        getSP().edit().putFloat(key, value).apply();
    }

    /**
     * 获取float的value
     */
    public static float getFloat(String key, Float defValue) {
        return getSP().getFloat(key, defValue);
    }

    /**
     * 保存一个long类型的值！
     */
    public static void putLong(String key, long value) {
        getSP().edit().putLong(key, value).apply();
    }

    /**
     * 获取long的value
     */
    public static long getLong(String key, long defValue) {
        return getSP().getLong(key, defValue);
    }

    /**
     * 取出List<String>
     *
     * @param key     List<String> 对应的key
     * @return List<String>
     */
    public static List<String> getStrListValue(String key) {
        List<String> strList = new ArrayList<String>();
        int size = getInt(key + "size", 0);
        //Log.d("sp", "" + size);
        for (int i = 0; i < size; i++) {
            strList.add(getString(key + i, null));
        }
        return strList;
    }

    /**
     * 存储List<String>
     *
     * @param key     List<String>对应的key
     * @param strList 对应需要存储的List<String>
     */
    public static void putStrListValue(String key, List<String> strList) {
        if (null == strList) {
            return;
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(key);
        int size = strList.size();
        putInt(key + "size", size);
        for (int i = 0; i < size; i++) {
            putString(key + i, strList.get(i));
        }
    }

    /**
     * 清空List<String>所有数据
     *
     * @param key     List<String>对应的key
     */
    public static void removeStrList(String key) {
        int size = getInt(key + "size", 0);
        if (0 == size) {
            return;
        }
        remove(key + "size");
        for (int i = 0; i < size; i++) {
            remove(key + i);
        }
    }

    /**
     * 清空对应key数据
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSP().edit();
        editor.remove(key).apply();
    }

}
