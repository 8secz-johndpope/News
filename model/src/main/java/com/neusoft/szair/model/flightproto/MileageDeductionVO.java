package com.neusoft.szair.model.flightproto;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class MileageDeductionVO{
	
	/**
	 * 抵扣里程数
     * XmlElement:DEDUCTIBLE_MILE
	 */
	@Tag(8001)
	private String deductibleMile;
	
	/**
	 * 抵扣金额
     * XmlElement:DEDUCTIBLE_AMOUNT
	 */
	@Tag(8002)
	private String deductibleAmount;
	
	/**
	 * 产品编码DH
     * XmlElement:DEDUCTIBLE_CODE
	 */
	@Tag(8003)
	private String deductibleCode;
	
	/**
	 * 产品编码DH
     * XmlElement:MILEAGE_PRODUCT_CODE
	 */
//	private String mileageProductCode;
	
	/**
	 * 里程抵扣上限
     * XmlElement:UPPER_LIMIT
	 */
	@Tag(8004)
	private String upperLimit;

}