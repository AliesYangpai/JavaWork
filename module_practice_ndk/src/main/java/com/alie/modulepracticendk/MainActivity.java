package com.alie.modulepracticendk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Reference to learn ：
 * https://www.jianshu.com/p/4c890e23c021
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    void initComponent() {
        findViewById(R.id.btn1).setOnClickListener(v -> {
            String content = stringFromJNI();
            Log.d(TAG,"===jniData java initComponent :"+content);
        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            short age = 55;
            String name = "alie";
            int[] array = {1,2,3,4,5,6};
            do_test_01_print_data(age,name,array);
        });
        findViewById(R.id.btn3).setOnClickListener(v -> {
            String[] array = {"Jordan","Brian","Iverson","Carter"};
            do_test_02_print_data(array);
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    /**
     * do_Test_01 打印参数
     * @param age
     * @param name
     * @param array
     */
    public native void do_test_01_print_data(short age,String name,int[] array);

    /**
     * do_Test_01 打印String数组
     * @param array
     */
    public native void do_test_02_print_data(String[] array);
}