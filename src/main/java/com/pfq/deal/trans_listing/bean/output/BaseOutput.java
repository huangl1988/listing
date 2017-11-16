package com.pfq.deal.trans_listing.bean.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseOutput implements IBaseOutput {
	
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
}
