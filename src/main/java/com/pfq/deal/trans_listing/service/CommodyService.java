package com.pfq.deal.trans_listing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfq.deal.trans_listing.bean.input.commody.InCreateVo;
import com.pfq.deal.trans_listing.bean.input.commody.InUpdateVo;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dto.CommodyDTO;
import com.pfq.deal.trans_listing.util.DateUtils;

@Service
public class CommodyService {

	@Autowired
	ICommodyDao commodyDao;

	public String create(InCreateVo inputVo) {

		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName()).build();

		return commodyDao.insert(dto) + "";
	}

	public void update(InUpdateVo inputVo) {

		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName())
				.id(inputVo.getId())
				.build();

		commodyDao.update(dto);
	}

	public void delete(Long id) {

		commodyDao.delete(id);
	}

	public RetCommodyVo findById(Long id) {

		return dtoToCommodyVo(Optional.ofNullable(commodyDao.select(id)).orElse(null));
	}

	public RetCommodyVo dtoToCommodyVo(CommodyDTO dto) {
		if (dto == null) {
			return RetCommodyVo.builder().build();
		}
		return RetCommodyVo.builder().commodyCode(dto.getCommodyCode()).commodyName(dto.getCommodyName()).id(dto.getId()).build();
	}

	public RetCommodyList selectList() {
		List<RetCommodyVo> retList = new ArrayList<>();
		RetCommodyList temp = RetCommodyList.builder().retList(retList).build();
		Optional.ofNullable(commodyDao.selectList()).ifPresent(dtolist -> {
			dtolist.forEach(dto -> {
				retList.add(dtoToCommodyVo(dto));
			});
		});
		return temp;
	}

}
