package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class ClassProductSubVO {

	/**
	 * 舱位产品类型
	 * APP_FAVOR/SPE_MOBILE_SELF/SPE_GROUP_FAVOR
	 * XmlElement:PRODOCT_SUB_TYPE
	 */
	@Tag(6001)
	private String prodoctSubType;
	
	/**
	 * 舱位产品名称
	 * 手机特惠/会员日特价/集团客户特惠
	 * XmlElement:PRODOCT_SUB_NAME
	 */
	@Tag(6002)
	private String prodoctSubName;
	
	/**
	 * 舱位信息集合
	 * XmlElement:CLASS_INFO_LIST
	 */
	@Tag(6003)
	private List<ClassVO> classInfoList;

}
