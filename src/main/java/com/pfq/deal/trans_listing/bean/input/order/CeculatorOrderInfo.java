package com.pfq.deal.trans_listing.bean.input.order;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by steven on 2017/12/5.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CeculatorOrderInfo extends BaseOutput{

    private BigDecimal totalPrice=new BigDecimal("0");

    private String orderNo;

    private String siteNo;

}
