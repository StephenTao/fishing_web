package com.augmentum.common.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.augmentum.fishing.Context;

/**
 * This is the ApplicationContext for spring. Developer can get the bean from container.
 *
 *
 */
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanId) {
        ApplicationContext applicationContext = getApplicationContext();
        return applicationContext.getBean(beanId);
    }

    public static String getMessage(String key) {
        if (StringUtil.isEmpty(key)) {
            return "";
        } else {
            Locale locale = Context.getContext().getLocale();
            return applicationContext.getMessage(key, null, locale);
        }
    }

    public static String getMessage(String key, String... args) {
        if (StringUtil.isEmpty(key)) {
            return "";
        } else {
            Locale locale = Context.getContext().getLocale();
            return applicationContext.getMessage(key, args, locale);
        }
    }

}
