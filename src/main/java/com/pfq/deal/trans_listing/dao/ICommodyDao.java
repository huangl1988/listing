package com.pfq.deal.trans_listing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pfq.deal.trans_listing.dto.CommodyDTO;

public interface ICommodyDao {

	int insert(CommodyDTO inputVo);

	void update(CommodyDTO dto);

	void delete(Long id);

	CommodyDTO select(@Param("id")Long id);

	List<CommodyDTO> selectList();
	
	
	
	
	
}
