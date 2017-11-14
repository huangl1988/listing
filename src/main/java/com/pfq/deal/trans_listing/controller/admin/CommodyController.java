package com.pfq.deal.trans_listing.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfq.deal.trans_listing.bean.input.commody.InCreateVo;
import com.pfq.deal.trans_listing.bean.input.commody.InUpdateVo;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.bean.output.commody.RetCreateVo;
import com.pfq.deal.trans_listing.service.CommodyService;

@RestController
@RequestMapping("/v1/sys/admin/commodies")

public class CommodyController {

	@Autowired
	CommodyService commodyService;

	@RequestMapping(value = "/commody", method = RequestMethod.POST)
	@ExceptionHandler
	public ResponseEntity<IBaseOutput> create(InCreateVo inputVo) {
		RetCreateVo vo = RetCreateVo.builder().id(commodyService.create(inputVo)).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(vo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<IBaseOutput> update(@PathVariable Long id, InUpdateVo inputVo) {
		inputVo.setId(id);
		commodyService.update(inputVo);
		RetCreateVo vo = RetCreateVo.builder().build();
		vo.setCode("succ");
		vo.setMsg("成功");
		return ResponseEntity.status(HttpStatus.OK).body(vo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<IBaseOutput> delete(@PathVariable Long id) {
		commodyService.delete(id);
		RetCreateVo vo = RetCreateVo.builder().build();
		return ResponseEntity.status(HttpStatus.OK).body(vo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RetCommodyVo> selectOne(@PathVariable Long id) {
		RetCommodyVo tRetCommodyVo = commodyService.findById(id);
		tRetCommodyVo.setCode("succ");
		tRetCommodyVo.setMsg("成功");
		return ResponseEntity.status(HttpStatus.OK).body(tRetCommodyVo);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<RetCommodyList> selectList() {
		RetCommodyList tRetCommodyList = commodyService.selectList();
		tRetCommodyList.setCode("succ");
		tRetCommodyList.setMsg("成功");
		return ResponseEntity.status(HttpStatus.OK).body(tRetCommodyList);
	}

}
