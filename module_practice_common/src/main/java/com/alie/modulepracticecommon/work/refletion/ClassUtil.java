package com.alie.modulepracticecommon.work.refletion;

/**
 * jvm always packaging class into Class it packets by type.
 * So if we can get Class,the class will do
 */
public class ClassUtil {

    private static void showData(String tag, String data) {
        System.out.println(tag + " " + data);
    }

    public static void showClassInfo1(String packageName) {
        try {
            Class aClass = Class.forName(packageName);
            showData("showClassInfo1",aClass.getName());
            showData("showClassInfo1",aClass.getSimpleName());
            showData("showClassInfo1",aClass.getPackage().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            showData("showClassInfo1", "classNotFind");
        }
    }

    public static <T> void showClassInfo2(T t) {
        Class aClass = t.getClass();
        showData("showClassInfo2", aClass.getName());
        showData("showClassInfo2", aClass.getSimpleName());
        showData("showClassInfo2", aClass.getPackage().getName());
    }

}
