package com.pfq.deal.trans_listing.service;

import java.sql.SQLException;
import java.util.Optional;

import com.pfq.deal.trans_listing.bean.output.region.RegionsOutput;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfq.deal.trans_listing.bean.input.region.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.region.RegionOutputVo;
import com.pfq.deal.trans_listing.dao.IRegionDao;
import com.pfq.deal.trans_listing.dto.RegionDto;
import com.pfq.deal.trans_listing.exception.BusinessException;

@Service
public class RegionService {
	
	@Autowired
	IRegionDao regionDao;
	
	public void doSave(InCreateVo vo) throws SQLException{
		Optional.ofNullable(regionDao.save(this.toDto(vo))).ifPresent(count->{
			if(count<1){
				throw new BusinessException("add fail! try it again");
			}
		});
	}
	
	private RegionDto toDto(InCreateVo vo){
		return RegionDto.builder().city(vo.getCity()).regionName(vo.getRegionName()).id(vo.getId()).build();
	}

	public void update(InCreateVo inputVo) throws SQLException {
		Optional.ofNullable(regionDao.update(this.toDto(inputVo))).ifPresent(count->{
			if(count<1){
				throw new BusinessException("update fail! try it again");
			}
		});
	}

	public void delete(Integer id) throws SQLException{
		Optional.ofNullable(regionDao.delete(id)).ifPresent(count->{
			if(count<1){
				throw new BusinessException("delete fail! try it again");
			}
		});
	}
	
	

	public RegionOutputVo select(Integer id) {
		
		return toRegionOutputVo(Optional.ofNullable(regionDao.select(id)).orElse(RegionDto.builder().build()));

	}
	
	private RegionOutputVo toRegionOutputVo(RegionDto vo){
		RegionOutputVo s=RegionOutputVo.builder().city(vo.getCity()).regionName(vo.getRegionName()).id(vo.getId()).build(); 
		return s;
	}


    public RegionsOutput selectList() {

		var retList = new java.util.ArrayList<RegionOutputVo>();
		Optional.ofNullable(regionDao.selectList()).ifPresent(regionDtos -> {
			regionDtos.forEach(regionDto -> {
				retList.add(toRegionOutputVo(regionDto));
			});
		});

		RegionsOutput retInfo = new RegionsOutput();
		retInfo.setList(retList);
		return retInfo;
    }
}
