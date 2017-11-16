package com.pfq.deal.trans_listing.bean.input.region;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InCreateVo implements IBaseInput{
	@NotNull
	private String city;
	
	private String regionName;
	
	private Integer id;
	
}
