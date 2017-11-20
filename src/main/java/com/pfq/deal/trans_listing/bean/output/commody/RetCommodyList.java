package com.pfq.deal.trans_listing.bean.output.commody;

import java.util.List;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Builder
public class RetCommodyList extends  BaseOutput{

	private List<CommodyInfoVo> retList;
	

}

