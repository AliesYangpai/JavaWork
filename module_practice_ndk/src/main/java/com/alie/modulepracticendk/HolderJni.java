package com.alie.modulepracticendk;

import android.util.Log;

public class HolderJni {
    private static final String TAG = "HolderJni";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
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
     * do_Test_02 打印String数组
     * @param array
     */
    private native void do_test_02_print_data(String[] array);

    /**
     * do_Test_03 print Student data
     * @param content
     * @param student
     */
    private native void do_test_03_print_data(String content,Student student);

    /**
     * first use
     */
    public void doFirstIn() {
        System.out.println("===doFirstIn");
        String value = stringFromJNI();
        System.out.println("value = "+value);
    }

    /**
     * print short String int[]
     */
    public void doTest01() {
        System.out.println("===doTest01");
        short age = 55;
        String name = "alie";
        int[] array = {1,2,3,4,5,6};
        do_test_01_print_data(age,name,array);
    }

    /**
     * print string[]
     */
    public void doTest02() {
        System.out.println("===doTest02");
        String[] array = {"Jordan","Brian","Iverson","Carter"};
        do_test_02_print_data(array);
    }

    /**
     * do_Test_03 打印String数组
     */
    public void doTest03() {
        System.out.println("===doTest03");
        do_test_03_print_data("Study hard",new Student("alie", (short) 109));
    }


}
