package com.pfq.deal.trans_listing.bean.output.commody;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetCreateVo extends  BaseOutput{
	
	private String id;
	
	
}