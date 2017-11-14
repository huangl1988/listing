package com.pfq.deal.trans_listing.exception.handler;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.exception.BusinessException;

@ControllerAdvice  
public class DealExceptionForGernal{
	
	@ExceptionHandler(value={BusinessException.class})
	public ResponseEntity<IBaseOutput> errorReturn(final BusinessException ex, final WebRequest req){
		
		return ResponseEntity.status(HttpStatus.OK_200).body(new BaseOutput("fail", ex.getMessage()));
	}
	
	
	
}
