package com.pfq.deal.trans_listing.bean.output.stylecooking;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.*;

import java.util.List;

/**
 * Created by steven o
 * n 2017/11/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class RetStyleCookingsVo extends BaseOutput {

    private List<StyleCookingInfo> retList;


}
