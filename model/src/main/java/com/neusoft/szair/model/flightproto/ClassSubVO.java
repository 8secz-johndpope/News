package com.neusoft.szair.model.flightproto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.shenzhenair.common.Mapping;

public class ClassSubVO {
	/**
	 * 运价编码
	 * XmlElement:FLIGHT_NO
	 */
	private String flightNo;
	/**
	 * 运价编码
	 * XmlElement:SPOLCODE
	 */
	private String spolCode;
	/**
	 * 舱位代码
	 * XmlElement:CLASS_CODE
	 */
	private String classCode;
	/**
	 * 舱位折扣
	 * XmlElement:DISCOUNT
	 */
	private String discount;
	
	private String ei;
	private String tc;
	private String fareBase;// 运价基础
	private String minRefundRate;
	private String maxRefundRate;
	private String minRescheduleRate;
	private String maxRescheduleRate;

	/**
	 * 自愿变更：航班规定离站时间前 7 天( 含) 之前
	 */
	private String beforeWeekReschedule;
	/**
	 * 自愿变更：航班规定离站时间前 7 天至72 小时( 含) 之前
	 */
	private String beforeDaysReschedule;  
	/**
	 * 自愿变更：航班规定离站时间前 72 小时至4 小时( 含) 之前
	 */
	private String beforeHoursReschedule;   
	/**
	 * 自愿变更：航班规定离站时间前 4 小时之后
	 */
	private String afterHoursReschedule;   
	/**
	 * 自愿退票：航班规定离站时间 7 天( 含) 之前
	 */
	private String beforeWeekRefund;  
	/**
	 * 自愿退票：航班规定离站时间 7 天至72 小时( 含) 之前
	 */
	private String beforeDaysRefund;   
	/**
	 * 自愿退票：航班规定离站时间 72 小时至4 小时( 含) 之前
	 */
	private String beforeHoursRefund;   
	/**
	 * 自愿退票：航班规定离站时间 4 小时之后
	 */
	private String afterHoursRefund;
	
	private List<TaxVO> taxList;
	
	/**
	 * 舱位折扣
	 * @return String 舱位折扣
	 * XmlElement:DISCOUNT
	 */
	@XmlElement(name="DISCOUNT")
	public String getDiscount(){
		return this.discount;
	}
	/**
	 * 舱位折扣
	 * @param discount 舱位折扣
	 * XmlElement:DISCOUNT
	 */
	public void setDiscount(String discount){
		this.discount = discount;
	}
	@XmlElement(name="MIN_REFUND_RATE")
	public String getMinRefundRate() {
		return minRefundRate;
	}
	public void setMinRefundRate(String minRefundRate) {
		this.minRefundRate = minRefundRate;
	}
	@XmlElement(name="MAX_REFUND_RATE")
	public String getMaxRefundRate() {
		return maxRefundRate;
	}
	public void setMaxRefundRate(String maxRefundRate) {
		this.maxRefundRate = maxRefundRate;
	}
	@XmlElement(name="MIN_RESCHEDULE_RATE")
	public String getMinRescheduleRate() {
		return minRescheduleRate;
	}
	public void setMinRescheduleRate(String minRescheduleRate) {
		this.minRescheduleRate = minRescheduleRate;
	}
	@XmlElement(name="MAX_RESCHEDULE_RATE")
	public String getMaxRescheduleRate() {
		return maxRescheduleRate;
	}
	public void setMaxRescheduleRate(String maxRescheduleRate) {
		this.maxRescheduleRate = maxRescheduleRate;
	}
	@XmlElement(name="FLIGHT_NO")
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	@XmlElement(name="SPOL_CODE")
	public String getSpolCode() {
		return spolCode;
	}
	public void setSpolCode(String spolCode) {
		this.spolCode = spolCode;
	}
	/**
     * 舱位代码
     * @return String 舱位代码
     * XmlElement:CLASS_CODE
     */
    @XmlElement(name="CLASS_CODE")
    public String getClassCode(){
        return this.classCode;
    }
    /**
     * 舱位代码
     * @param classCode 舱位代码
     * XmlElement:CLASS_CODE
     */
    @Mapping("classCode")
    public void setClassCode(String classCode){
        this.classCode = classCode;
    }
	@XmlElement(name="BEFORE_WEEK_RESCHEDULE")
	public String getBeforeWeekReschedule() {
		return beforeWeekReschedule;
	}
	public void setBeforeWeekReschedule(String beforeWeekReschedule) {
		this.beforeWeekReschedule = beforeWeekReschedule;
	}
	@XmlElement(name="BEFORE_DAYS_RESCHEDULE")
	public String getBeforeDaysReschedule() {
		return beforeDaysReschedule;
	}
	public void setBeforeDaysReschedule(String beforeDaysReschedule) {
		this.beforeDaysReschedule = beforeDaysReschedule;
	}
	@XmlElement(name="BEFORE_HOURS_RESCHEDULE")
	public String getBeforeHoursReschedule() {
		return beforeHoursReschedule;
	}
	public void setBeforeHoursReschedule(String beforeHoursReschedule) {
		this.beforeHoursReschedule = beforeHoursReschedule;
	}
	@XmlElement(name="AFTER_HOURS_RESCHEDULE")
	public String getAfterHoursReschedule() {
		return afterHoursReschedule;
	}
	public void setAfterHoursReschedule(String afterHoursReschedule) {
		this.afterHoursReschedule = afterHoursReschedule;
	}
	@XmlElement(name="BEFORE_WEEK_REFUND")
	public String getBeforeWeekRefund() {
		return beforeWeekRefund;
	}
	public void setBeforeWeekRefund(String beforeWeekRefund) {
		this.beforeWeekRefund = beforeWeekRefund;
	}
	@XmlElement(name="BEFORE_DAYS_REFUND")
	public String getBeforeDaysRefund() {
		return beforeDaysRefund;
	}
	public void setBeforeDaysRefund(String beforeDaysRefund) {
		this.beforeDaysRefund = beforeDaysRefund;
	}
	@XmlElement(name="BEFORE_HOURS_REFUND")
	public String getBeforeHoursRefund() {
		return beforeHoursRefund;
	}
	public void setBeforeHoursRefund(String beforeHoursRefund) {
		this.beforeHoursRefund = beforeHoursRefund;
	}
	@XmlElement(name="AFTER_HOURS_REFUND")
	public String getAfterHoursRefund() {
		return afterHoursRefund;
	}
	public void setAfterHoursRefund(String afterHoursRefund) {
		this.afterHoursRefund = afterHoursRefund;
	}

	@XmlElement(name="TAX_LIST")
	public List<TaxVO> getTaxList() {
		return taxList;
	}
	public void setTaxList(List<TaxVO> taxList) {
		this.taxList = taxList;
	}
	@XmlElement(name="EI")
	public String getEi() {
		return ei;
	}
	public void setEi(String ei) {
		this.ei = ei;
	}
	@XmlElement(name="TC")
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	@XmlElement(name="FARE_BASE")
	public String getFareBase() {
		return fareBase;
	}
	public void setFareBase(String fareBase) {
		this.fareBase = fareBase;
	}
}
