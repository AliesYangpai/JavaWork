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
        env->ReleaseStringUTFChars(value, p_value);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printData__Lcom_alie_modulepracticendk_bean_Cpu_2(
        JNIEnv *env, jobject thiz, jobject cpu) {
    /**
     * （与反射很像）
     * 1.获取class
     * 2.获取method
     * 3.env->Callxxxx
     */
    jclass clazzCpu = env->GetObjectClass(cpu);
    jmethodID getPrice = env->GetMethodID(clazzCpu, "getPrice", "()F");
    jmethodID getName = env->GetMethodID(clazzCpu, "getName", "()Ljava/lang/String;");
    jfloat price = env->CallFloatMethod(cpu, getPrice);
    __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native Cpu.price:%f",
                        price);
    jstring name = static_cast<jstring>(env->CallObjectMethod(cpu, getName));
    const char *p_name = env->GetStringUTFChars(name, 0);
    __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native Cpu.name:%s",
                        p_name);
    env->ReleaseStringUTFChars(name, p_name);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printDataObjStaticMethod__Lcom_alie_modulepracticendk_bean_Cpu_2(
        JNIEnv *env, jobject thiz, jobject cpu) {
    jclass clazzCpu = env->GetObjectClass(cpu);
    jmethodID showInfo = env->GetStaticMethodID(clazzCpu, "showInfo", "()V");
    env->CallStaticVoidMethod(clazzCpu, showInfo);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printDataObjField__Lcom_alie_modulepracticendk_bean_Cpu_2(
        JNIEnv *env, jobject thiz, jobject cpu) {
    jclass clazzCpu = env->GetObjectClass(cpu);

    /**
     * 获取 price：float 属性并修改
     */
    jfieldID priceFieldId = env->GetFieldID(clazzCpu, "mPrice", "F");
    jfloat price = env->GetFloatField(cpu, priceFieldId);
    __android_log_print(ANDROID_LOG_DEBUG, "native-lib.cpp", "===jniData native field price:%f",
                        price);
    jfloat value = 87.8F;
    env->SetFloatField(cpu, priceFieldId, value);

    /**
     * 获取name:String 属性并修改
     */
    jfieldID nameFieldId = env->GetFieldID(clazzCpu, "mName", "Ljava/lang/String;");
    jstring newName = env->NewStringUTF("联想电脑");
    env->SetObjectField(cpu, nameFieldId, newName);
    env->DeleteLocalRef(newName); // 释放局部变量
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateCpu(JNIEnv *env, jobject thiz, jstring name,
                                                      jfloat price) {

    /**
     * 1.获取类：FindClass
     * 2.获取构造方法 （）【备注】构造方法的方法名 永远都是<init>
     * 3.调用构造方法 NewObject
     */
    const char *p_class_cpu = "com/alie/modulepracticendk/bean/Cpu";
    jclass clazzCpu = env->FindClass(p_class_cpu);
    // 有参构造
    jmethodID constructMethodId = env->GetMethodID(clazzCpu, "<init>", "(Ljava/lang/String;F)V");

    const char *p_name = "小米";
    jstring targetName = env->NewStringUTF(p_name);
    jfloat targetPrice = 150.5F;
    return env->NewObject(clazzCpu, constructMethodId, targetName, targetPrice);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateGpu(JNIEnv *env, jobject thiz, jstring name,
                                                      jfloat price) {
    const char *p_package_gpu = "com/alie/modulepracticendk/bean/Gpu";
    jclass clazzGpu = env->FindClass(p_package_gpu);
    jmethodID constructCpu = env->GetMethodID(clazzGpu, "<init>", "(Ljava/lang/String;F)V");
    return env->NewObject(clazzGpu, constructCpu, name, price);

}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateMemory(JNIEnv *env, jobject thiz, jstring name,
                                                         jfloat price) {
    const char *p_package_memory = "com/alie/modulepracticendk/bean/Memory";
    jclass clazzMemory = env->FindClass(p_package_memory);
    jmethodID constructMemory = env->GetMethodID(clazzMemory, "<init>", "(Ljava/lang/String;F)V");
    return env->NewObject(clazzMemory, constructMemory, name, price);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateComputer(JNIEnv *env, jobject thiz, jstring name,
                                                           jobject cpu, jobject gpu,
                                                           jobject memory) {
    const char *p_package_computer = "com/alie/modulepracticendk/bean/Computer";
    jclass clazzComputer = env->FindClass(p_package_computer);
    jmethodID constructComputer = env->GetMethodID(clazzComputer, "<init>",
                                                   "(Ljava/lang/String;Lcom/alie/modulepracticendk/bean/Cpu;Lcom/alie/modulepracticendk/bean/Gpu;Lcom/alie/modulepracticendk/bean/Memory;)V");
   return env->NewObject(clazzComputer,constructComputer,name,cpu,gpu,memory);
}