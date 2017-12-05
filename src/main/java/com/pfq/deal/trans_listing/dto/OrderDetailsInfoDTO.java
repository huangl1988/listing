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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsInfoDTO {

    private Long commodyId;

    private BigDecimal commodyPrice;

    private int number;

    private String tagsName;

    private String siteNo;

    private Long totalId;

    private String orderNo;

    private Long id;

    private int cancelFlag;

    private Date inserttime;

    private Date cancelTime;

    private int isCooking;

    public Long getPriceLong(){
        return commodyPrice.multiply(new BigDecimal(100l)).longValue();
    }
}
