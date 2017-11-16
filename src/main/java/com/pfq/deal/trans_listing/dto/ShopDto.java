package com.pfq.deal.trans_listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by steven on 2017/11/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDto {

    private String shopName;
    private String shopCode;
    private String shopAddres;
    private Date startTime;
    private Date endTime;
    private String point;
    private Integer regionId;
    private Integer id;
    private Date inserttime;
    private Date updatetime;

}
