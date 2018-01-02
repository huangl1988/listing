package com.pfq.deal.trans_listing.bean.input.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by steven on 2017/12/5.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateOrder {

    private String orderNo;

    private List<OrderInfo> orderInfoList;

    private String siteNo;

    private Long totalId;
}
