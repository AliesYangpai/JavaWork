package com.alie.modulepracticecommon.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.telephony.TelephonyManager;

import com.alie.modulepracticecommon.R;
import com.alie.modulepracticecommon.CommonAppication;


public class CommonUtil {

    private static final String TAG = "CommonUtil";
    /**
     * 获取当前线程信息, 线程名/id/是否主线程等
     */
    public static String getThreadInfo() {
        Thread thread = Thread.currentThread();
        return "name:" + thread.getName() + ",id:" + thread.getId() + ",isMain:" + isMainThread();
    }
    /**
     * 判断当前是否在主线程
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
    /**
     * 获取主线程id
     */
    public static long getMainThreadId() {
        return Looper.getMainLooper().getThread().getId();
    }

    /**
     * 获取当前线程id
     */
    public static long getcurrentThreadId() {
        return Thread.currentThread().getId();
    }

    //获取设备号
    @SuppressLint("HardwareIds")
    public static String getDeviceID(Context context) {
        String deviceId = new String("");
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String strTm = tm.getDeviceId();
            int length = strTm.length();
            if(length < 32)
            {
                int diffLength = 32-length;
                for(int i=0;i<diffLength;i++)
                {

                    deviceId += "0";
                }
            }

            deviceId += strTm;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceId;
    }

    /**
     * 距离单位转换：米转公里
     *
     * @param dis
     * @return
     */
    public static String distanceUnitTransform(long dis){
        StringBuffer sb = new StringBuffer();
        int distance = (int) dis;
        if (distance >= 1000) {
            int kiloMeter = distance / 1000;
            int leftMeter = distance % 1000;
            leftMeter = leftMeter / 100;
            if(kiloMeter > 100){
                sb.append(kiloMeter);
                sb.append(getResources().getString(R.string.km));
            } else if (leftMeter > 0) {
                sb.append(kiloMeter);
                sb.append(".");
                sb.append(leftMeter);
                sb.append(getResources().getString(R.string.km));
            } else {
                sb.append(kiloMeter);
                sb.append(getResources().getString(R.string.km));
            }
        } else {
            sb.append(distance);
            sb.append(getResources().getString(R.string.route_meter));
        }
        return sb.toString();
    }

    public static Resources getResources(){
        return CommonAppication.Companion.getApplication().getApplicationContext().getResources();
    }

}
