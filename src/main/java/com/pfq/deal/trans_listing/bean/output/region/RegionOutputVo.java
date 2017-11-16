package com.pfq.deal.trans_listing.bean.output.region;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
public class RegionOutputVo extends BaseOutput{
	
	private Integer id;
	
	private String regionName;
	
	private String city;
	
	private String updateTime;
	
	
}
