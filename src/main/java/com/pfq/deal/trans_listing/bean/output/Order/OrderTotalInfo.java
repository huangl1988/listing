package com.pfq.deal.trans_listing.bean.output.Order;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by steven on 2017/12/5.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderTotalInfo extends BaseOutput {

    private String orderNo;

    private BigDecimal totalPrice;

    private int totalNum;

    private List<RetOrderInfo> list;

    private String siteNo;

}
