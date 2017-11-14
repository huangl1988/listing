package com.pfq.deal.trans_listing.bean.input.region;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InCreateVo implements IBaseInput{
	
	private String city;
	
	private String regionName;
	
	private Integer id;
	
}
