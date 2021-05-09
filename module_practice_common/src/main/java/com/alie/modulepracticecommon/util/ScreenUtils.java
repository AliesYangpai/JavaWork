package com.alie.modulepracticecommon.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


public class ScreenUtils {

    /**
     * 获取屏幕信息
     * @param activity
     */
    public static void getScreenInfo(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        AutoConstant.mScreenWidth = dm.widthPixels;
//        AutoConstant.mScreenHeight = dm.heightPixels;
    }

    // 获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager windowmanager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowmanager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    // 得到屏幕高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取 View 的坐标
     * <p>
     * RectF rect = calcViewScreenLocation(view);
     * boolean isInViewRect = rect.contains(x, y);
     *
     * @param view
     * @return
     */
    public static RectF calcViewScreenLocation(View view) {
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }
}
