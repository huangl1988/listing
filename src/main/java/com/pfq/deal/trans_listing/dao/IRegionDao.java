package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.RegionDto;

public interface IRegionDao {
	
	int save(RegionDto dto);

	int update(RegionDto dto);

	int delete(Integer id);

	RegionDto select(Integer id);
	
	
}
