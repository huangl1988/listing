package com.pfq.deal.trans_listing.controller.shopper;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2017/12/12.
 */
@RestController
@RequestMapping("/shopper")
public class ShopperController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/{orderNo}/confirmPayment",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> confirmPayment(@PathVariable String orderNo){

        orderService.confirmPayment(orderNo);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }



}
