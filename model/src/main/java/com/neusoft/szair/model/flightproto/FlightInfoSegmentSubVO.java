package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public class FlightInfoSegmentSubVO {
	/**
	 * 航班信息
	 * 单程场合：一条
	 * 中转场合：二条
	 * 往返场合：一条
	 * 多段场合：一条
	 * XmlElement:FLIGHT_LIST
	 */
	@Tag(3001)
	private List<FlightVO> flightList;
	
	/**
	 * 经停城市
	 * XmlElement:ZZ_CITY_LIST
	 */
	@Tag(3003)
	private List<ZZCityVO> zzCityList;
	
	/**
	 * 经停时间
	 * XmlElement:STOP_TIME
	 */
	@Tag(3004)
	private String stopTime;
	/**
	 * 经停时间
	 * XmlElement:STOP_TIME_BACK
	 */
	@Tag(3005)
	private String stopTimeBack;
	
	/**
	 * 经停时间
	 * XmlElement:DURATION
	 */
	@Tag(3007)
	private String duration;
	
	/**
	 * 经停时间
	 * XmlElement:DURATION_BACK
	 */
	@Tag(3008)
	private String durationBack;
	
	/**
	 * 航班号组合场合相加如（ZH9999+ZH0000）
	 * XmlElement:FLIGHT_NO
	 */
	@Tag(3006)
	private String flightNo;
	
	/**
	 * 舱位产品信息
	 * XmlElement:CLASS_PRODUCT_LIST
	 */
	@Tag(3002)
	private List<ClassProductVO> classProductList;
}
