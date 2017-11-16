package com.pfq.deal.trans_listing.bean.output;

import lombok.Data;

@Data
public class BaseOutput implements IBaseOutput {
	
	
	public BaseOutput(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public BaseOutput(){
		
	}
	
	/**
	 * 返回值
	 */
	private String code;
	/**
	 * 返回结果
	 */
	private String msg;

	public void doSucc(){
		code="succ";
		msg="succ";
	}
	
	public void doError(String msg){
		code="error";
		msg=msg;
	}
	
}
