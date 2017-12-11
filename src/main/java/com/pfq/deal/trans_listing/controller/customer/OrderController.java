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

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> orderExtends(@PathVariable String orderNo,@RequestBody InputOrder inputVo){

        orderService.orderExtends(inputVo,orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{siteNo}",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> lastOrderInfoBySiteNo(@PathVariable String siteNo){

        String orderNo=orderDao.findOrderNoBySiteNo(siteNo);

        OrderTotalInfo orderTotalInfo=orderService.orderInfo(orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderTotalInfo);
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> orderInfo(@PathVariable String orderNo){

        OrderTotalInfo orderTotalInfo=orderService.orderInfo(orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderTotalInfo);
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> reduceOrder(@PathVariable String orderNo,UpdateOrder orderInfo){

        orderInfo.setOrderNo(orderNo);

        orderService.reduceOrder(orderInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}/{totalId}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> deleteOrder(@PathVariable String orderNo,@PathVariable Long totalId){



        orderService.deleteOrder(orderNo,totalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> deleteOrderAll(@PathVariable String orderNo,@PathVariable Long totalId){

        orderService.deleteOrder(orderNo,null);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> confirmOrder(@PathVariable String orderNo,Long totalId){

        orderService.confirmOrder(orderNo,totalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/order/{orderNo}/ceculator",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> ceculatorOrder(@PathVariable String orderNo){

        CeculatorOrderInfo info=orderService.ceculatorOrder(orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(info);
    }

    @RequestMapping(value = "/order/{orderNo}/pay",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> payOrder(@PathVariable String orderNo){

        PayOrderRes res=orderService.payOrder(orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @RequestMapping(value = "/order/{orderNo}/confirmPayment",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> confirmPayment(@PathVariable String orderNo){

       orderService.confirmPayment(orderNo);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

}
