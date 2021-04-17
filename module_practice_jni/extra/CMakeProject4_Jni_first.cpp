// CMakeProject4_Jni_first.cpp: 定义应用程序的入口点。
// 引入jni头文件，此头文件来自java jdk
#include <jni.h>
extern "C"

//==========以上都是环境配置，最关键的来
// c中的编写格式
// 参数
JNIEXPORT void JNICALL JAVA_com_alie_modulepracticejni_ExampleUnitTest_doTestJni(
	JNIEnv* env,
	jobject instance,
	jstring name,
	jint num
	) 
{
    const char* p_name = env->GetStringUTFChars(name, 0);
	printf("====JAVA_com_alie_libpracticeapijni_MyClass_doTestJni %d, %s", num, p_name);
}