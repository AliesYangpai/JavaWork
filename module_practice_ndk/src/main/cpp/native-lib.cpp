#include <jni.h>
#include <string>
#include <android/log.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_alie_modulepracticendk_HolderJni_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_HolderJni_do_1test_101_1print_1data(JNIEnv *env,
                                                                    jobject thiz,
                                                                    jshort age, jstring name,
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
Java_com_alie_modulepracticendk_HolderJni_do_1test_102_1print_1data(JNIEnv *env, jobject thiz,
                                                                    jobjectArray array) {
    jsize size = env->GetArrayLength(array);
    for (int i = 0; i < size; i++) {
        jstring str = static_cast<jstring>(env->GetObjectArrayElement(array, i));
        const char *p_value = env->GetStringUTFChars(str, NULL);
        __android_log_print(ANDROID_LOG_DEBUG, "XXX", "===jniData native array[%d] = %s", i,
                            p_value);
        env->ReleaseStringUTFChars(str, p_value);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_HolderJni_do_1test_103_1print_1data(JNIEnv *env, jobject thiz,
                                                                    jstring content,
                                                                    jobject student) {
    const char *p_content = env->GetStringUTFChars(content, 0);
    __android_log_print(ANDROID_LOG_DEBUG, "xxx", "===jniData native content = %s", p_content);
    env->ReleaseStringUTFChars(content, p_content);
    /**
     * 类似于java中的反射调用
     */
    // 1.获取class
    jclass stuClz = env->GetObjectClass(student);
    // 2.通过class 获取方法 (class,方法名，签名)
    jmethodID getAge = env->GetMethodID(stuClz, "getAge", "()S");
    jmethodID getName = env->GetMethodID(stuClz, "getName", "()Ljava/lang/String;");
    jmethodID setAge = env->GetMethodID(stuClz, "setAge", "(S)V");
    jmethodID setName = env->GetMethodID(stuClz, "setName", "(Ljava/lang/String;)V");
    // 3.使用env->CallShortMethod 调用方法
    jshort age = env->CallShortMethod(student, getAge);
    jstring jstringName = static_cast<jstring>(env->CallObjectMethod(student, getName));
    const char *p_value = env->GetStringUTFChars(jstringName, 0);
    __android_log_print(ANDROID_LOG_DEBUG, "xxx",
                        "===jniData native student.age = %d student.name = %s", age, p_value);
    env->ReleaseStringUTFChars(jstringName, p_value);

    //=======set方法
    env->CallVoidMethod(student,setAge,32);
    jstring nameParam = env->NewStringUTF("大西瓜");
    env->CallVoidMethod(student,setName,nameParam); // 此处不能传入String
    //释放掉局部引用：
    env->DeleteLocalRef(nameParam);

}