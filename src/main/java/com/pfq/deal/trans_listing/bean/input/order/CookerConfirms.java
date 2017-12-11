package com.pfq.deal.trans_listing.bean.input.order;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by steven on 2017/12/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookerConfirms implements IBaseInput {

    private List<OrderInfo> confirmsList;

}
