package com.pfq.deal.trans_listing.controller.customer;

import com.pfq.deal.trans_listing.bean.input.order.CeculatorOrderInfo;
import com.pfq.deal.trans_listing.bean.input.order.InputOrder;
import com.pfq.deal.trans_listing.bean.input.order.UpdateOrder;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.bean.output.Order.OrderTotalInfo;
import com.pfq.deal.trans_listing.bean.output.Order.PayOrderRes;
import com.pfq.deal.trans_listing.dao.IOrderDao;
import com.pfq.deal.trans_listing.service.OrderService;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.intfc.MyPathavalibe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by steven on 2017/12/4.
 */
@RestController
@Slf4j
@RequestMapping("")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    IOrderDao orderDao;

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> order(@RequestBody InputOrder inputVo){

        orderService.createOrder(inputVo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/extends/{orderNo}",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> orderExtends(@PathVariable String orderNo,@RequestBody InputOrder inputVo){

        orderService.orderExtends(inputVo,orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{shopId}/{siteNo}",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> lastOrderInfoBySiteNo(@PathVariable String siteNo,@MyPathavalibe(clz=ShopService.class) Integer shopId){

        String orderNo=orderDao.findOrderNoBySiteNo(siteNo,shopId);
        
        Integer status = orderDao.getPayStatus(orderNo);
        if(status!=null&&status>=20)
        	return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
        try {
        	 OrderTotalInfo orderTotalInfo=orderService.orderInfo(orderNo);
        	 return ResponseEntity.status(HttpStatus.OK).body(orderTotalInfo);
		} catch (Exception e) {
			log.error("error",e);
			throw e;
		}
        

        
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> orderInfo(@PathVariable String orderNo){

        OrderTotalInfo orderTotalInfo=orderService.orderInfo(orderNo);

        return ResponseEntity.status(HttpStatus.OK).body(orderTotalInfo);
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> reduceOrder(@PathVariable String orderNo,@RequestBody  UpdateOrder orderInfo){

        orderInfo.setOrderNo(orderNo);

        orderService.reduceOrder(orderInfo);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}/{totalId}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> deleteOrder(@PathVariable String orderNo,@PathVariable Long totalId){



        orderService.deleteOrder(orderNo,totalId);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> deleteOrderAll(@PathVariable String orderNo,@PathVariable Long totalId){

        orderService.deleteOrder(orderNo,null);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}/confirm",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> confirmOrder(@PathVariable String orderNo,Long totalId){

        orderService.confirmOrder(orderNo,totalId);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}/ceculator",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> ceculatorOrder(@PathVariable String orderNo){

        CeculatorOrderInfo info=orderService.ceculatorOrder(orderNo);

        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @RequestMapping(value = "/order/{orderNo}/pay",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> payOrder(@PathVariable String orderNo){

        PayOrderRes res=orderService.payOrder(orderNo);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
