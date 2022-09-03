package com.alie.modulepracticeaopannotation.aop;

import com.alie.modulepracticeaopannotation.TestConst;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class WorkAspect {
    public static final String EXECUTION_WORK = "execution(@com.alie.modulepracticeaopannotation.aop.InterpolateWork * *(..))";

    @Pointcut(EXECUTION_WORK)
    public void pointCut() {
    }

    @Before("pointCut()")
    public void methodBeforeWork(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        String methodParams = generateMethodArgsString(joinPoint.getArgs());
        boolean isAnnotationRet = method.isAnnotationPresent(InterpolateWork.class);
        if (isAnnotationRet) {
            String annotationValue = generateAnnotationValue(method);
            TestConst.showLog("methodBeforeWork",
                    "isAnnotationRet " + isAnnotationRet
                            + "annotationValue" + annotationValue
                            + "methodName:" + name
                            + " methodParams:" + methodParams);
        } else {
            TestConst.showLog("methodBeforeWork",
                    "isAnnotationRet " + isAnnotationRet
                            + "methodName:" + name
                            + " methodParams:" + methodParams);
        }
    }


//    @Around("pointCut()")
//    public void methodAroundWork(ProceedingJoinPoint proceedingJoinPoint) {
//
//    }

    @After("pointCut()")
    public void methodAfterWork(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String name = method.getName();
        String methodParams = generateMethodArgsString(joinPoint.getArgs());
        boolean isAnnotationRet = method.isAnnotationPresent(InterpolateWork.class);
        if (isAnnotationRet) {
            String annotationValue = generateAnnotationValue(method);
            TestConst.showLog("methodAfterWork",
                    "isAnnotationRet " + isAnnotationRet
                            + " annotationValue" + annotationValue
                            + " methodName:" + name
                            + " methodParams:" + methodParams);
        } else {
            TestConst.showLog("methodAfterWork",
                    "isAnnotationRet " + isAnnotationRet
                            + " methodName:" + name
                            + " methodParams:" + methodParams);
        }
    }

    private String generateMethodArgsString(Object[] args) {
        StringBuilder tip = new StringBuilder();
        for (int i = 0; i < args.length; i++) {

            tip.append(args[i]).append("_");
        }
        return tip.toString();
    }

    private String generateAnnotationValue(Method method) {
        InterpolateWork annotation = method.getAnnotation(InterpolateWork.class);
        String value = null;
        if (annotation != null) {
            value = annotation.value();
        }
        return value;
    }
}

