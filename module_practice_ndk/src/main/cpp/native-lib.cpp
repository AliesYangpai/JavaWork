#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_alie_modulepracticendk_NativeRaw_doHelloWorld(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Hello ndk");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printData__I(JNIEnv *env, jobject thiz, jint age) {
    __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native name:%d", age);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printData__Ljava_lang_String_2(JNIEnv *env, jobject thiz,
                                                                         jstring name) {
    const char *p_name = env->GetStringUTFChars(name, 0);
    __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native name:%s", p_name);
    env->ReleaseStringUTFChars(name, p_name);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printData___3I(JNIEnv *env, jobject thiz,
                                                         jintArray int_array) {
    jint *p_array = env->GetIntArrayElements(int_array, 0);
    jsize length = env->GetArrayLength(int_array);
    for (jint i = 0; i < length; ++i) {
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp",
                            "===jniData native int_array[%d] = %d", i, *(p_array + i));
    }
    /**
     * 参数3：MODE
     * 0:刷新java数组 并释放C/C++数组
     * 1:JNI_COMMIT -> 只刷新java数组，不释放
     * 2:JNI_ABORT -> java c++ 全部释放
     */
    env->ReleaseIntArrayElements(int_array, p_array, 0);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printData___3Ljava_lang_String_2(JNIEnv *env,
                                                                           jobject thiz,
                                                                           jobjectArray string_array) {
    jsize length = env->GetArrayLength(string_array);
    for (jint i = 0; i < length; ++i) {
        jstring value = static_cast<jstring>(env->GetObjectArrayElement(string_array, i));
        const char *p_value = env->GetStringUTFChars(value, 0);
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native name:%s",
                            p_value);
        env->ReleaseStringUTFChars(value,p_value);
    }
}