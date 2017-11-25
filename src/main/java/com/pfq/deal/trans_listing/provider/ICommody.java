package com.pfq.deal.trans_listing.provider;

import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;

public interface ICommody {

	RetCommodyVo selectOne(Long id);

	RetCommodyList selectList(Integer shopId);

	

}
