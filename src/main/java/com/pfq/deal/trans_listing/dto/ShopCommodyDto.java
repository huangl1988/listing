package com.pfq.deal.trans_listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by steven on 2017/11/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCommodyDto {

    private Long commody_id;
    private Integer shop_id;
    private Date showTime;
    private Integer show_flag;
    private Date inserttime;
    private String picture_url;
    private BigDecimal shop_price;
    private Date updatetime;
}
