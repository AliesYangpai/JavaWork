#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>

#define PRINT_LOG(...) __android_log_print(ANDROID_LOG_DEBUG,"native-lib.cpp",__VA_ARGS__)

/**
 * ==============================线程相关测试开始==============================
 */


JavaVM *mVm;

/**
 * 调用时机：
 *         java中调用System.loadLibrary()函数时，
 *         在jni内部就会先去查找so中的JNI_OnLoad 函数并执行调用
 * 用处:
 *     jni初始化的一些操作，别入vm指针保存（可用于后面，pthread其他线程中使用vm）
 * @param vm 代表jvm虚拟机，指针（看，有了虚拟机，那么我们基本上java的一些都系都可以干了）
 * @param reserved 我也不知道这是个干啥的~
 * @return 当前jni的版本
 */
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    mVm = vm;
    return JNI_VERSION_1_6; // 这里使用2、4、6
}

struct ThreadParam1 {
    const char *p_param1;
    const char *p_param2;
};

void *thread_method01(void *args) {
    PRINT_LOG("===thread_method01");
    ThreadParam1 *param = static_cast<ThreadParam1 *>(args);
    PRINT_LOG("param1:%s,param:%s", param->p_param1, param->p_param2);
    free(param);
    param = NULL;
    return 0;
}

/**
 *  part1 创建线程并执行 (前两个是线程属性，后两个属于方法属性)
     pthread_create()
	 一共4个参数
	 param1:线程对象的指针
	 param2:线程属性设置:在c或者c++中就代表的是null
	 param3:函数指针-> 这个就是我们想要在线程中运行的函数
	 param4:函数中的参数
     pthread_create(&pid,NULL, my_task_in_pthread,&arg);
 */
void thread_common_test01(const char *p_1, const char *p_2) {
    PRINT_LOG("===thread_common_test01");
    pthread_t pid;
    ThreadParam1 *param = static_cast<ThreadParam1 *>(malloc(sizeof(ThreadParam1)));
    param->p_param1 = p_1;
    param->p_param2 = p_2;
    pthread_create(&pid, 0, thread_method01, param);
}

struct ThreadParam2 {
    jobject param_target;
};

/**
 * 2号线程方法啊
 * @param args
 * @return
 */
void *thread_method02(void *args) {

    PRINT_LOG("thread_method02");
    ThreadParam2 *param = static_cast<ThreadParam2 *>(args);
    /**
     * 从jvm中获取在当前线程中env的指针（很重要~，mVm的获取我们可以在ON_JNI_LOAD的时候获取）
     * 从jvm中构建一个属于当前线程的jni环境（env）啦
     */
    JNIEnv *env;
    jint attach_ret = mVm->AttachCurrentThread(&env, 0);
    PRINT_LOG("attach_ret:%d", attach_ret);
    if (attach_ret) {
        // 数据获取(电脑名称)
        jobject computer = param->param_target;
        jclass clzComputer = env->GetObjectClass(computer);
        jmethodID getComputerName = env->GetMethodID(clzComputer, "getName",
                                                     "()Ljava/lang/String;");
        jstring computerName = static_cast<jstring>(env->CallObjectMethod(computer,
                                                                          getComputerName));

        // 数据获取（cpu名称）
        jmethodID getComputerCpuMethodId = env->GetMethodID(clzComputer, "getCpu",
                                                            "()Lcom/alie/modulepracticendk/bean/Cpu;");
        jobject cpuObj = env->CallObjectMethod(computer, getComputerCpuMethodId);
        jclass clzCpu = env->GetObjectClass(cpuObj);
        jmethodID getNameMethodId = env->GetMethodID(clzCpu, "getName", "()Ljava/lang/String;");
        jstring cpuName = static_cast<jstring>(env->CallObjectMethod(cpuObj, getNameMethodId));
        const char *p_computer_name = env->GetStringUTFChars(computerName, 0);
        const char *p_cpu_name = env->GetStringUTFChars(cpuName, 0);
        PRINT_LOG("computerName:%s,cpuName:%s", p_computer_name, p_cpu_name);
    }
    jint detach_ret = mVm->DetachCurrentThread();
    PRINT_LOG("detach_ret:%d", detach_ret);
    delete param;
    param = NULL;
    return 0;
}

/**
 * 创建线程并执行任务,这里我们在线程中完成对数据的转化，env相关方法
 */
void thread_common_test02(jobject computer) {
    pthread_t pid;
    ThreadParam2 *threadParam2 = new ThreadParam2();
    threadParam2->param_target = computer;
    pthread_create(&pid, 0, thread_method02, threadParam2);
}

/**
 * ==============================线程相关测试结束==============================
 */
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
    return env->NewObject(clazzCpu, constructMethodId, name, price);
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
    return env->NewObject(clazzComputer, constructComputer, name, cpu, gpu, memory);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printDataThreadWork(JNIEnv *env, jobject thiz,
                                                              jobject computer) {
    jclass clzComputer = env->GetObjectClass(computer);
    jmethodID getComputerCpuMethodId = env->GetMethodID(clzComputer, "getCpu",
                                                        "()Lcom/alie/modulepracticendk/bean/Cpu;");
    jmethodID getComputerName = env->GetMethodID(clzComputer, "getName", "()Ljava/lang/String;");
    jstring computerName = static_cast<jstring>(env->CallObjectMethod(computer, getComputerName));

    jobject cpuObj = env->CallObjectMethod(computer, getComputerCpuMethodId);
    jclass clzCpu = env->GetObjectClass(cpuObj);
    jmethodID getNameMethodId = env->GetMethodID(clzCpu, "getName", "()Ljava/lang/String;");
    jstring cpuName = static_cast<jstring>(env->CallObjectMethod(cpuObj, getNameMethodId));

    const char *p_computer_name = env->GetStringUTFChars(computerName, 0);
    const char *p_cpu_name = env->GetStringUTFChars(cpuName, 0);
    thread_common_test01(p_computer_name, p_cpu_name);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_alie_modulepracticendk_NativeRaw_printDataThreadWorkVm(JNIEnv *env, jobject thiz,
                                                                jobject computer) {
    thread_common_test02(computer);
}

/**
 * ======================= Vehicle start=========================
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateCarEngine(JNIEnv *env, jobject thiz,
                                                            jstring name) {
    const char *p_package = "com/alie/modulepracticendk/bean/car/CarEngine";
    jclass classCarEngine =  env->FindClass(p_package);
    jmethodID  constructCarEngine = env->GetMethodID(classCarEngine,"<init>", "(Ljava/lang/String;)V");
   return env->NewObject(classCarEngine,constructCarEngine,name);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateCarFrame(JNIEnv *env, jobject thiz,
                                                           jstring name) {
    const char* p_package = "com/alie/modulepracticendk/bean/car/CarFrame";
   jclass classCarFrame =  env->FindClass(p_package);
   jmethodID  constructCarFrame = env->GetMethodID(classCarFrame,"<init>", "(Ljava/lang/String;)V");
    return env->NewObject(classCarFrame,constructCarFrame,name);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateCarWheel(JNIEnv *env, jobject thiz,
                                                           jstring name) {

    const char* p_package = "com/alie/modulepracticendk/bean/car/CarWheel";
    jclass classCarWheel =  env->FindClass(p_package);
   jmethodID constructCarWheel =  env->GetMethodID(classCarWheel,"<init>", "(Ljava/lang/String;)V");
    return env->NewObject(classCarWheel,constructCarWheel,name);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateVehicle__Ljava_lang_String_2(JNIEnv *env,
                                                                               jobject thiz,
                                                                               jstring name) {

}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_alie_modulepracticendk_NativeRaw_generateVehicle__Ljava_lang_String_2Lcom_alie_modulepracticendk_bean_car_CarEngine_2Lcom_alie_modulepracticendk_bean_car_CarFrame_2Lcom_alie_modulepracticendk_bean_car_CarWheel_2(
        JNIEnv *env, jobject thiz, jstring name, jobject car_engine, jobject car_frame,
        jobject car_wheel) {
    const char* p_package = "com/alie/modulepracticendk/bean/car/Vehicle";
    jclass  classVehicle = env->FindClass(p_package);
    jmethodID constructVehicle =  env->GetMethodID(classVehicle,"<init>", "(Ljava/lang/String;Lcom/alie/modulepracticendk/bean/car/CarEngine;Lcom/alie/modulepracticendk/bean/car/CarFrame;Lcom/alie/modulepracticendk/bean/car/CarWheel;)V");
    return env->NewObject(classVehicle,constructVehicle,name,car_engine,car_frame,car_wheel);
}