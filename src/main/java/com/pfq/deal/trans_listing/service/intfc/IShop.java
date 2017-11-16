package com.pfq.deal.trans_listing.service.intfc;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by steven on 2017/11/16.
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {com.pfq.deal.trans_listing.service.intfc.impl.ShopImpl.class}
)
public @interface IShop {
}
