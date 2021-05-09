package com.alie.modulepracticecommon.util;


/**
 * 网络工具类
 * // todo fix error red for follow-up tasks
 */
public class SdkNetworkUtil {

    private static final String TAG = "SdkNetworkUtil";
    /**
     * Class of broadly defined "WIFI" networks.
     */
    public static final int SIGNAL_WIFI_ENABLE = 6;
    public static final int SIGNAL_WIFI_UNENABLE = 7;
    public static final int NETWORK_CLASS_ETHERNET = 9;

//    /**
//     * 是否有网络连接
//     */
//    public static boolean isNetworkConnected() {
//        Log.d(TAG, "isNetworkConnected");
//        ConnectivityManager connMgr = getConnectivityService(SdkApplicationUtils.Companion.getApplication().getApplicationContext());
//        if (null != connMgr) {
//            Log.d(TAG, "isNetworkConnected null != connMgr");
//            NetworkInfo info = getActiveNetworkInfo();
//            if (null != info && info.isConnected()){
//                int netWorkType = getNetWorkType(SdkApplicationUtils.Companion.getApplication().getApplicationContext());
//                Log.d(TAG, "isNetworkConnected info.isConnected() netWorkType = {?}",netWorkType);
//                if(netWorkType == NetworkStatus.AUTO_UNKNOWN_ERROR) {
//                    return false;
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static ConnectivityManager getConnectivityService(Context context){
//        ConnectivityManager connectivityManager = null;
//        try {
//            connectivityManager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//        } catch (Exception e) {
//            Log.e("AutoNetworkUtil", "Exception={?}",e);
//        }
//        return connectivityManager;
//    }
//
//    /**
//     * 包含屏蔽局域网逻辑
//     * @return
//     */
//    private static NetworkInfo getActiveNetworkInfo(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) SdkApplicationUtils.Companion.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(connectivityManager == null){
//            return null;
//        }
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        String connectState = activeNetworkInfo != null? (activeNetworkInfo.isConnected()? "connected":"not connected") :"null";
//        Log.d(TAG, "getActiveNetworkInfo activeNetworkInfo :"+connectState);
//        return activeNetworkInfo;
//    }
//
//    /**
//     * 获取网络类型，未知（无网络），wifi，2g,3g,4g，其他网络
//     * @param context
//     * @return HMI定义的网络类型，如AutoNetworkUtil.NETWORK_CLASS_UNKNOWN
//     */
//    public static int getNetWorkType(Context context) {
//        if (null == context) {
//            return NetworkStatus.AUTO_UNKNOWN_ERROR;
//        }
//        ConnectivityManager connectivityManager = getConnectivityService(context);
//        if (null != connectivityManager) {
//            NetworkInfo activeNetInfo = getActiveNetworkInfo();
//            if (activeNetInfo != null && activeNetInfo.isConnected()){
//                int type = activeNetInfo.getType();
//                Log.d(TAG, "getNetWorkType type = {?}", type);
//                if (type == ConnectivityManager.TYPE_WIFI) {
//                    return NetworkStatus.NetworkStatusWiFi;
//                } else if (type == ConnectivityManager.TYPE_MOBILE) {
//                    return getMobileNetType(context);
//                } else{
//                    return NetworkStatus.NetworkStatusOther;
//                }
//            } else {
//                Log.d(TAG, "getNetWorkType: activeNetInfo=null or activeNetInfo.isConnected()=false");
//            }
//        } else {
//            Log.d(TAG, "getNetWorkType: connectivityManager=null");
//        }
//        return NetworkStatus.AUTO_UNKNOWN_ERROR;
//    }
//
//    /**
//     * 获取移动网络的网络类型2g,3g,4g
//     */
//    private static int getMobileNetType(Context context) {
//        if (null == context) {
//            return NetworkStatus.NetworkStatusOther;
//        }
//        TelephonyManager telephonyMgr = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        if (null != telephonyMgr) {
//            switch (telephonyMgr.getNetworkType()) {
//                case TelephonyManager.NETWORK_TYPE_GPRS:
//                case TelephonyManager.NETWORK_TYPE_EDGE:
//                case TelephonyManager.NETWORK_TYPE_CDMA:
//                case TelephonyManager.NETWORK_TYPE_1xRTT:
//                case TelephonyManager.NETWORK_TYPE_IDEN:
//                    return NetworkStatus.NetworkStatus2G;
//                case TelephonyManager.NETWORK_TYPE_UMTS:
//                case TelephonyManager.NETWORK_TYPE_EVDO_0:
//                case TelephonyManager.NETWORK_TYPE_EVDO_A:
//                case TelephonyManager.NETWORK_TYPE_HSDPA:
//                case TelephonyManager.NETWORK_TYPE_HSUPA:
//                case TelephonyManager.NETWORK_TYPE_HSPA:
//                case TelephonyManager.NETWORK_TYPE_EVDO_B:
//                case TelephonyManager.NETWORK_TYPE_EHRPD:
//                case TelephonyManager.NETWORK_TYPE_HSPAP:
//                    return NetworkStatus.NetworkStatus3G;
//                case TelephonyManager.NETWORK_TYPE_LTE:
//                    return NetworkStatus.NetworkStatus4G;
//                default:
//                    return NetworkStatus.NetworkStatusOther;
//            }
//        }
//        return NetworkStatus.NetworkStatusOther;
//    }
//
//    public static class NetReceiver extends BroadcastReceiver {
//        public static int isWifiEnable;
//
//        public NetReceiver() {
//        }
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            // 先通知Wifi是否打开,这个监听wifi的打开与关闭，与wifi的连接无关
//            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
//                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
//                switch (wifiState) {
//                    case WifiManager.WIFI_STATE_DISABLED:
//                        Log.d("WIFI状态", "关闭"+wifiState);
//                        isWifiEnable = SIGNAL_WIFI_UNENABLE;
//                        break;
//                    case WifiManager.WIFI_STATE_ENABLED:
//                        Log.d("WIFI状态", "打开"+wifiState);
//                        isWifiEnable = SIGNAL_WIFI_ENABLE;
//                        break;
//                    default:
//                        break;
//                }
//            }
//            if (TextUtils.equals(action, ConnectivityManager.CONNECTIVITY_ACTION)){//网络状态变化
//                Log.d(TAG, "AutoNetworkUtil.NetReceiver.onReceive CONNECTIVITY_ACTION");
//                SdkNetworkUtil.notifyBLNetworkChange(isNetworkConnected());
//            }else if(TextUtils.equals(action, WifiManager.RSSI_CHANGED_ACTION)){//wifi信号强度变化
//                Log.d(TAG, "AutoNetworkUtil.NetReceiver.onReceive RSSI_CHANGED_ACTION");
//            }
//        }
//
//    }
//
//    /**
//     * 通知BL层网络变化
//     * 退出程序后，网络监听并没有移除，导致去调用BL方法有可能出现UnsatisfiedLinkError
//     * 无网络0
//     * @param connected 具体参考BL的NetworkStatus，之前只区分是否有网，和BL沟通，可以把所有有网状态当成wifi状态处理
//     *
//     */
//    public static void notifyBLNetworkChange(boolean connected){
//        try{
//            Log.d(TAG,"notifyBLNetworkChange:"+connected);
//            NetworkInfo networkInfo = SdkNetworkUtil.getActiveNetworkInfo();
//            int networkstate;
//            if(networkInfo != null && networkInfo.isConnected()){
//                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                    networkstate = NetworkStatus.NetworkStatusWiFi;
//                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
//                    networkstate = NetworkStatus.NetworkStatus4G;
//                } else {
//                    networkstate = NetworkStatus.NetworkStatusOther;
//                }
//            }else{
//                networkstate = NetworkStatus.NetworkStatusNotReachable;
//            }
//            Log.d(TAG,"notifyBLNetwork,net={?}"+networkstate);
//        }catch (Throwable e){
//            Log.d("Throwable notifyBLNetworkChange:{?}", e.getMessage());
//        }
//    }
}
