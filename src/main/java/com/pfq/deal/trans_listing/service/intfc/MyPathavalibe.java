package com.pfq.deal.trans_listing.service.intfc;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by steven on 2017/11/26.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyPathavalibe {

    Class<?> clz() default  Object.class;

    String message () default " not exist";

    String value() default "";
}
