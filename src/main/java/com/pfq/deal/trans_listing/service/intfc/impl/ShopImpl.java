package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.intfc.IRegion;
import com.pfq.deal.trans_listing.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by steven on 2017/11/16.
 */
public class ShopImpl implements ConstraintValidator<IRegion,Integer> {
    @Override
    public void initialize(IRegion iRegion) {

    }

    @Override
    public boolean isValid(Integer shopId, ConstraintValidatorContext constraintValidatorContext) {

        if(shopId==null)
            return false;
        ShopService shopService=SpringContextUtils.getBean(ShopService.class);

        return StringUtils.isEmpty(shopService.selectOne(shopId).getShopCode());
    }
}
