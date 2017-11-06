package com.pfq.deal.trans_listing.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfq.deal.trans_listing.bean.input.commody.InCreateVo;
import com.pfq.deal.trans_listing.bean.input.commody.InUpdateVo;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.bean.output.commody.RetCreateVo;
import com.pfq.deal.trans_listing.provider.ICommody;
import com.pfq.deal.trans_listing.service.CommodyService;

@RestController("commodyProvider")
@RequestMapping("/v1/sys/admin/commodies")
public class CommodyController implements ICommody{
	
	@Autowired
	CommodyService commodyService;
	
	@RequestMapping(value = "/commody", method = RequestMethod.POST)
	@ResponseBody
	
	public ResponseEntity<IBaseOutput> create(InCreateVo inputVo) {
		RetCreateVo vo = RetCreateVo.builder().id(commodyService.create(inputVo)).code("succ").msg("成功").build();
		return ResponseEntity.status(HttpStatus.CREATED).body(vo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<IBaseOutput> update(@PathVariable Long id,InUpdateVo inputVo) {
		inputVo.setId(id);
		commodyService.update(inputVo);
		RetCreateVo vo = RetCreateVo.builder().code("succ").msg("成功").build();
		return ResponseEntity.status(HttpStatus.CREATED).body(vo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<IBaseOutput> delete(@PathVariable Long id) {
		commodyService.delete(id);
		RetCreateVo vo = RetCreateVo.builder().code("succ").msg("成功").build();
		return ResponseEntity.status(HttpStatus.CREATED).body(vo);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public RetCommodyVo selectOne(@PathVariable Long id) {
		RetCommodyVo tRetCommodyVo=commodyService.findById(id);
		tRetCommodyVo.setCode("succ");
		tRetCommodyVo.setMsg("成功");
		return tRetCommodyVo;
	}
	
}
