package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.service.StyleCookingService;
import com.pfq.deal.trans_listing.service.intfc.IStyleCooking;
import com.pfq.deal.trans_listing.util.SpringContextUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by steven on 2017/11/18.
 */
public class StyleCookingImpl implements ConstraintValidator<IStyleCooking,Integer> {
    @Override
    public void initialize(IStyleCooking iStyleCooking) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

        StyleCookingService styleCookingService= SpringContextUtils.getBean(StyleCookingService.class);
        return styleCookingService.select(id).getId()==null;
    }
}
