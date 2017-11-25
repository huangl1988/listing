package com.pfq.deal.trans_listing.controller.admin;

import java.sql.SQLException;

import javax.mail.Header;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import com.alibaba.dubbo.remoting.exchange.Response;
import com.pfq.deal.trans_listing.bean.input.region.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.bean.output.region.RegionsOutput;
import com.pfq.deal.trans_listing.exception.BusinessException;
import com.pfq.deal.trans_listing.service.RegionService;

import io.swagger.annotations.ResponseHeader;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

@RestController
@RequestMapping("/sys/admin/regions")
@Slf4j
public class RegionController {
	
	@Autowired
	RegionService regionService;
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity<IBaseOutput> create(InCreateVo inputVo){
		try{
			regionService.doSave(inputVo);
			return ResponseEntity.ok(new BaseOutput());
		}catch(SQLException e){
			log.error("error",e);
			throw new BusinessException("info has been deal or try it another time!");
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity<IBaseOutput> update(InCreateVo inputVo,@PathVariable Integer id){
		try{
			inputVo.setId(id);
			regionService.update(inputVo);
			return ResponseEntity.ok(new BaseOutput());
		}catch(SQLException e){
			log.error("error",e);
			throw new BusinessException("info has been deal or try it another time!");
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<IBaseOutput> delete(@PathVariable Integer id){
		try{
			
			regionService.delete(id);
			return ResponseEntity.ok(new BaseOutput());
		}catch(SQLException e){
			log.error("error",e);
			throw new BusinessException("info has been deal or try it another time!");
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<IBaseOutput> select(@PathVariable Integer id){
		try{
			return ResponseEntity.ok(regionService.select(id));
		}catch(NullPointerException e){
			log.error("error",e);
			throw new BusinessException("id is not exist!");
		}
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<RegionsOutput> select(){
		try{
			return ResponseEntity.ok(regionService.selectList());
		}catch(NullPointerException e){
			log.error("error",e);
			throw new BusinessException("id is not exist!");
		}
	}

}
