package com.pfq.deal.trans_listing.bean.output.shop;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.*;

import java.util.List;

/**
 * Created by steven on 2017/11/17.
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetShopInfoListVo extends BaseOutput{

    private List<ShopInfo> shopInfoList;
}
