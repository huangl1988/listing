package com.pfq.deal.trans_listing.bean.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by steven on 2017/11/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InCreateShopCommodyVo implements IBaseInput{

   private Long commody_id;
   private Integer shop_id;
   private String showTime;
   private Integer show_flag;
   private String inserttime;
   private String picture_url;
   private BigDecimal shop_price;
   private String commodyName;

}
