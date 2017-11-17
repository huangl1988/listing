package com.pfq.deal.trans_listing.dto;

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
public class StyleCookingDto {

    private String styleName;

    private Integer id;

    private Date inserttime;

    private Date updatetime;

}
