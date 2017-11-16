package com.pfq.deal.trans_listing.bean.output.Shop;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.*;

/**
 * Created by steven on 2017/11/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RetShopInoVo extends BaseOutput{
   private ShopInfo shopInfo;
}
