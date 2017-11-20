package com.pfq.deal.trans_listing.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by steven on 2017/11/20.
 */
@Data
@Builder
public class TagDto {

    private String tagName;

    private Long id;

    private Integer shopId;
}
