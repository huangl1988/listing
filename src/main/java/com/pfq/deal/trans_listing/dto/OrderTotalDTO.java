package com.pfq.deal.trans_listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by steven on 2017/12/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTotalDTO {

    private String siteNo;

    private BigDecimal totalPrice;

    private int orderNum;

    private String orderTime;

    private Long id;

    private String orderNo;

    private byte onLineFlag;

    private String address;

    private String point;

    private int confirmFlag;

    private Date confirmDate;

    private Date inserttime;

}
