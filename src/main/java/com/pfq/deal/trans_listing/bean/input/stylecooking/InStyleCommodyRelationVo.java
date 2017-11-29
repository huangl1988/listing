package com.pfq.deal.trans_listing.bean.input.stylecooking;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by steven on 2017/11/18.
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InStyleCommodyRelationVo implements IBaseInput {
    //@RequestBody
    private List<Long> commodyList;
    //@NotNull(message = "commodyListJson is empty!")
    private String commodyListJson;

}
