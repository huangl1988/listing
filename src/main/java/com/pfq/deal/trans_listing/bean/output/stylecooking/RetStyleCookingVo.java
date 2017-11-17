package com.pfq.deal.trans_listing.bean.output.stylecooking;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.dto.StyleCookingDto;
import lombok.*;

/**
 * Created by steven on 2017/11/17.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RetStyleCookingVo extends BaseOutput {

    private StyleCookingInfo styleCookingInfo;

}
