package com.pfq.deal.trans_listing.bean.output.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by steven on 2017/12/5.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class OrderDetailsInfo {

    private BigDecimal totalPrice;

    private BigDecimal commodyPrice;

    private Long commodyId;

    private int num;

    private String siteNo;

    private Long id;

    private String tagsName;

    private int cancelFlag;

    private String inserttime;

    private int isCooking;


}
