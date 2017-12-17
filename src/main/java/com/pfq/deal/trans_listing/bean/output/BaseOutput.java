package com.pfq.deal.trans_listing.bean.output;

import lombok.Data;

@Data
public class BaseOutput implements IBaseOutput {
	
	

	
	public BaseOutput(){

		this.doSucc();
	}
	
	/**
	 * 返回值
	 */
	private String code="succ";
	/**
	 * 返回结果
	 */
	private String msg="succ";

	public void doSucc(){
		code="succ";
		msg="succ";
	}
	
	public void doError(String msg){
		code="error";
		this.msg=msg;
	}
	
}
