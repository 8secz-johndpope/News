package com.neusoft.szair.model.flightproto;

import java.util.List;


import io.protostuff.Tag;


public class FlightSearchDomesticResultVO {
	/**
	 * WS执行结果
	 * 0 - 正常结束
	 * 1 - 参数错误
	 * 2 - SESSION超时
	 * 3 - 无结果
	 * 9 - 系统异常
	 */
	@Tag(1001)
	private String opResult = "0";
	/**
	 * 航程类型（DC、ZZ、WF、DD）
	 * XmlElement:HC_TYPE
	 */
	@Tag(1002)
	private String hcType;
	/**
	 * 常客会员价一键预定展示标识
	 * XmlElement:CRM_YJYD
	 */
	@Tag(1003)
	private String crmYJYD;
	/**
	 * 国航查询ID
	 * XmlElement:CA_SEARCH_ID
	 */
	@Tag(1004)
	private String caSearchId;
	
	/**
	 * 航程信息集合
	 * 单程场合：一条
	 * 中转场合：一条
	 * 往返场合：二条
	 * 多段场合：几段几条
	 * XmlElement:FLIGHT_INFO_SEGMENT_LIST
	 */
	@Tag(1005)
	private List<FlightInfoSegmentVO> flightInfoSegmentList;
	/**
	 * 错误信息
	 * XmlElement:RESULT_MSG
	 */
	@Tag(1006)
	private String resultMsg;
}
