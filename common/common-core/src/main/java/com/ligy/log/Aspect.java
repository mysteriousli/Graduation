package com.ligy.log;

import com.ligy.annotation.Log;
import com.ligy.util.EsUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author lgy
 */
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
    @Resource
    private EsUtils esUtil;
    @Pointcut("@annotation(com.ligy.annotation.Log)")
    public void logPointCut(){

    }
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult){
        handleLog(joinPoint, jsonResult);
    }
    void handleLog(final JoinPoint joinPoint,Object jsonResult){
        // 获得注解
        Log controllerLog = getAnnotationLog(joinPoint);
        Date date = new Date();
        String index = "log_" + date.getTime();
        esUtil.save(controllerLog.toString(), index);
    }
    Log getAnnotationLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
