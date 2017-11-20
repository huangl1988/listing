package com.pfq.deal.trans_listing.service.intfc;

import com.pfq.deal.trans_listing.service.intfc.impl.StyleCookingImpl;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by steven on 2017/11/18.
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StyleCookingImpl.class)
public @interface IStyleCooking {
    String message () default "style is not exist!";
}
