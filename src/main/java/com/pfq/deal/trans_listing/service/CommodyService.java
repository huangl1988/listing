package com.pfq.deal.trans_listing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfq.deal.trans_listing.bean.input.commody.InCreateVo;
import com.pfq.deal.trans_listing.bean.input.commody.InUpdateVo;
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
				.commodyName(inputVo.getCommodyName())
				.commodyPrice(inputVo.getCommodyPrice()).build();

		return commodyDao.insert(dto) + "";
	}

	public void update(InUpdateVo inputVo) {
		
		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName())
				.commodyPrice(inputVo.getCommodyPrice())
				.endTime(DateUtils.getTimeDate(inputVo.getEndTime()))
				.id(inputVo.getId())
				.startTime(DateUtils.getTimeDate(inputVo.getStartTime()))
				.showFlag(inputVo.getShowFlag()).build();
		
		commodyDao.update(dto);
	}

	public void delete(Long id) {
		
		commodyDao.delete(id);
	}
	
	public RetCommodyVo findById(Long id) {

		return dtoToCommodyVo(Optional.ofNullable(commodyDao.select(id)).orElse(null));
	}
	
	public  RetCommodyVo dtoToCommodyVo(CommodyDTO dto){
		if(dto==null){
			return RetCommodyVo.builder().build();
		}
		return RetCommodyVo.builder()
				.commodyCode(dto.getCommodyCode())
				.commodyName(dto.getCommodyName())
				.commodyPrice(dto.getCommodyPrice())
				.endTime(DateUtils.getDateString(dto.getEndTime()))
				.id(dto.getId())
				.showFlag(dto.getShowFlag())
				.startTime(DateUtils.getDateString(dto.getStartTime()))
				.build();
	}
	
}
