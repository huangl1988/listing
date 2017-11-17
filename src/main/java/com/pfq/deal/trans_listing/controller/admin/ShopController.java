package com.pfq.deal.trans_listing.controller.admin;

import com.pfq.deal.trans_listing.bean.output.shop.RetShopInfoListVo;
import com.pfq.deal.trans_listing.service.intfc.IShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfq.deal.trans_listing.bean.input.shop.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.shop.RetShopInoVo;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.intfc.IRegion;

@RestController
@RequestMapping("/sys/admin/regions/{regionId}/shops")
public class ShopController {

	@Autowired
	ShopService shopService;


	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<BaseOutput> create(@PathVariable @IRegion Integer regionId, InCreateVo inputVo){
		inputVo.setRegionId(regionId);
		shopService.save(inputVo);
		return ResponseEntity.ok(new BaseOutput());
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<BaseOutput> selectOne(@PathVariable @IShop Integer id, @PathVariable @IRegion Integer regionId){
		RetShopInoVo retShopInoVo=new RetShopInoVo();
		retShopInoVo.setShopInfo(shopService.selectOne(id));
		retShopInoVo.doSucc();
		return ResponseEntity.ok(retShopInoVo);
	}

	@RequestMapping(value="/",method = RequestMethod.GET)
	public ResponseEntity<BaseOutput> select(@PathVariable @IRegion Integer regionId){
		RetShopInfoListVo retShopInoVo= RetShopInfoListVo.builder().shopInfoList(shopService.selectList(regionId)).build();
		retShopInoVo.doSucc();
		return ResponseEntity.ok(retShopInoVo);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<BaseOutput> delete(@PathVariable @IShop Integer id,@PathVariable @IRegion Integer regionId){
		BaseOutput retVo = new BaseOutput();
		shopService.delete(id);
		retVo.doSucc();
		return ResponseEntity.ok(retVo);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity<BaseOutput> update(@PathVariable @IShop Integer id,@PathVariable @IRegion Integer regionId, InCreateVo inputVo){
		inputVo.setRegionId(regionId);

		shopService.update(inputVo,id);
		return ResponseEntity.ok(new BaseOutput());
	}
	
	
	
}
