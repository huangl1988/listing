package com.pfq.deal.trans_listing.service.intfc.impl;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.service.RegionService;
import com.pfq.deal.trans_listing.service.intfc.IRegion;
import com.pfq.deal.trans_listing.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by steven on 2017/11/16.
 */
@Aspect
@Component
public class RegionImpl {


    @Pointcut(value = "@annotation(com.pfq.deal.trans_listing.service.intfc.IRegion)")
    private void pointcut() {

    }
    @Around(value = "pointcut() && @annotation(myLog)")
    public Object around(ProceedingJoinPoint point, IRegion myLog) throws Throwable{

        if(isValid(Integer.parseInt(myLog.regionId()))){
            return point.proceed();
        }else
            throw new RuntimeException(myLog.message());
    }

    public boolean isValid(Integer regionId) {

        if (regionId==null)
            return false;

        RegionService regionService= SpringContextUtils.getBean(RegionService.class);

        if(StringUtils.isNotEmpty(regionService.select(regionId).getRegionName())){
            return true;
        }


        return false;
    }
}
