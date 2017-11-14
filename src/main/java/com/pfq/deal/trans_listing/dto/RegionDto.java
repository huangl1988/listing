package com.pfq.deal.trans_listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionDto {
	
	private Integer id;
	
	private String regionName;
	
	private String city;
	
	private String updateTime;
	
	
}
