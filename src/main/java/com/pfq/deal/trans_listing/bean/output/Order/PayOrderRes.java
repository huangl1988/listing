package com.pfq.deal.trans_listing.bean.output.Order;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by steven on 2017/12/5.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayOrderRes extends BaseOutput {

    private Map<String,Object> retMap;



}
