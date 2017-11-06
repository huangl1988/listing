package com.pfq.deal.trans_listing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HeathyCheckController {
	@RequestMapping(value="hs",method=RequestMethod.GET)
	public String isOk(){
		return "system is running";
	}
	
	
	
}
