package com.pfq.deal.trans_listing.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfq.deal.trans_listing.bean.input.shop.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;

@RestController
@RequestMapping("/sys/admin/regions/{regionId}/shops")
public class ShopController {
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<BaseOutput> create(InCreateVo inputVo){
		return null;
	}
	
	
	
	
	
	
	
}
