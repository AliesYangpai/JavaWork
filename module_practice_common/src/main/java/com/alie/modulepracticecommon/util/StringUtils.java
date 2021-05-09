package com.alie.modulepracticecommon.util;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String TAG = "StringUtils";
    private static Gson mGson = new Gson();

    /**
     * 判断一个String能否转为int
     */
    public static boolean isInteger(String str) {
        Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(str);
        return mer.find();
    }

    /**
     * 字符串转数字
     *
     * @param radix        进制,默认10
     * @param defaultValue 若转换失败,则返回默认值
     */
    public static int str2Int(String src, int radix, int defaultValue) {
        if (TextUtils.isEmpty(src)) {
            return defaultValue;
        }

        try {
            assert src != null;
            return Integer.parseInt(src, radix);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static long str2Long(String src, int radix, long defaultValue) {
        if (TextUtils.isEmpty(src)) {
            return defaultValue;
        }

        try {
            assert src != null;
            return Long.parseLong(src, radix);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static float str2Float(String src, float defaultValue) {
        if (TextUtils.isEmpty(src)) {
            return defaultValue;
        }

        try {
            assert src != null;
            return Float.parseFloat(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static double str2Double(String src, double defaultValue) {
        if (TextUtils.isEmpty(src)) {
            return defaultValue;
        }

        try {
            assert src != null;
            return Double.parseDouble(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(@Nullable String src) {
        return src == null || src.length() == 0;

    }

    /**
     * 直接序列化(不做格式缩进)
     */
    public static String toJson(Object obj) {
        return toJsonInternal(mGson, obj);
    }

    /**
     * 序列化
     */
    private static String toJsonInternal(Gson gson, Object obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            Log.d(TAG, "toJson() fail:" + e.getMessage());
            return obj.toString();
        }
    }

    /**
     * TODO 解析Json数据为目标对象，int可能会被转为double类型，请注意该项
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> T parseJson(String json, Class<? extends T> cls) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mGson.fromJson(json, cls);
        } catch (Exception e) {
            Log.d(TAG, "parseJson() fail, srcJson:" + json + "\nerrorMsg:" + e.getMessage());
            return null;
        }
    }
}
