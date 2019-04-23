package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.shenzhenair.common.Mapping;

import io.protostuff.Tag;

public class FlightVO {
	
	
	@Override
	public String toString() {
		return flightNo + ":" + orgCity + dstCity + ":" + orgDate;
	}

	/**
	 * 国航标志
	 * XmlElement:CA_FLIGHT_FLAG
	 */
	@Tag(4001)
	private String caFlightFlag = "0";

	/**
	 * 航班号
	 * XmlElement:FLIGHT_NO
	 */
	@Tag(4002)
	private String flightNo;

	/**
	 * 出发城市（三字码）
	 * XmlElement:ORG_CITY
	 */
	@Tag(4003)
	private String orgCity;

	/**
	 * 到达城市（三字码）
	 * XmlElement:DST_CITY
	 */
	@Tag(4004)
	private String dstCity;
	
	/**
	 * 出发日期
	 * XmlElement:ORG_DATE
	 */
	@Tag(4005)
	private String orgDate;
	
	/**
	 * 到达日期
	 * XmlElement:DST_DATE
	 */
	@Tag(4006)
	private String dstDate;

	/**
	 * 起飞时间
	 * XmlElement:ORG_TIME
	 */
	@Tag(4007)
	private String orgTime;

	/**
	 * 到达时间
	 * XmlElement:DST_TIME
	 */
	@Tag(4008)
	private String dstTime;
	
	/**
	 * 飞行时长
	 * XmlElement:DURATION
	 */
	@Tag(4009)
	private String duration;

	/**
	 * 是否为共享航班(0-false;1-true)
	 * XmlElement:IS_CODE_SHARE
	 */
	@Tag(4010)
	private String isCodeShare;

	/**
	 * 共享航班提示文字
	 * XmlElement:CODE_SHARE_TEXT
	 */
	@Tag(4011)
	private String codeShareText;
	/**
	 * 市场
	 * XmlElement:MARKET
	 */
	@Tag(4012)
	private String market;
	/**
	 * 实际承运人
	 * XmlElement:CARRIER
	 */
	@Tag(4013)
	private String carrier;

	/**
	 * 实际承运航班号
	 * XmlElement:CARRIER_FLIGHT_NO
	 */
	@Tag(4014)
	private String carrierFlightNo;

	/**
	 * 经停城市(三字码)
	 * XmlElement:STOP_CITY
	 */
	@Tag(4015)
	private String stopCity;
	

	/**
	 * 经停城市(中文)
	 * XmlElement:STOP_CITY_CH
	 */
	@Tag(4016)
	private String stopCityCH;
	
	
	/**
	 * 经停次数
	 * XmlElement:STOP_QUANTITY
	 */
	@Tag(4030)
	private String stopQuantity;

	/**
	 * 是否有餐食 0：无1：有
	 * XmlElement:MEAL
	 */
	@Tag(4017)
	private String meal;

	/**
	 * 特殊餐食 0:无特殊餐食； 1:提供特殊餐食但始发地不是深圳； 2:深圳始发的航班提供更多特殊餐食
	 * XmlElement:SPECIAL_MEAL
	 */
	@Tag(4018)
	private String spMeal;

	/**
	 * 机型
	 * XmlElement:AC_TYPE
	 */
	@Tag(4019)
	private String acType;

	/**
	 * 航段公布运价
	 * XmlElement:BASE_PRICE
	 */
	@Tag(4020)
	private String basePrice;

	/**
	 * 该行段是否需要验证码0不、1需要
	 * XmlElement:IS_CHECK_SEGMENT
	 */
	@Tag(4021)
	private String isCheckSegment;
	
	/**
	 * 准点率
	 * XmlElement:ONTIME_RATE
	 */
	@Tag(4022)
	private String ontimeRate;
	
	/**
     * Y舱公布运价
     */
	@Tag(4024)
    private String pulicClassPriceY;
    
    /**
     * F舱公布运价
     */
	@Tag(4025)
    private String pulicClassPriceF;
    
    /**
     * Y舱库存
     */
	@Tag(4027)
    private String classStorageY;
    
    /**
     * F舱库存
     */
	@Tag(4028)
    private String classStorageF;

    /**
     * C舱库存
     */
	@Tag(4029)
    private String classStorageC;
    
    /**
     * C舱公布运价
     */
	@Tag(4026)
    private String pulicClassPriceC;
	/**
	 * 税费信息
	 * XmlElement:TAX_LIST
	 */
	@Tag(4023)
	private List<TaxVO> taxList;
	
	/**
	 * 是否新运价，0：否，1：是
	 * XmlElement:TAX_LIST
	 */
	@Tag(4031)
	private String isNewFare;


}
