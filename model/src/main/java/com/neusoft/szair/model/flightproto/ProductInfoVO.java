package com.neusoft.szair.model.flightproto;

import java.util.Map;


public class ProductInfoVO {

	/**
	 * 产品编号
	 */
	private String productCode;

	/**
	 * 产品类型
	 */
	private String productType;

	/**
	 * 产品名称
	 */
	private String name;

	/**
	 * 产品描述
	 */
	private String description;
	
	/**
	 * 产品创建日期
	 */
	private String createDate;

	/**
	 * 产品验证使用
	 * 产品类型：1-赠送,2-使用
	 */
	private String giveFlag;
	
	/**
	 * 产品验证使用
	 * 产品使用验证结果：1-通过,2-不通过
	 */
	private String validateResult;
	
	/**
	 * 产品验证使用
	 * 产品使用验证结果描述
	 */
	private String resultMsg;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGiveFlag() {
		return giveFlag;
	}

	public void setGiveFlag(String giveFlag) {
		this.giveFlag = giveFlag;
	}

	public String getValidateResult() {
		return validateResult;
	}

	public void setValidateResult(String validateResult) {
		this.validateResult = validateResult;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
