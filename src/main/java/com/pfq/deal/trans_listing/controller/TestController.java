package com.pfq.deal.trans_listing.controller;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.service.TestService;
import com.pfq.deal.trans_listing.service.intfc.MyPathavalibe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2017/11/26.
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test/{id}")
    public ResponseEntity<IBaseOutput> test(@MyPathavalibe(clz = TestService.class) String id){
        System.out.println(id+"controller");
        return ResponseEntity.ok(new BaseOutput());
    }


}
