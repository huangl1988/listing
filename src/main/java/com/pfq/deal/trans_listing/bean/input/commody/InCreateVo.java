package com.pfq.deal.trans_listing.bean.input.commody;

import com.pfq.deal.trans_listing.bean.input.IBaseInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InCreateVo implements IBaseInput{
	@NonNull
	private String commodyName;
	@NonNull
	private Long commodyPrice;
	@NonNull
	private String commodyCode;
	
}
