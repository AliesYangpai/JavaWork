package com.alie.modulepracticeaop.aop;

import com.alie.modulepracticeaop.TestConst;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 切点的语法：
 * execution(* com.alie.modulepracticeaop.MainPresenter.*(..))
 * 1.第1个参数代表返回值，这里用 * 是通配符，表示可以是任意类型。
 * 2.第二个com.alie.modulepracticeaop.MainPresenter.* 代表目标类的全路径
 * 其中 * 代表的是这个类中的任意方法，
 * 3.*(..)中..表示任意类型、任意个数的参数
 */
@Aspect
public class WorkAspect {
    private static final String TAG = "WorkAspect";

    private static final String EXECUTION = "execution(* com.alie.modulepracticeaop.MainPresenter.*(..))";


    @Pointcut(EXECUTION)
    public void cutPoint(JoinPoint joinPoint) {
    }

    @Before("cutPoint()")
    public void cutPointBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        if (args == null || args.length == 0) {
            TestConst.showLog("cutPointBefore", methodName + " ");
            return;
        }
        String tip = generateString(args);
        TestConst.showLog("cutPointBefore", methodName + " " + tip);
    }


    private String generateString(Object[] args) {
        StringBuilder tip = new StringBuilder();
        for (int i = 0; i < args.length; i++) {

            tip.append(args[i]).append("_");
        }
        return tip.toString();
    }
}
