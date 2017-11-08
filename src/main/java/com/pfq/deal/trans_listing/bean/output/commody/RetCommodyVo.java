package com.pfq.deal.trans_listing.bean.output.commody;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
public class RetCommodyVo extends  BaseOutput{

	private String commodyName;

	private Long commodyPrice;

	private String commodyCode;

	private String startTime;

	private String endTime;

	private byte showFlag;

	private Long id;

}
