package com.pfq.deal.trans_listing.bean.output.commody;

import com.pfq.deal.trans_listing.bean.output.IBaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetCreateVo implements IBaseOutput{
	
	private String id;
	
	/**
	 * 返回值
	 */
	private String code;
	/**
	 * 返回结果
	 */
	private String msg;
}
