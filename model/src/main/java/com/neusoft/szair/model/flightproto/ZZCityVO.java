package com.neusoft.szair.model.flightproto;

import javax.xml.bind.annotation.XmlElement;

import com.shenzhenair.common.Mapping;

import io.protostuff.Tag;

public class ZZCityVO {
	/**
	 * 索引
     * XmlElement:INDEX
	 */
	@Tag(10001)
	private String index;
	/**
	 * 城市
     * XmlElement:CITY
	 */
	@Tag(10002)
	private String city;
	/**
	 * 经停时间
     * XmlElement:STOP_TIME
	 */
	@Tag(10003)
	private String stopTime;
}
