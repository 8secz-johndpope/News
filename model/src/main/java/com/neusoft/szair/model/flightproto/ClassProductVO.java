package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class ClassProductVO {

	/**
	 * 舱位产品类型
	 * F/B/Y
	 * XmlElement:PRODOCT_TYPE
	 */
	@Tag(5001)
	private String prodoctType;
	
	/**
	 * 舱位产品名称
	 * 头等舱/舒适经济舱/经济舱
	 * XmlElement:PRODOCT_NAME
	 */
	@Tag(5002)
	private String prodoctName;
	
	/**
	 * 舱位信息
	 * XmlElement:CLASS_SUB_LIST
	 */
	@Tag(5003)
	private List<ClassProductSubVO> classSubList;
}
