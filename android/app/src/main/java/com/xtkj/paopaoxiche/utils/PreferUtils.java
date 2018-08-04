package com.xtkj.paopaoxiche.utils;


import android.content.Context;
import android.content.SharedPreferences;


public class PreferUtils {
    private static PreferUtils sInstance;
    private static SharedPreferences mPref;

    private final static String spName = "PAOPAOXICHE";

    private PreferUtils(Context context) {
        mPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }


    public static PreferUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PreferUtils.class) {
                sInstance = new PreferUtils(context);
            }
        }
        return sInstance;
    }


    /**
     * 存整数
     * @param String    key
     * @param value  value
     */
    public void putInt(String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }


    /**
     * 取整数
     * @param String   key
     * @return      value
     */
    public int getInt(String key) {
        return mPref.getInt(key,0);
    }



    /**
     * 存字符串
     * @param String    key
     * @param value  value
     */
    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }


    /**
     * 取字符串
     * @param String   key
     * @return      value
     */
    public String getString(String key) {
        return mPref.getString(key, "");
    }


    /**
     * 存boolean
     * @param String    key
     * @param value  value
     */
    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }


    /**
     * 取 boolean
     * @param String       key
     * @param defValue  default value
     * @return    boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }


    /**
     * 删除 key
     * @param String   key
     */
    public static void remove(String key) {
        mPref.edit().remove(key).apply();
    }


    /**
     * 清空sp
     * @return  boolean
     */
    public static boolean clear() {
        return mPref.edit().clear().commit();
    }

}
