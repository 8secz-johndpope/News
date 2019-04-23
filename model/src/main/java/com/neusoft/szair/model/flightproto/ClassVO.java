package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.shenzhenair.common.Mapping;

import io.protostuff.Tag;

public class ClassVO {
	
	@Override
	public String toString() {
		return "{classCode:" + classCode + 
				", productValue:"  + productValue +
				", realProductValue:" + realProductValue + 
				", originalProductValue:"  + originalProductValue + "}";
	}
	/**
	 * 国深航班查询索引
	 * XmlElement:CA_INDEX
	 */
	@Tag(7001)
	private String caIndex;
	
	/**
	 * 舱位代码
	 * XmlElement:CLASS_CODE
	 */
	@Tag(7002)
	private String classCode;
	
	/**
	 * 舱位类型（0-头等舱；1-商务舱；2-经济舱）
	 * XmlElement:CLASS_TYPE
	 */
	@Tag(7003)
	private String classType;
	
	/**
	 * 舱位名称
	 * XmlElement:CLASS_NAME
	 */
	@Tag(7004)
	private String className;
	
	/**
	 * 产品代码(展示用)
	 * 
	 * XmlElement:PRODUCT_VALUE
	 */
	@Tag(7005)
	private String productValue;
	
	/**
	 * 产品代码(原产品代码)
	 * 
	 * XmlElement:ORIGINAL_PRODUCT_VALUE
	 */
	@Tag(7006)
	private String originalProductValue;
	
	/**
	 * 产品代码(组合产品)
	 * 
	 * XmlElement:REAL_PRODUCT_VALUE
	 */
	@Tag(7007)
	private String realProductValue;
	
	
	/**
	 * 舱位价格(优惠前)
	 * XmlElement:TICKET_PRICE
	 */
	@Tag(7009)
	private String ticketPrice;
	
	/**
	 * 舱位价格
	 * XmlElement:CLASS_PRICE
	 */
	@Tag(7010)
	private String classPrice;

	/**
	 * 公布运价
	 * XmlElement:PUBLIC_CLASS_PRICE
	 */
	@Tag(7011)
	private String publicClassPrice;
	/**
	 * 舱位折扣
	 * XmlElement:DISCOUNT
	 */
	@Tag(7012)
	private String discount;
	/**
	 * 舱位相对折扣
	 * XmlElement:BASE_DISCOUNT
	 */
	@Tag(7013)
	private String baseDiscount;

	/**
	 * 库存座位数
	 * XmlElement:STORGE
	 */
	@Tag(7014)
	private String storge;

	/**
	 * 退改签规定（使用说明）
	 * XmlElement:CHANGE_REFUND
	 */
	@Tag(7015)
	private String changeRefund;

	/**
	 * 里程累积
	 * XmlElement:MILE_ACC_PRO
	 */
	@Tag(7016)
	private String mileAccPro;

	/**
	 * 最低里程累积
	 * XmlElement:LOWEST_ACC
	 */
	@Tag(7017)
	private String lowestAcc;

	/**
	 * 立省
	 * XmlElement:SAVE_MONEY
	 */
	@Tag(7018)
	private String saveMoney;

	/**
	 * 优惠后价格
	 * XmlElement:AFTER_COUPON_PRICE
	 */
	@Tag(7019)
	private String afterCouponPrice;

	/**
	 * 该舱位的儿童票价格
	 * XmlElement:CHILD_TICKET_PRICE
	 */
	@Tag(7021)
	private String ticketPriceChild;
	
	/**
	 * 该舱位的婴儿价格
	 * XmlElement:BABY_TICKET_PRICE
	 */
	@Tag(7022)
	private String ticketPriceBaby;

	/**
	 * 飞偿等待（0:否；1-是）
	 * XmlElement:DELAY_INSURE
	 */
	@Tag(7020)
	private String delayInsure;

	/**
	 * 是否赠送优惠券（0：否1：是）
	 * XmlElement:GIVECOUPON
	 */
	@Tag(7023)
	private String giveCoupon;
	
	/**
	 * 里程抵扣产品
	 * XmlElement:MILEAGE_DEDUCTION_LIST
	 */
	@Tag(7024)
	private List<MileageDeductionVO> mileageList;
	
	/**
	 * 里程抵扣类型0：定额，1：非定额
	 * XmlElement:MILEAGE_DESCRIBE
	 */
//	private String mileageDescribe;
	/**
	 * 里程抵扣类型0：定额，1：非定额
	 * XmlElement:MILEAGE_TYPE
	 */
	@Tag(7025)
	private String mileageType;

	/**
	 * 里程免票所需里程
	 * XmlElement:MILEAGE_POINTS
	 */
	@Tag(7026)
	private String mileagePoints;
	/**
	 * 里程免票单程所需里程（往返有值）
	 * XmlElement:SEG_MILEAGE_POINTS
	 */
	@Tag(7027)
	private String segMileagePoints = "0";

	/**
	 * 直减金额
	 * XmlElement:CUT_PRICE
	 */
	@Tag(7031)
	private String cutPrice;
	
	/**
	 * 旅客信息填写页面
	 * XmlElement:PASSENGER_INFO_SHOW
	 */
	@Tag(7030)
	private String passengerInfoShow;
	
	/**
	 * 订单信息确认页面
	 * XmlElement:TICKET_INFO_SHOW
	 */
	@Tag(7029)
	private String ticketInfoShow;
	
	/**
	 * 航班选择页面
	 * XmlElement:CHOSE_FLIGHT_SHOW
	 */
	@Tag(7028)
	private String choseFlightShow;

	/**
	 * 机场接送
	 * XmlElement:AIRPORT_SHUTTLE
	 */
	@Tag(7032)
	private String airportShuttle;
	
	/**
	 * 是否采用新退改签规则
	 */
	@Tag(7045)
	private String isNewRefundReschedule;
	
	/**
	 * 飞行前2小时的退票金额
	 * XmlElement:MINREFUND_MONEY
	 */
	@Tag(7033)
	private String minRefundMoney;
	
	/**
	 * 飞行2小时(含)之后的退票金额
	 * XmlElement:MAXREFUND_MONEY
	 */
	@Tag(7034)
	private String maxRefundMoney;
	
	/**
	 * 飞行前2小时的改期金额
	 * XmlElement:MINRESCHEDULE_MONEY
	 */
	@Tag(7035)
	private String minRescheduleMoney;
	
	/**
	 * 自愿变更：航班规定离站时间前 7 天( 含) 之前
	 */
	@Tag(7037)
	private String beforeWeekRescheduleMoney;
	/**
	 * 自愿变更：航班规定离站时间前 7 天至72 小时( 含) 之前
	 */
	@Tag(7038)
	private String beforeDaysRescheduleMoney;
	/**
	 * 自愿变更：航班规定离站时间前 72 小时至4 小时( 含) 之前
	 */
	@Tag(7039)
	private String beforeHoursRescheduleMoney;
	/**
	 * 自愿变更：航班规定离站时间前 4 小时之后
	 */
	@Tag(7040)
	private String afterHoursRescheduleMoney;
	/**
	 * 自愿退票：航班规定离站时间 7 天( 含) 之前
	 */
	@Tag(7041)
	private String beforeWeekRefundMoney;
	/**
	 * 自愿退票：航班规定离站时间 7 天至72 小时( 含) 之前
	 */
	@Tag(7042)
	private String beforeDaysRefundMoney;
	/**
	 * 自愿退票：航班规定离站时间 72 小时至4 小时( 含) 之前
	 */
	@Tag(7043)
	private String beforeHoursRefundMoney;
	/**
	 * 自愿退票：航班规定离站时间 4 小时之后
	 */
	@Tag(7044)
	private String afterHoursRefundMoney;
	
	/**
	 * 飞行2小时(含)之后的改期金额
	 * XmlElement:MAXRESCHEDULE_MONEY
	 */
	@Tag(7036)
	private String maxRescheduleMoney;
   	
//	private String isSpPrice;
	
//	private List<ProductInfoVO> productInfoList;
	
//	private String spmealPropertyType;
//
//	private String spmealProductCode;
	
//	private String retrunFlag;
	/** 中转场合第一段价格 */
	@Tag(7050)
	private String classPrice1;
	/** 中转场合第二段价格 */
	@Tag(7051)
	private String classPrice2;
	/** 中转场合第一段折扣 */
	@Tag(7052)
	private String discount1;
	/** 中转场合第二段折扣 */
	@Tag(7053)
	private String discount2;
	/** 中转场合第一段航班号 */
	@Tag(7054)
	private String flightNo1;
	/** 中转场合第二段航班号*/
	@Tag(7055)
	private String flightNo2;
	/** 中转场合第一段舱位 */
	@Tag(7056)
	private String classCode1;
	/** 中转场合第二段舱位*/
	@Tag(7057)
	private String classCode2;

	/**
	 * 中转场合多条非中转场合单条
	 */
//	private List<ClassSubVO> classInfoSubList;
	@Tag(7047)
	private List<TaxVO> taxList;

}

