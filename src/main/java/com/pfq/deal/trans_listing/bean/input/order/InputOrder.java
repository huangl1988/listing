package com.pfq.deal.trans_listing.bean.input.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by steven on 2017/12/4.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InputOrder {
    @NotNull
    private String siteNo;
    @NotNull
    private List<OrderInfo> orderList;

    private String onLineFlag;

    private String address;

    private String point;

    private Long totalId;

}
