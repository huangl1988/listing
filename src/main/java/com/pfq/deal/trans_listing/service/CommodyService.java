package com.pfq.deal.trans_listing.service;

import java.util.*;
import java.util.stream.Collector;

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
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dto.CommodyDTO;
import com.pfq.deal.trans_listing.util.DateUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommodyService implements IBaseService{

	@Autowired
	ICommodyDao commodyDao;
	@Autowired
	ShopService shopService;

	@Transactional
	public String create(InCreateVo inputVo) {

		CommodyDTO dto = CommodyDTO.builder().commodyCode(inputVo.getCommodyCode())
				.commodyName(inputVo.getCommodyName()).shopId(inputVo.getShopId()).build();

		 commodyDao.insert(dto);
		 if(inputVo.getStyleId()!=null){
			 List<Long> list= Arrays.asList();
			 list.add(dto.getId());
			 shopService.saveCommodyRelation(inputVo.getStyleId(),list,dto.getShopId());
		 }
		return "1";
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
		List<TagInfo> tags = Arrays.asList();
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
				.tags(tags).build();
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
