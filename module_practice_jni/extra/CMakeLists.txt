# CMakeList.txt: CMakeProject4_Jni_first 的 CMake 项目，在此处包括源代码并定义
# 项目特定的逻辑。
#
cmake_minimum_required (VERSION 3.8)

# 将源代码添加到此项目的可执行文件。add_executable 的作用时编译出可执行文件
# add_executable (CMakeProject4_Jni_first "CMakeProject4_Jni_first.cpp" "CMakeProject4_Jni_first.h")




# =====================  分割线 =====================

# 加入jdk中头文件查找目录与平台信息
include_directories("D:/Address/Java/jdk1.8.0_261/include")  
include_directories("D:/Address/Java/jdk1.8.0_261/include/win32")

# 说明： add_executable 目的是编写一个可执行文件，但是我们需要的是编写一个让java使用的动态so库 使用 add_library 
# add_library（libname 库类型静态还是动态 需要的原文件）;参数不用带引号,中间无“，”
add_library(alie_first SHARED CMakeProject4_Jni_first.cpp)  

# TODO: 如有需要，请添加测试并安装目标。