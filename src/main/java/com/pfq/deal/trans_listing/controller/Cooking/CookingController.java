package com.pfq.deal.trans_listing.controller.Cooking;

import com.pfq.deal.trans_listing.bean.input.order.CookerConfirms;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by steven on 2017/12/11.
 */
@RestController
@RequestMapping("shops/{shopId}/cookers/")
public class CookingController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public IBaseOutput getOrderList(@PathVariable Long shopId){

        RetCommodyList list = orderService.getUnCookingList(shopId);
        list.doSucc();
        return list;
    }

    @RequestMapping(value = "/confirms",method = RequestMethod.POST)
    public IBaseOutput confirmCooking(@RequestBody  CookerConfirms cookerInfo){

        orderService.confirmCooking(cookerInfo);

        return new BaseOutput();
    }





}
