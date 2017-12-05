package com.pfq.deal.trans_listing.bean.input.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by steven on 2017/12/4.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfo {

    private int num;

    private Long commdyId;

    private String tagsName;

    private BigDecimal commodyPrice;

    private int shopId;

    private Long id;

    public Long getPriceLong(){
        return commodyPrice.multiply(new BigDecimal(100l)).longValue();
    }

}
