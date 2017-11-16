package com.pfq.deal.trans_listing.bean.output.shop;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2017/11/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
public class RetShopInoVo extends BaseOutput{
   private ShopInfo shopInfo;
}
