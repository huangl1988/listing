package com.pfq.deal.trans_listing.bean.output.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2017/11/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopInfo {

    private String shopName;
    private String shopCode;
    private String shopAddres;
    private String startTime;
    private String endTime;
    private String point;
    private Integer regionId;
    private Integer id;

}
