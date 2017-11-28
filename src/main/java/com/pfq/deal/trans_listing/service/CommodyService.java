package com.pfq.deal.trans_listing.service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.pfq.deal.trans_listing.bean.output.commody.CommodyInfoVo;
import com.pfq.deal.trans_listing.bean.output.tag.TagInfo;
import com.pfq.deal.trans_listing.dto.ShopCommodyDto;
import com.pfq.deal.trans_listing.dto.TagDto;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfq.deal.trans_listing.bean.input.commody.InCreateVo;
import com.pfq.deal.trans_listing.bean.input.commody.InUpdateVo;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.bean.output.stylecooking.StyleCookingInfo;
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dto.CommodyDTO;
import com.pfq.deal.trans_listing.util.DateUtils;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommodyService extends IBaseService{

	@Autowired
	ICommodyDao commodyDao;
	@Autowired
	ShopService shopService;
	@Autowired
	StyleCookingService styleCookingService;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String create(InCreateVo inputVo) {

		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName()).shopId(inputVo.getShopId())
				.styleId(inputVo.getStyleId()).build();

		 commodyDao.insert(dto);
		 if(inputVo.getStyleId()!=null){
			 Arrays.asList();
			 List<Long> list= new ArrayList<>();
			 list.add(dto.getId());
			 shopService.saveCommodyRelation(inputVo.getStyleId(),list,dto.getShopId());
		 }
		 
		return ""+dto.getId();
	}

	public void update(InUpdateVo inputVo) {

		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName())
				.id(inputVo.getId())
				.build();

		commodyDao.update(dto);
	}
	@Transactional
	public void delete(Long id) {
		shopService.deleteCommodyInfo(null,id);
		commodyDao.delete(id);
	}

	public RetCommodyVo findById(Long id) {

		return dtoToRetCommodyVo(toCommodyInfo(Optional.ofNullable(commodyDao.select(id)).orElse(null)));
	}

	public RetCommodyVo dtoToRetCommodyVo(CommodyInfoVo infoVo) {

		return RetCommodyVo.builder().
				commodyInfo(Optional.ofNullable(infoVo).orElse(CommodyInfoVo.builder().build())).build();
	}
	private CommodyInfoVo toCommodyInfo(CommodyDTO dto){
		if(dto==null)
			return null;
		ShopCommodyDto shopCommodyDto=Optional.ofNullable(shopService.getShopCommodyByCommodyId(dto.getId())).orElse(ShopCommodyDto.builder().build());
		List<TagDto> tagsList= shopService.getTagList(dto.getId());
		List<TagInfo> tags = new ArrayList<>();
		
		List<StyleCookingInfo> stylesInfo=Optional.ofNullable(styleCookingService.selectList(dto.getShopId())).orElse(new ArrayList<>());
		List<Integer> styleIds=shopService.getCommodyStylesByCommodyListList(dto.getId());
		List<StyleCookingInfo> isSelectStyle= new ArrayList<>();
		Optional.ofNullable(styleIds).ifPresent(list->{
			stylesInfo.parallelStream().filter(new Predicate<StyleCookingInfo>() {
				@Override
				public boolean test(StyleCookingInfo t) {
					
					return styleIds.contains(t.getId());
				}
			}).collect(Collectors.toList()).forEach(styleInfo->{
				isSelectStyle.add(styleInfo);
			});
		});
		Optional.ofNullable(tagsList).ifPresent(list->{list.parallelStream().forEach(tagDto -> {
			 tags.add(TagInfo.builder().id(tagDto.getId()).tagName(tagDto.getTagName()).build());
		});});
		return CommodyInfoVo.builder()
				.commody_id(dto.getId())
				.commodyCode(dto.getCommodyCode())
				.commodyName(dto.getCommodyName())
				.inserttime(DateUtils.getDateString(dto.getInserttime()))
				.isSelect(shopCommodyDto.getCommody_id()==null?0:1)
				.picture_url(shopCommodyDto.getPicture_url())
				.shop_price(shopCommodyDto.getShop_price())
				.show_flag(shopCommodyDto.getShow_flag())
				.showTime(DateUtils.getDateString(shopCommodyDto.getShowTime()))
				.tags(tags)
				.styleNames(isSelectStyle).build();
	}

	public RetCommodyList selectList(Integer shopId) {
		List<CommodyInfoVo> retList = new ArrayList<>();
		RetCommodyList temp = RetCommodyList.builder().retList(retList).build();
		Optional.ofNullable(commodyDao.selectList(shopId)).ifPresent(dtolist -> {
			dtolist.forEach(dto -> {
				retList.add(toCommodyInfo(dto));
			});
		});
		return temp;
	}

	@Override
	public boolean isExist(String id) {
		return this.findById(Long.parseLong(id)).getCommodyInfo()!=null;
	}
}
