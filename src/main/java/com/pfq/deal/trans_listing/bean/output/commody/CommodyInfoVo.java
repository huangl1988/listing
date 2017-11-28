package com.pfq.deal.trans_listing.bean.output.commody;

import com.pfq.deal.trans_listing.bean.output.stylecooking.StyleCookingInfo;
import com.pfq.deal.trans_listing.bean.output.tag.TagInfo;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by steven on 2017/11/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommodyInfoVo {

    private Long commody_id;
    private String showTime;
    private Integer show_flag;
    private String inserttime;
    private String picture_url;
    private BigDecimal shop_price;
    private String commodyName;
    private String commodyCode;
    private Integer isSelect;
    private List<TagInfo> tags;
    private List<StyleCookingInfo> styleNames;
}
