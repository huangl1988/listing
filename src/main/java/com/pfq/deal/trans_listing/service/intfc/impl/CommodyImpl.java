package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.service.CommodyService;
import com.pfq.deal.trans_listing.service.intfc.ICommody;
import com.pfq.deal.trans_listing.service.intfc.IRegion;
import com.pfq.deal.trans_listing.util.SpringContextUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by steven on 2017/11/18.
 */
public class CommodyImpl implements ConstraintValidator<ICommody,Long> {
    @Override
    public void initialize(ICommody iCommody) {

    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        CommodyService commodyService= SpringContextUtils.getBean(CommodyService.class);

        return commodyService.findById(id).getCommodyName()==null;
    }
}
