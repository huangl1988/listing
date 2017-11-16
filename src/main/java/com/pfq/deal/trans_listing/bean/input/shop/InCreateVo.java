package com.pfq.deal.trans_listing.bean.input.shop;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InCreateVo implements IBaseInput{
	@NonNull
	private String shopName;
	@NonNull
	private String shopCode;
	@NonNull
	private String shopAddres;
	@NonNull
	private String startTime;
	@NonNull
	private String endTime;
	@NonNull
	private String point;

	private Integer regionId;
	
}
