package com.pfq.deal.trans_listing.bean.output.shop;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.commody.CommodyInfoVo;
import lombok.*;

import java.util.List;

/**
 * Created by steven on 2017/11/20.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RetShopCommodyListVo extends BaseOutput{

    private List<CommodyInfoVo> allList;

}
