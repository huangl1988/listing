package com.pfq.deal.trans_listing.bean.input.commody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InUpdateVo {

	
	private String commodyName;
	
	private Long commodyPrice;
	
	private String commodyCode;
	
	private String startTime;
	
	private String endTime;
	
	private byte showFlag;
	
	private Long id;
	
}
