package com.pfq.deal.trans_listing.bean.output.region;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import lombok.*;

import java.util.List;

/**
 * Created by steven on 2017/11/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class RegionsOutput extends BaseOutput {

    private List<RegionOutputVo> list;



}
