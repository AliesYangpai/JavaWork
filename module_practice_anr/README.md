# Practice for ANR

------

This module is a sample to create an ANR situation,in order to learn how to detect and fix ANR bug,I give a classic examle of Main Thread Sleep.


> * Create an ANR situation
> * How to explore it
> * How to detect it


------

## Create an ANR situation

In order to learn how to detect and fix bug,I give a classic examle of Main Thread Sleep.Just read the code in [module_practice_anr] to get it And the logs I had already explored in  "extra/bugreport-sirius-OPM1.171019.019-2021-02-28-13-09-54.zip"

## How to explore it

It is most important to get "trace" file from your device.All What we gona do is base on the "trace" file.The "trace" file in directory of data/anr/.In some elder OS version,there is only one anr file in that directory named "trace.txt",but in some heigh version(mine is 8.1.0),ths anr file is named "anr_*" (eg. anr_2021-02-28-12-38-13-923).

### 1. Root version

if your device has been root just follows(go):
1.To get ANR file only,just search on website(go).
2.To get All files use [adb bugreport],you well get all logs the anr info in bugreport-xxx-OPM1.171019.019-2021-02-28-13-09-54 file.(go)

### 2. Not root version

if your device not root yet just follows(go):
1.god To get All files use [adb bugreport],you well get all logs the anr info in bugreport-xxx-OPM1.171019.019-2021-02-28-13-09-54 file.

## How to detect it
It is most Importance part of solve ANR problems.I had already put the logs in "module_practice_anr/extra/bugreport-sirius-OPM1.171019.019-2021-02-28-13-09-54.zip".All right Let's do it

### 1.Keywords

try these keywords to locate your problem(go):
"VM TRACES AT LAST ANR"
"Cmd line" (your'd better have packege name to search just like "Cmd line: com.alie.modulepracticeanr")

### 2.Check them

see [here](https://blog.csdn.net/yxz329130952/article/details/50087731) to get more.

------

Thanks for those guys help(go):
[Android Developers ANR](https://developer.android.com/topic/performance/vitals/anr?hl=zh-cn)(go)
[Android ANR分析](https://blog.csdn.net/yxz329130952/article/details/50087731)(go)
[Android ANR：原理分析及解决办法](https://www.jianshu.com/p/388166988cef)(go)
[Android手机在没有/data/anr目录权限的情况下如何分析ANR](https://blog.csdn.net/xjz696/article/details/97958441)(go)

