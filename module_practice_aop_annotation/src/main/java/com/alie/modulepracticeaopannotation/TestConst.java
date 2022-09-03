package com.alie.modulepracticeaopannotation;

import android.util.Log;

public class TestConst {
    private static final String TAG = "TestConst";

    public static void showLog(String tag, String param) {
        Log.d(TAG, "aopWork " + tag + " " + param);
    }
}
