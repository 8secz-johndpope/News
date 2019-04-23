package com.neusoft.szair.model.flightproto;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class TaxVO {
	/**
	 * 变更舱位
	 */
//	private String changeClass;
	/**
	 * 乘机人类型
	 */
	@Tag(9001)
	private String psgrType;
	/**
	 * 金额信息
	 */
	@Tag(9002)
	private String amount;
	/**
	 * 币种类型
	 */
	@Tag(9003)
	private String currencyCode;
	/**
	 * 税费代码
	 */
	@Tag(9004)
	private String taxCode;
	/**
	 * 税费类型
	 */
	@Tag(90016)
	private String taxType;
	/**
	 * 税费信息
	 */
	@Tag(9005)
	private String taxName;

}
