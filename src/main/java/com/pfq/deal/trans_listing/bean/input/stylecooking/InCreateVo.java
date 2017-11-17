package com.pfq.deal.trans_listing.bean.input.stylecooking;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by steven on 2017/11/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InCreateVo implements IBaseInput{
    @NotNull(message="style name is empty")
    private String styleName;

}
