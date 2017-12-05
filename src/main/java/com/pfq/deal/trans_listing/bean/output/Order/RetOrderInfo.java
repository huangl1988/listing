package com.pfq.deal.trans_listing.bean.output.Order;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by steven on 2017/12/5.
 */
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class RetOrderInfo {

    private BigDecimal totalPrice=new BigDecimal("0");

    private String inserttime;

    private int totalNum;

    private List<OrderDetailsInfo> list;


}
