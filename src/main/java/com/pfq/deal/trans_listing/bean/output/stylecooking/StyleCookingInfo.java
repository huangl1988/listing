package com.pfq.deal.trans_listing.bean.output.stylecooking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by steven on 2017/11/17.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StyleCookingInfo {

    private String styleName;

    private Integer id;

    private String inserttime;

    private String updatetime;


}
