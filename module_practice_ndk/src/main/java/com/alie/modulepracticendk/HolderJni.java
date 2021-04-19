package com.alie.modulepracticendk;

import android.util.Log;

public class HolderJni {
    private static final String TAG = "HolderJni";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    /**
     * first use
     */
    public void doTest01() {
        System.out.println("===doTest01");
        String value = stringFromJNI();
        System.out.println("value = "+value);
    }

    public void doTest02() {
        System.out.println("===doTest02");
        short age = 55;
        String name = "alie";
        int[] array = {1,2,3,4,5,6};
        do_test_01_print_data(age,name,array);
    }

    public void doTest03() {
        System.out.println("===doTest03");
        String[] array = {"Jordan","Brian","Iverson","Carter"};
        do_test_02_print_data(array);
    }



    /**
     * =========================== native method ==================================
     */
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private native String stringFromJNI();

    /**
     * do_Test_01 打印参数
     * @param age
     * @param name
     * @param array
     */
    private native void do_test_01_print_data(short age,String name,int[] array);

    /**
     * do_Test_01 打印String数组
     * @param array
     */
    private native void do_test_02_print_data(String[] array);


}
