package com.pfq.deal.trans_listing.service.intfc;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by steven on 2017/11/16.
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IRegion {
    String message () default "the region is not exist!";
    String regionId() default "";
}
