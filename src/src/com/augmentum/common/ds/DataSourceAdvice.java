package com.augmentum.common.ds;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private Set<String> accessMasterKeys = new HashSet<String>();

    public void setAccessMasterKeys(Set<String> accessMasterKeys) {
        this.accessMasterKeys = accessMasterKeys;
    }

    @Override
    public void afterReturning(Object arg, Method method, Object[] args, Object target) throws Throwable {

    }

    @Override
    public void before(Method method, Object[] arg, Object target) throws Throwable {

        boolean isMaster = false;
        String methodName = method.getName();
        for(String accessMasterKey : accessMasterKeys) {
            if (methodName.startsWith(accessMasterKey)) {
                isMaster = true;
                break;
            }
        }

       if(isMaster) {
           DynamicDataSourceHolder.setMaster();
       } else {
           DynamicDataSourceHolder.setSlave();
       }
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        DynamicDataSourceHolder.setSlave();
    }
}
