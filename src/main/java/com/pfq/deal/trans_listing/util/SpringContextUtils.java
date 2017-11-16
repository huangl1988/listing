package com.pfq.deal.trans_listing.util;

import org.springframework.context.ApplicationContext;


/**
 * Created by steven on 2017/11/16.
 */
public class SpringContextUtils
{
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context)
    {
        applicationContext = context;
    }

    public static <T> T getBean(Class clz)
    {
        return (T) applicationContext.getBean(clz);
    }
}
