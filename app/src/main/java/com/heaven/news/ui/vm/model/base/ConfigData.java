package com.heaven.news.ui.vm.model.base;

import java.io.Serializable;
import java.util.List;

public class ConfigData implements Serializable {
	public int netCode = 0;
	public Version androidversion;
	public Version androidversionnew;
	public String message;
	public String wx_start;	//微信活动开始日期
	public String wx_end;		//微信活动结束日期
	public String shareTxt;	//分享用语
	public String shareTxt_en;
	public String shareWx;
	public String shareWx_en;
	public String shareWb;
	public String shareWb_en;
	public String sharePyq;
	public String sharePyq_en;
	public String ylywswitch;	//（易来易往开关） 定为0：关闭，1：打开
	public String ylywpic;		//（易来易往图片）
	public String ylywurl;		//（易来易往链接地址）
	public String myddc;		//评价服务
	public List<Airline> airlineList;	//航空公司
	public List<String> classCodeList;	//舱位
	public List<String> cityList;	//热门城市
	public String calendarUpdate;	//日历更新时间
	public String youzijiaUpdate;	//日历更新时间
	public String tcljUrl; // 添次良积
	public String cjbc;	//乘机泊车url
	public String ycfw;	//用车服务url
	public String zysc;	//知音商城url
	public String zysc_en;	//知音商城url
	public String lzzmurl;	//离站证明url
	public String yswpurl;	//遗失物品找回url
	public String tpfzsurl;	//退票费展示url
	public String wxpxzurl;	//危险品须知
	public String wxpxzurl_en;	//危险品须知
	public String tslvxzurl;	//特殊旅客
	public String tslvxzurl_en;	//特殊旅客
    public String hkcarorderurl;
    public String yryurl;

	public String swkurl;	//上网卡url
	public String swkurl_en;	//上网卡url
	public String cjbcurl;	//乘机泊车url
	public String cjbcurl_en;	//乘机泊车url
	public String yzjtyurl;	//悠自驾 桐叶url
	public String yzjtyurl_en;	//悠自驾 桐叶url
	public String yzjzzurl;	//悠自驾 至尊url
	public String yzjzzurl_en;	//悠自驾 至尊url
	public String jcjshhurl;	//机场接送 豪华url
	public String jcjshhurl_en;	//机场接送 豪华url
	public String jcjsjjurl;	//机场接送 经济url
	public String jcjsjjurl_en;	//机场接送 经济url
	public String shkxurl;		//深航快线
	public String zzgqurl;		//改期
	public String ywgqurl;		//延误改期
	public String zzgqxcurl;    //行程改期
	public String zzgqddurl;    //行程改期

	public String yzjjb;//悠自驾使用说明 精简
	public String yzjjb_en;//悠自驾使用说明 精简
	public String yzjxq;//悠自驾使用说明 完整
	public String yzjxq_en;//悠自驾使用说明 完整
	public String cjbcjb;//乘机泊车使用说明 精简
	public String cjbcjb_en;//乘机泊车使用说明 精简
	public String cjbcxq;//乘机泊车使用说明 完整
	public String cjbcxq_en;//乘机泊车使用说明 完整
	public String jcjsjb;//机场接送使用说明 精简
	public String jcjsjb_en;//机场接送使用说明 精简
	public String jcjsxq;//机场接送使用说明 完整
	public String jcjsxq_en;//机场接送使用说明 完整
	public String jwswjb;//境外上网卡使用说明 精简
	public String jwswjb_en;//境外上网卡使用说明 精简
	public String jwswxq;//境外上网卡使用说明 完整
	public String jwswxq_en;//境外上网卡使用说明 完整
	public String ksajjb;//快速安检使用说明 完整
	public String ksajjb_en;//快速安检使用说明 完整
	public String ksajxq;//快速安检使用说明 完整
	public String ksajxq_en;//快速安检使用说明 完整
	public String sakcjb;//港澳行-深澳快船简版说明
	public String sakcjb_en;//港澳行-深澳快船简版说明
	public String sakcxq;//港澳行-深澳快船详情说明
	public String sakcxq_en;//港澳行-深澳快船详情说明
	public String sgbsjb;//港澳行-深港巴士简版说明
	public String sgbsjb_en;//港澳行-深港巴士简版说明
	public String sgbsxq;//港澳行-深港巴士详情说明
	public String sgbsxq_en;//港澳行-深港巴士详情说明
	public String jplyjb; //精品旅游简版说明
	public String jplyjb_en; //精品旅游简版说明
	public String jplyhome;//精品旅游详情说明
	public String jplyhome_en;//精品旅游详情说明
	public String zhongzzs;//中转住宿-普通
	public String zhongzzs_en;//中转住宿-普通英
	public String zhongzzsvip;//中转住宿-vip
	public String zhongzzsvip_en;//中转住宿-vip英
	public String yeeCard;
	public String isnewfare;//f改c开关 close 旧规则 其它新规则

	//奖励客票
    public boolean jlkpswitch = true;
    public String jlkpurl;
    public String internationalInformationUrl;
    public String internationalInformationUrl_en;
    public String internalInformationUrl;
    public String internalInformationUrl_en;

	public boolean jgrlswitch;	//价格日历开关，0是关，1是开
	public String jgrlurl;

	public String onlineserviceurl;

	public String cjbchome;
	public String cjbchome_en;
	public String jcjshome;

	public String jcjshome_en;
	public String jwswhome;
	public String jwswhome_en;
	public String ksajhome;
	public String ksajhome_en;
	public String yzjhome;
	public String yzjhome_en;
	public String gaxhome;
	public String gaxhome_en;

	public String insuranceurl;
	public String insuranceurl_en;
	public String shjjsmurl;
	public String shjjsmurl_en;
	public String tscssmurl;
	public String tscssmurl_en;
	public String zcxyurl;
	public String zcxyurl_en;
	public String zjxyurl;
	public String zjxyurl_en;
	public String lcdksmurl;
	public String lcdksmurl_en;
	public String tscstxt;
	public String tscstxt_en;
	public String jjcgpurl;
	public String bizStoreUrl;
	public String mmbkurl;

	public String hbdturl;//航班动态url
	public String jcjturl;//机场交通url
	public String ssrsmurl;//受让人url
	public String ssrsmurl_en;//受让人url
	public String cybzurl;//乘机帮助url
	public String cybzurl_en;//乘机帮助url
    public String yxpjurl;//易行评价
    public String yxpjurl_en;//易行评价
    public String insuranceswitch;//保险默认值
    public String dcxyurl;//头等舱协议
    public String dcxyurl_en;//头等舱协议
    public String yxpjswitch;//易行评价
	public String djkscsmurl;//登机口升舱
	public String djkscsmurl_en;//登机口升舱
	public String djkschome;//登机口升舱
	public String djkschome_en;//登机口升舱


	public String yjpjurl;//有奖评价

	public String tslkurl;//特殊旅客
	public String jdydurl;//酒店预定
	public String gdbhome;//一票多场
	public String gdbhome_en;//一票多场

	public String sourceSpringStart;
	public String sourceSpringEnd;
	public String sourceSummerStart;
	public String sourceSummerEnd;

	public String targetSpringStart;
	public String targetSpringEnd;
	public String targetSummerStart;
	public String targetSummerEnd;

	public String ylywGoStartDate;
	public String ylywGoEndDate;
	public String ylywBakcStartDate;
	public String ylywBackEndDate;
	public String ylywsmurl;
	public String xlgmhome;
	public List<String> ylywCityList;

	public String ffxlgn;//额外行李国内说明
	public String ffxlgj;//额外行李国际说明

	public String ystzurl;//隐私通知
	public String ystzurl_en;//隐私通知英文

	public String jcjssnhome;//机场接送市内的key
	public String jcjskchome;//机场接送跨城的key是jcjskchome

	public String checkinProtocolUrl;//值机协议中文
	public String checkinProtocolEnUrl;//值机协议英文
	public String showAuthAlert;//实名认证享福利开关

	public String jcjsAppend = "https://mobile.shenzhenair.com/file/develop/jxjsMini.html";

	public static class Airline implements  Serializable{
		private String code;
		private String text;
		private String en;
		public Airline(String code, String text, String en){
			this.code = code;
			this.text = text;
			this.en = en;
		}

		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public void setEn(String en) {
			this.en = en;
		}
		public String getEn() {
			return en;
		}
	}


	@Override
	public String toString() {
		return "ConfigData{" +
				"netCode=" + netCode +
				", message='" + message + '\'' +
				", wx_start='" + wx_start + '\'' +
				", wx_end='" + wx_end + '\'' +
				", shareTxt='" + shareTxt + '\'' +
				", shareTxt_en='" + shareTxt_en + '\'' +
				", shareWx='" + shareWx + '\'' +
				", shareWx_en='" + shareWx_en + '\'' +
				", shareWb='" + shareWb + '\'' +
				", shareWb_en='" + shareWb_en + '\'' +
				", sharePyq='" + sharePyq + '\'' +
				", sharePyq_en='" + sharePyq_en + '\'' +
				", ylywswitch='" + ylywswitch + '\'' +
				", ylywpic='" + ylywpic + '\'' +
				", ylywurl='" + ylywurl + '\'' +
				", myddc='" + myddc + '\'' +
				", airlineList=" + airlineList +
				", classCodeList=" + classCodeList +
				", cityList=" + cityList +
				", calendarUpdate='" + calendarUpdate + '\'' +
				", youzijiaUpdate='" + youzijiaUpdate + '\'' +
				", tcljUrl='" + tcljUrl + '\'' +
				", cjbc='" + cjbc + '\'' +
				", ycfw='" + ycfw + '\'' +
				", zysc='" + zysc + '\'' +
				", zysc_en='" + zysc_en + '\'' +
				", lzzmurl='" + lzzmurl + '\'' +
				", yswpurl='" + yswpurl + '\'' +
				", tpfzsurl='" + tpfzsurl + '\'' +
				", wxpxzurl='" + wxpxzurl + '\'' +
				", wxpxzurl_en='" + wxpxzurl_en + '\'' +
				", tslvxzurl='" + tslvxzurl + '\'' +
				", tslvxzurl_en='" + tslvxzurl_en + '\'' +
				", hkcarorderurl='" + hkcarorderurl + '\'' +
				", yryurl='" + yryurl + '\'' +
				", swkurl='" + swkurl + '\'' +
				", swkurl_en='" + swkurl_en + '\'' +
				", cjbcurl='" + cjbcurl + '\'' +
				", cjbcurl_en='" + cjbcurl_en + '\'' +
				", yzjtyurl='" + yzjtyurl + '\'' +
				", yzjtyurl_en='" + yzjtyurl_en + '\'' +
				", yzjzzurl='" + yzjzzurl + '\'' +
				", yzjzzurl_en='" + yzjzzurl_en + '\'' +
				", jcjshhurl='" + jcjshhurl + '\'' +
				", jcjshhurl_en='" + jcjshhurl_en + '\'' +
				", jcjsjjurl='" + jcjsjjurl + '\'' +
				", jcjsjjurl_en='" + jcjsjjurl_en + '\'' +
				", shkxurl='" + shkxurl + '\'' +
				", zzgqurl='" + zzgqurl + '\'' +
				", ywgqurl='" + ywgqurl + '\'' +
				", zzgqxcurl='" + zzgqxcurl + '\'' +
				", zzgqddurl='" + zzgqddurl + '\'' +
				", yzjjb='" + yzjjb + '\'' +
				", yzjjb_en='" + yzjjb_en + '\'' +
				", yzjxq='" + yzjxq + '\'' +
				", yzjxq_en='" + yzjxq_en + '\'' +
				", cjbcjb='" + cjbcjb + '\'' +
				", cjbcjb_en='" + cjbcjb_en + '\'' +
				", cjbcxq='" + cjbcxq + '\'' +
				", cjbcxq_en='" + cjbcxq_en + '\'' +
				", jcjsjb='" + jcjsjb + '\'' +
				", jcjsjb_en='" + jcjsjb_en + '\'' +
				", jcjsxq='" + jcjsxq + '\'' +
				", jcjsxq_en='" + jcjsxq_en + '\'' +
				", jwswjb='" + jwswjb + '\'' +
				", jwswjb_en='" + jwswjb_en + '\'' +
				", jwswxq='" + jwswxq + '\'' +
				", jwswxq_en='" + jwswxq_en + '\'' +
				", ksajjb='" + ksajjb + '\'' +
				", ksajjb_en='" + ksajjb_en + '\'' +
				", ksajxq='" + ksajxq + '\'' +
				", ksajxq_en='" + ksajxq_en + '\'' +
				", sakcjb='" + sakcjb + '\'' +
				", sakcjb_en='" + sakcjb_en + '\'' +
				", sakcxq='" + sakcxq + '\'' +
				", sakcxq_en='" + sakcxq_en + '\'' +
				", sgbsjb='" + sgbsjb + '\'' +
				", sgbsjb_en='" + sgbsjb_en + '\'' +
				", sgbsxq='" + sgbsxq + '\'' +
				", sgbsxq_en='" + sgbsxq_en + '\'' +
				", jplyjb='" + jplyjb + '\'' +
				", jplyjb_en='" + jplyjb_en + '\'' +
				", jplyhome='" + jplyhome + '\'' +
				", jplyhome_en='" + jplyhome_en + '\'' +
				", zhongzzs='" + zhongzzs + '\'' +
				", zhongzzs_en='" + zhongzzs_en + '\'' +
				", zhongzzsvip='" + zhongzzsvip + '\'' +
				", zhongzzsvip_en='" + zhongzzsvip_en + '\'' +
				", yeeCard='" + yeeCard + '\'' +
				", isnewfare='" + isnewfare + '\'' +
				", jlkpswitch=" + jlkpswitch +
				", jlkpurl='" + jlkpurl + '\'' +
				", internationalInformationUrl='" + internationalInformationUrl + '\'' +
				", internationalInformationUrl_en='" + internationalInformationUrl_en + '\'' +
				", internalInformationUrl='" + internalInformationUrl + '\'' +
				", internalInformationUrl_en='" + internalInformationUrl_en + '\'' +
				", jgrlswitch=" + jgrlswitch +
				", jgrlurl='" + jgrlurl + '\'' +
				", onlineserviceurl='" + onlineserviceurl + '\'' +
				", cjbchome='" + cjbchome + '\'' +
				", cjbchome_en='" + cjbchome_en + '\'' +
				", jcjshome='" + jcjshome + '\'' +
				", jcjshome_en='" + jcjshome_en + '\'' +
				", jwswhome='" + jwswhome + '\'' +
				", jwswhome_en='" + jwswhome_en + '\'' +
				", ksajhome='" + ksajhome + '\'' +
				", ksajhome_en='" + ksajhome_en + '\'' +
				", yzjhome='" + yzjhome + '\'' +
				", yzjhome_en='" + yzjhome_en + '\'' +
				", gaxhome='" + gaxhome + '\'' +
				", gaxhome_en='" + gaxhome_en + '\'' +
				", insuranceurl='" + insuranceurl + '\'' +
				", insuranceurl_en='" + insuranceurl_en + '\'' +
				", shjjsmurl='" + shjjsmurl + '\'' +
				", shjjsmurl_en='" + shjjsmurl_en + '\'' +
				", tscssmurl='" + tscssmurl + '\'' +
				", tscssmurl_en='" + tscssmurl_en + '\'' +
				", zcxyurl='" + zcxyurl + '\'' +
				", zcxyurl_en='" + zcxyurl_en + '\'' +
				", zjxyurl='" + zjxyurl + '\'' +
				", zjxyurl_en='" + zjxyurl_en + '\'' +
				", lcdksmurl='" + lcdksmurl + '\'' +
				", lcdksmurl_en='" + lcdksmurl_en + '\'' +
				", tscstxt='" + tscstxt + '\'' +
				", tscstxt_en='" + tscstxt_en + '\'' +
				", jjcgpurl='" + jjcgpurl + '\'' +
				", bizStoreUrl='" + bizStoreUrl + '\'' +
				", mmbkurl='" + mmbkurl + '\'' +
				", hbdturl='" + hbdturl + '\'' +
				", jcjturl='" + jcjturl + '\'' +
				", ssrsmurl='" + ssrsmurl + '\'' +
				", ssrsmurl_en='" + ssrsmurl_en + '\'' +
				", cybzurl='" + cybzurl + '\'' +
				", cybzurl_en='" + cybzurl_en + '\'' +
				", yxpjurl='" + yxpjurl + '\'' +
				", yxpjurl_en='" + yxpjurl_en + '\'' +
				", insuranceswitch='" + insuranceswitch + '\'' +
				", dcxyurl='" + dcxyurl + '\'' +
				", dcxyurl_en='" + dcxyurl_en + '\'' +
				", yxpjswitch='" + yxpjswitch + '\'' +
				", djkscsmurl='" + djkscsmurl + '\'' +
				", djkscsmurl_en='" + djkscsmurl_en + '\'' +
				", djkschome='" + djkschome + '\'' +
				", djkschome_en='" + djkschome_en + '\'' +
				", yjpjurl='" + yjpjurl + '\'' +
				", tslkurl='" + tslkurl + '\'' +
				", jdydurl='" + jdydurl + '\'' +
				", gdbhome='" + gdbhome + '\'' +
				", gdbhome_en='" + gdbhome_en + '\'' +
				", sourceSpringStart='" + sourceSpringStart + '\'' +
				", sourceSpringEnd='" + sourceSpringEnd + '\'' +
				", sourceSummerStart='" + sourceSummerStart + '\'' +
				", sourceSummerEnd='" + sourceSummerEnd + '\'' +
				", targetSpringStart='" + targetSpringStart + '\'' +
				", targetSpringEnd='" + targetSpringEnd + '\'' +
				", targetSummerStart='" + targetSummerStart + '\'' +
				", targetSummerEnd='" + targetSummerEnd + '\'' +
				", ylywGoStartDate='" + ylywGoStartDate + '\'' +
				", ylywGoEndDate='" + ylywGoEndDate + '\'' +
				", ylywBakcStartDate='" + ylywBakcStartDate + '\'' +
				", ylywBackEndDate='" + ylywBackEndDate + '\'' +
				", ylywsmurl='" + ylywsmurl + '\'' +
				", xlgmhome='" + xlgmhome + '\'' +
				", ylywCityList=" + ylywCityList +
				", ffxlgn='" + ffxlgn + '\'' +
				", ffxlgj='" + ffxlgj + '\'' +
				", ystzurl='" + ystzurl + '\'' +
				", ystzurl_en='" + ystzurl_en + '\'' +
				", jcjssnhome='" + jcjssnhome + '\'' +
				", jcjskchome='" + jcjskchome + '\'' +
				", checkinProtocolUrl='" + checkinProtocolUrl + '\'' +
				", checkinProtocolEnUrl='" + checkinProtocolEnUrl + '\'' +
				", showAuthAlert='" + showAuthAlert + '\'' +
				", jcjsAppend='" + jcjsAppend + '\'' +
				'}';
	}
}
