package com.pfq.deal.trans_listing.controller.admin;

import com.pfq.deal.trans_listing.bean.output.Shop.RetShopInoVo;
import com.pfq.deal.trans_listing.exception.BusinessException;
import com.pfq.deal.trans_listing.service.RegionService;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.intfc.IRegion;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfq.deal.trans_listing.bean.input.shop.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import java.util.Optional;

@RestController
@RequestMapping("/sys/admin/regions/{regionId}/shops")
public class ShopController {

	@Autowired
	ShopService shopService;


	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<BaseOutput> create(@PathVariable @IRegion Integer regionId, InCreateVo inputVo){
		inputVo.setRegionId(regionId);
		shopService.save(inputVo);
		return ResponseEntity.ok(BaseOutput.builder().code("succ").msg("succ").build());
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<BaseOutput> selectOne(@PathVariable Integer id,@PathVariable @IRegion Integer regionId){
		RetShopInoVo retShopInoVo=RetShopInoVo.builder().shopInfo(shopService.selectOne(id)).build();
		retShopInoVo.doSucc();
		return ResponseEntity.ok(retShopInoVo);
	}
	
	
	
	
	
}
