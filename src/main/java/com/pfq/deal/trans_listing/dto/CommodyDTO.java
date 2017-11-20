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
	
	private String commodyCode;
	
	private Date inserttime;
	
	private Date updatetime;

	private Integer shopId;
	
}
