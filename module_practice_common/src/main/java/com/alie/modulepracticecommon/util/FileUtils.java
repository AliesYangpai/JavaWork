package com.alie.modulepracticecommon.util;

import android.util.Log;

import java.io.File;


/**
 * Created by AutoSdk.
 */
public class FileUtils {

    public static final String TAG = FileUtils.class.getSimpleName();

    /**
     * 创建文件夹,若已存在则不重新创建
     *
     * @param dirpath 路径
     */
    public static boolean createDIR(String dirpath) {
        return createDIR(dirpath, false);
    }

    /**
     * 创建文件夹
     * 若文件存在,但非目录,则删除重建
     * 参考 {@link #createDIR(File, boolean)}
     */
    public static boolean createDIR(String dirpath, boolean forceRecreate) {
        return createDIR(new File(dirpath), forceRecreate);
    }

    /**
     * 创建文件夹
     * 若文件存在,但非目录,则删除重建
     *
     * @param targetFile    要创建的目标目录文件
     * @param forceRecreate 若目录已存在,是否要强制重新闯进(删除后,新建)
     * @return 是否创建成功
     */
    public static boolean createDIR(File targetFile, boolean forceRecreate) {
        if (targetFile == null) {
            return false;
        }

        if (targetFile.exists()) { // 存在同名文件
            boolean isDir = targetFile.isDirectory();
            if (!isDir) { // 非目录,删除以便创建目录
                boolean result = targetFile.delete();
                Log.d(TAG, "dirPath:" + targetFile.getAbsolutePath() + " is a file, delete it, result=" + result);
            } else if (forceRecreate) { // 强制删除目录
                deleteDir(targetFile);
            } else { // 目录存在
                return true;
            }
        }

        return targetFile.mkdirs();
    }

    /**
     * 删除指定目录
     * 若存在同名非目录文件,则不处理
     */
    public static boolean deleteDir(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return true;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {// 删除所有文件
                file.delete();
            } else if (file.isDirectory()) { // 递归删除子目录
                deleteDir(file);
            }
        }
        return dir.delete();// 删除空目录本身
    }
}
