package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.RegionDto;

import java.util.List;

public interface IRegionDao {
	
	int save(RegionDto dto);

	int update(RegionDto dto);

	int delete(Integer id);

	RegionDto select(Integer id);

    List<RegionDto> selectList();
}
