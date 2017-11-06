package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.CommodyDTO;

public interface ICommodyDao {

	int insert(CommodyDTO inputVo);

	void update(CommodyDTO dto);

	void delete(Long id);

	CommodyDTO select(Long id);
	
	
	
	
	
}
