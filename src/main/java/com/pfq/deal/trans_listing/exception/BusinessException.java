package com.pfq.deal.trans_listing.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973326678548729646L;
	
	public BusinessException(String message){
		super(message);
	}
	
	private String message;
	
}
