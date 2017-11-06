package com.pfq.deal.trans_listing.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommodyDTO{
	
	private Long id;
	
	private String commodyName;
	
	private Long commodyPrice;
	
	private String commodyCode;
	
	private Date endTime;
	
	private Date startTime;
	
	private byte showFlag;
	
	private Date inserttime;
	
	private Date updatetime;
	
}
