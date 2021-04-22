package com.alie.modulepracticendk;

public class NativeRaw {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    protected native String doHelloWorld();
    protected native void printData(int age);
    protected native void printData(String name);
    protected native void printData(int[] intArray);
    protected native void printData(String[] stringArray);
}
