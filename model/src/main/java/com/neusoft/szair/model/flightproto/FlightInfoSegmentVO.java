package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class FlightInfoSegmentVO {

	/**
	 * 航班顺序
	 * XmlElement:INDEX
	 */
	@Tag(2001)
	private String index;
	/**
	 * 航程出发城市（三字码）
	 * XmlElement:ORG_CITY
	 */
	@Tag(2002)
	private String orgCity;

	/**
	 * 航程到达城市（三字码）
	 * XmlElement:DST_CITY
	 */
	@Tag(2003)
	private String dstCity;
	/**
	 * 航程信息集合
	 * XmlElement:FLIGHT_INFO_SEGMENT_SUB_LIST
	 */
	@Tag(2004)
	private List<FlightInfoSegmentSubVO> flightInfoSegmentSubList;
}
