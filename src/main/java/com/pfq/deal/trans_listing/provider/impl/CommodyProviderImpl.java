package com.pfq.deal.trans_listing.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.provider.ICommody;
import com.pfq.deal.trans_listing.service.CommodyService;

@Component("commodyProvider")
public class CommodyProviderImpl implements ICommody {

	@Autowired
	CommodyService commodyServoce;

	@Override
	public RetCommodyVo selectOne(Long id) {

		return commodyServoce.findById(id);
	}

	@Override
	public RetCommodyList selectList(Integer shopId) {

		return commodyServoce.selectList(shopId);
	}

}
