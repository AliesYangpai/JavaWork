package com.alie.modulepracticecommon.util;

import android.content.Context;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.Nullable;

/**
 * Created by AutoSdk.
 */

public class AssertUtils {
    public static final String TAG = AssertUtils.class.getSimpleName();

    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String  原文件路径  如：/aa
     * @param newPath String  复制后路径  如：xx:/bb/cc
     */
    public static void copyFilesAssets(Context context, String oldPath, String newPath) {
        try {
            String[] fileNames = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            Log.d(TAG, "[xzc] copyFilesAssets=" + fileNames.length);
            if (fileNames != null && fileNames.length > 0) {//如果是目录
                File file = new File(newPath);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    Log.d(TAG, "[xzc] copyFilesAssets11=" + fileName);
                    copyFilesAssets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {//如果是文件
                InputStream is = context.getAssets().open(oldPath);
                Log.d(TAG, "[xzc] copyFilesAssets=" + is);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {//循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证指定的assets文件存在,并且包含特定文本
     *
     * @param assertFilePath 文件相对路径
     * @param expectText     需要包含的文本(全部满足才算ok), 若为空,则表示仅判断文件是否存在
     * @return true asset文件存在并包含给定的文本, false-asset文件不存在或不包含给定的文本
     */
    public static boolean containInfo(Context context, String assertFilePath, @Nullable String... expectText) {
        if (context == null) {
            return false;
        }

        int expectArrLength = expectText == null ? 0 : expectText.length;
        boolean[] resultArr = new boolean[expectArrLength];
        boolean finalResult = false;

        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(assertFilePath);

            if (expectArrLength == 0) { // 无异常则文件存在,直接返回
                return true;
            }

            byte[] buff = new byte[1024];
            int len = inputStream.read(buff);
            while (len >= 0) {
                String line = new String(buff);
                finalResult = true;
                for (int i = 0; i < expectArrLength; i++) {
                    if (!resultArr[i]) {
                        if (line.contains(expectText[i])) {
                            resultArr[i] = true;
                        }
                    }
                    finalResult = finalResult && resultArr[i];
                }

                if (finalResult) {
                    break;
                }
                len = inputStream.read(buff);
            }

            return finalResult;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }


    /**
     * 判断assert文件是否存在,不支持目录
     *
     * @param assertFilePath 文件相对路径
     */
    public static boolean isFileExist(Context context, String assertFilePath) {
        String text = null; // 避免编译警告, 使用了非varargs
        return containInfo(context, assertFilePath, text);
    }

    public static boolean copyAssets(Context context, File dest, String assetFile) {
        if (context == null) {
            return false;
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            if (!dest.exists()) {
                dest.getParentFile().mkdirs();
            }
            inputStream = context.getAssets().open(assetFile);
            outputStream = new FileOutputStream(dest);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buff)) >= 0) {
                outputStream.write(buff, 0, len);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
