package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.service.RegionService;
import com.pfq.deal.trans_listing.service.intfc.IRegion;
import com.pfq.deal.trans_listing.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by steven on 2017/11/16.
 */
public class RegionImpl implements ConstraintValidator<IRegion,Integer> {


    @Override
    public void initialize(IRegion iRegion) {

    }

    @Override
    public boolean isValid(Integer regionId, ConstraintValidatorContext constraintValidatorContext) {

        if (regionId==null)
            return false;

        RegionService regionService= SpringContextUtils.getBean(RegionService.class);

        return StringUtils.isEmpty(regionService.select(regionId).getRegionName());
    }
}
