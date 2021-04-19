#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"

JNIEXPORT jstring JNICALL
Java_com_alie_modulepracticendk_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

void showArrayData(jint *p, jsize size) {
    for (int i = 0; i < size; i++) {
        __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native array[%d] = %d", i,
                            *(p + i));
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_MainActivity_do_1test_101_1print_1data(
        JNIEnv *env,
        jobject thiz,
        jshort age,
        jstring name,
        jintArray array) {
    __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native age:%d", age);
    /**
     * isCopy
     * 1:copy to a new data in memory ,the pointer change to a new one
     * 0:not create value just using the data that point to java (from java)
     */
    const char *p_name = env->GetStringUTFChars(name, NULL);
    __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native name:%s", p_name);

    jint *p_array = env->GetIntArrayElements(array, NULL);
    jsize length = env->GetArrayLength(array);
    for (int i = 0; i < length; i++) {
        __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native array[%d] = %d", i,
                            *(p_array + i));
    }

    /**
     * ============================Release================================
     * When you env->GetXXXXX() you also have to env-> Releasexxxxx() if needed
     */
    env->ReleaseStringUTFChars(name, p_name);

    /**
     * 参数3：MODE
     * 0:刷新java数组 并释放C/C++数组
     * 1:JNI_COMMIT -> 只刷新java数组，不释放
     * 2:JNI_ABORT -> java c++ 全部释放
     */
    env->ReleaseIntArrayElements(array, p_array, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_MainActivity_do_1test_102_1print_1data(JNIEnv *env, jobject thiz,
                                                                       jobjectArray array) {
    jsize size = env->GetArrayLength(array);
    for (int i = 0; i < size; i++) {
        jstring str = static_cast<jstring>(env->GetObjectArrayElement(array, i));
        const char* p_value = env->GetStringUTFChars(str, NULL);
        __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native array[%d] = %s",i,p_value);
    }
}