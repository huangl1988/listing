package com.pfq.deal.trans_listing.service;

//import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.stereotype.Service;

/**
 * Created by steven on 2017/11/26.
 */
@Service
public class TestService implements IBaseService {
    @Override
    public boolean isExist(String id) {
        System.out.println(id+"service");
        return false;
    }
}
