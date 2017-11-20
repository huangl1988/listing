package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.RegionDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRegionDao {
	
	int save(RegionDto dto);

	int update(RegionDto dto);

	int delete(@Param("id") Integer id);

	RegionDto select(@Param("id") Integer id);

    List<RegionDto> selectList();
}
