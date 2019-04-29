package com.heaven.news.ui.vm.model.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author heaven
 */
public class ConfigData implements Serializable {
	public static final long serialVersionUID = -8241685949732527971L;
	public int netCode = 0;
	public String message;
    public String wx_start;
    public String wx_end;
    public String shareTxt;
    public String shareWx;
    public String shareWb;
    public String sharePyq;
    public String tscstxt;
    public String android_service_url;
    public String onlineserviceurl;
    public String insuranceswitch;
    public String ylywpic;
    public String ylywurl;
    public String ylywswitch;
    public String ylywsmurl;
    public String jlkpswitch;
    public String jlkpurl;
    public String yxpjswitch;
    public String jgrlswitch;
    public String jgrlurl;
    public String myddc;
    public String yjpjurl;
    public String bizStoreUrl;
    public String jjcgpurl;
    public String calendarUpdate;
    public String calendarurl;
    public String youzijiaUpdate;
    public String youzijiaurl;
    public String tcljUrl;
    public String yryurl;
    public String zysc;
    public String lzzmurl;
    public String yswpurl;
    public String tpfzsurl;
    public String swkurl;
    public String cjbcurl;
    public String yzjtyurl;
    public String yzjzzurl;
    public String jcjshhurl;
    public String jcjsjjurl;
    public String zzgqurl;
    public String zzgqxcurl;
    public String zzgqddurl;
    public String ywgqurl;
    public String shkxurl;
    public String hbdturl;
    public String tslkurl;
    public String yxpjurl;
    public String jdydurl;
    public String shwburl;
    public String ssrsmurl;
    public String dcxyurl;
    public String cybzurl;
    public String wxpxzurl;
    public String internationalInformationUrl;
    public String internalInformationUrl;
    public String ticketValidityMessageUrl;
    public String lcdksmurl;
    public String cjbchome;
    public String cjbcjb;
    public String cjbcxq;
    public String jcjshome;
    public String jcjssnhome;
    public String jcjskchome;
    public String jcjsjb;
    public String jcjsxq;
    public String cjbshome;
    public String cjbsjb;
    public String cjbsxq;
    public String jwswhome;
    public String jwswjb;
    public String jwswxq;
    public String ksajhome;
    public String ksajhome500;
    public String ksajjb;
    public String ksajjb500;
    public String ksajxq;
    public String ksajxq500;
    public String yzjhome;
    public String yzjjb;
    public String yzjxq;
    public String gaxhome;
    public String sakcjb;
    public String sakcxq;
    public String hkcarorderurl;
    public String sgbsjb;
    public String sgbsxq;
    public String shkxhome;
    public String djkscsmurl;
    public String insuranceurl;
    public String shjjsmurl;
    public String tscssmurl;
    public String zcxyurl;
    public String zjxyurl;
    public String ystzurl;
    public String gdbhome;
    public String tslkwpet;
    public String tslkly;
    public String tslklymr;
    public String tslkyf;
    public String tslvxzurl;
    public String ffxlgn;
    public String ffxlgj;
    public String loadJS;
    public String djkschome;
    public String xlgmhome;
    public Version androidversion;
    public Version androidversionnew;
    public TimeStamp timestamp;
    public String tscstxt_en;
    public String shareTxt_en;
    public String shareWx_en;
    public String shareWb_en;
    public String sharePyq_en;
    public String shwburl_en;
    public String zysc_en;
    public String swkurl_en;
    public String cjbcurl_en;
    public String yzjtyurl_en;
    public String yzjzzurl_en;
    public String jcjshhurl_en;
    public String jcjsjjurl_en;
    public String ssrsmurl_en;
    public String dcxyurl_en;
    public String cybzurl_en;
    public String wxpxzurl_en;
    public String internationalInformationUrl_en;
    public String internalInformationUrl_en;
    public String lcdksmurl_en;
    public String cjbchome_en;
    public String cjbcjb_en;
    public String cjbcxq_en;
    public String jcjshome_en;
    public String jcjsjb_en;
    public String jcjsxq_en;
    public String jwswhome_en;
    public String jwswjb_en;
    public String jwswxq_en;
    public String ksajhome_en;
    public String ksajhome_en500;
    public String ksajjb_en;
    public String ksajjb_en500;
    public String ksajxq_en;
    public String ksajxq_en500;
    public String yzjhome_en;
    public String yzjjb_en;
    public String yzjxq_en;
    public String gaxhome_en;
    public String sakcjb_en;
    public String sakcxq_en;
    public String sgbsjb_en;
    public String sgbsxq_en;
    public String shkxhome_en;
    public String djkscsmurl_en;
    public String insuranceurl_en;
    public String shjjsmurl_en;
    public String tscssmurl_en;
    public String zcxyurl_en;
    public String zjxyurl_en;
    public String ystzurl_en;
    public String gdbhome_en;
    public String tslvxzurl_en;
    public String djkschome_en;
    public String checkinProtocolUrl;
    public String checkinProtocolEnUrl;
    public String jplyhome;
    public String jplyjb;
    public String mmbkurl;
    public String yeeCard;
    public String jplyxq;
    public String isnewfare;
    public String zhongzzs;
    public String zhongzzs_en;
    public String zhongzzsvip;
    public String zhongzzsvip_en;
    public String showAuthAlert;
    public String hyzxurl;
    public String wxpcxurl;
    public List<String> city;
    public List<Airline> airline;
    public List<String> classCode;
    public List<String> ylywDate;
    public List<String> ylywCity;

	public static class Airline implements  Serializable{
		public String code;
		public String text;
		public String en;
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
                ", wx_start='" + wx_start + '\'' +
                ", wx_end='" + wx_end + '\'' +
                ", shareTxt='" + shareTxt + '\'' +
                ", shareWx='" + shareWx + '\'' +
                ", shareWb='" + shareWb + '\'' +
                ", sharePyq='" + sharePyq + '\'' +
                ", tscstxt='" + tscstxt + '\'' +
                ", android_service_url='" + android_service_url + '\'' +
                ", onlineserviceurl='" + onlineserviceurl + '\'' +
                ", insuranceswitch='" + insuranceswitch + '\'' +
                ", ylywpic='" + ylywpic + '\'' +
                ", ylywurl='" + ylywurl + '\'' +
                ", ylywswitch='" + ylywswitch + '\'' +
                ", ylywsmurl='" + ylywsmurl + '\'' +
                ", jlkpswitch='" + jlkpswitch + '\'' +
                ", jlkpurl='" + jlkpurl + '\'' +
                ", yxpjswitch='" + yxpjswitch + '\'' +
                ", jgrlswitch='" + jgrlswitch + '\'' +
                ", jgrlurl='" + jgrlurl + '\'' +
                ", myddc='" + myddc + '\'' +
                ", yjpjurl='" + yjpjurl + '\'' +
                ", bizStoreUrl='" + bizStoreUrl + '\'' +
                ", jjcgpurl='" + jjcgpurl + '\'' +
                ", calendarUpdate='" + calendarUpdate + '\'' +
                ", calendarurl='" + calendarurl + '\'' +
                ", youzijiaUpdate='" + youzijiaUpdate + '\'' +
                ", youzijiaurl='" + youzijiaurl + '\'' +
                ", tcljUrl='" + tcljUrl + '\'' +
                ", yryurl='" + yryurl + '\'' +
                ", zysc='" + zysc + '\'' +
                ", lzzmurl='" + lzzmurl + '\'' +
                ", yswpurl='" + yswpurl + '\'' +
                ", tpfzsurl='" + tpfzsurl + '\'' +
                ", swkurl='" + swkurl + '\'' +
                ", cjbcurl='" + cjbcurl + '\'' +
                ", yzjtyurl='" + yzjtyurl + '\'' +
                ", yzjzzurl='" + yzjzzurl + '\'' +
                ", jcjshhurl='" + jcjshhurl + '\'' +
                ", jcjsjjurl='" + jcjsjjurl + '\'' +
                ", zzgqurl='" + zzgqurl + '\'' +
                ", zzgqxcurl='" + zzgqxcurl + '\'' +
                ", zzgqddurl='" + zzgqddurl + '\'' +
                ", ywgqurl='" + ywgqurl + '\'' +
                ", shkxurl='" + shkxurl + '\'' +
                ", hbdturl='" + hbdturl + '\'' +
                ", tslkurl='" + tslkurl + '\'' +
                ", yxpjurl='" + yxpjurl + '\'' +
                ", jdydurl='" + jdydurl + '\'' +
                ", shwburl='" + shwburl + '\'' +
                ", ssrsmurl='" + ssrsmurl + '\'' +
                ", dcxyurl='" + dcxyurl + '\'' +
                ", cybzurl='" + cybzurl + '\'' +
                ", wxpxzurl='" + wxpxzurl + '\'' +
                ", internationalInformationUrl='" + internationalInformationUrl + '\'' +
                ", internalInformationUrl='" + internalInformationUrl + '\'' +
                ", ticketValidityMessageUrl='" + ticketValidityMessageUrl + '\'' +
                ", lcdksmurl='" + lcdksmurl + '\'' +
                ", cjbchome='" + cjbchome + '\'' +
                ", cjbcjb='" + cjbcjb + '\'' +
                ", cjbcxq='" + cjbcxq + '\'' +
                ", jcjshome='" + jcjshome + '\'' +
                ", jcjssnhome='" + jcjssnhome + '\'' +
                ", jcjskchome='" + jcjskchome + '\'' +
                ", jcjsjb='" + jcjsjb + '\'' +
                ", jcjsxq='" + jcjsxq + '\'' +
                ", cjbshome='" + cjbshome + '\'' +
                ", cjbsjb='" + cjbsjb + '\'' +
                ", cjbsxq='" + cjbsxq + '\'' +
                ", jwswhome='" + jwswhome + '\'' +
                ", jwswjb='" + jwswjb + '\'' +
                ", jwswxq='" + jwswxq + '\'' +
                ", ksajhome='" + ksajhome + '\'' +
                ", ksajhome500='" + ksajhome500 + '\'' +
                ", ksajjb='" + ksajjb + '\'' +
                ", ksajjb500='" + ksajjb500 + '\'' +
                ", ksajxq='" + ksajxq + '\'' +
                ", ksajxq500='" + ksajxq500 + '\'' +
                ", yzjhome='" + yzjhome + '\'' +
                ", yzjjb='" + yzjjb + '\'' +
                ", yzjxq='" + yzjxq + '\'' +
                ", gaxhome='" + gaxhome + '\'' +
                ", sakcjb='" + sakcjb + '\'' +
                ", sakcxq='" + sakcxq + '\'' +
                ", hkcarorderurl='" + hkcarorderurl + '\'' +
                ", sgbsjb='" + sgbsjb + '\'' +
                ", sgbsxq='" + sgbsxq + '\'' +
                ", shkxhome='" + shkxhome + '\'' +
                ", djkscsmurl='" + djkscsmurl + '\'' +
                ", insuranceurl='" + insuranceurl + '\'' +
                ", shjjsmurl='" + shjjsmurl + '\'' +
                ", tscssmurl='" + tscssmurl + '\'' +
                ", zcxyurl='" + zcxyurl + '\'' +
                ", zjxyurl='" + zjxyurl + '\'' +
                ", ystzurl='" + ystzurl + '\'' +
                ", gdbhome='" + gdbhome + '\'' +
                ", tslkwpet='" + tslkwpet + '\'' +
                ", tslkly='" + tslkly + '\'' +
                ", tslklymr='" + tslklymr + '\'' +
                ", tslkyf='" + tslkyf + '\'' +
                ", tslvxzurl='" + tslvxzurl + '\'' +
                ", ffxlgn='" + ffxlgn + '\'' +
                ", ffxlgj='" + ffxlgj + '\'' +
                ", loadJS='" + loadJS + '\'' +
                ", djkschome='" + djkschome + '\'' +
                ", xlgmhome='" + xlgmhome + '\'' +
                ", androidversion=" + androidversion +
                ", androidversionnew=" + androidversionnew +
                ", timestamp=" + timestamp +
                ", tscstxt_en='" + tscstxt_en + '\'' +
                ", shareTxt_en='" + shareTxt_en + '\'' +
                ", shareWx_en='" + shareWx_en + '\'' +
                ", shareWb_en='" + shareWb_en + '\'' +
                ", sharePyq_en='" + sharePyq_en + '\'' +
                ", shwburl_en='" + shwburl_en + '\'' +
                ", zysc_en='" + zysc_en + '\'' +
                ", swkurl_en='" + swkurl_en + '\'' +
                ", cjbcurl_en='" + cjbcurl_en + '\'' +
                ", yzjtyurl_en='" + yzjtyurl_en + '\'' +
                ", yzjzzurl_en='" + yzjzzurl_en + '\'' +
                ", jcjshhurl_en='" + jcjshhurl_en + '\'' +
                ", jcjsjjurl_en='" + jcjsjjurl_en + '\'' +
                ", ssrsmurl_en='" + ssrsmurl_en + '\'' +
                ", dcxyurl_en='" + dcxyurl_en + '\'' +
                ", cybzurl_en='" + cybzurl_en + '\'' +
                ", wxpxzurl_en='" + wxpxzurl_en + '\'' +
                ", internationalInformationUrl_en='" + internationalInformationUrl_en + '\'' +
                ", internalInformationUrl_en='" + internalInformationUrl_en + '\'' +
                ", lcdksmurl_en='" + lcdksmurl_en + '\'' +
                ", cjbchome_en='" + cjbchome_en + '\'' +
                ", cjbcjb_en='" + cjbcjb_en + '\'' +
                ", cjbcxq_en='" + cjbcxq_en + '\'' +
                ", jcjshome_en='" + jcjshome_en + '\'' +
                ", jcjsjb_en='" + jcjsjb_en + '\'' +
                ", jcjsxq_en='" + jcjsxq_en + '\'' +
                ", jwswhome_en='" + jwswhome_en + '\'' +
                ", jwswjb_en='" + jwswjb_en + '\'' +
                ", jwswxq_en='" + jwswxq_en + '\'' +
                ", ksajhome_en='" + ksajhome_en + '\'' +
                ", ksajhome_en500='" + ksajhome_en500 + '\'' +
                ", ksajjb_en='" + ksajjb_en + '\'' +
                ", ksajjb_en500='" + ksajjb_en500 + '\'' +
                ", ksajxq_en='" + ksajxq_en + '\'' +
                ", ksajxq_en500='" + ksajxq_en500 + '\'' +
                ", yzjhome_en='" + yzjhome_en + '\'' +
                ", yzjjb_en='" + yzjjb_en + '\'' +
                ", yzjxq_en='" + yzjxq_en + '\'' +
                ", gaxhome_en='" + gaxhome_en + '\'' +
                ", sakcjb_en='" + sakcjb_en + '\'' +
                ", sakcxq_en='" + sakcxq_en + '\'' +
                ", sgbsjb_en='" + sgbsjb_en + '\'' +
                ", sgbsxq_en='" + sgbsxq_en + '\'' +
                ", shkxhome_en='" + shkxhome_en + '\'' +
                ", djkscsmurl_en='" + djkscsmurl_en + '\'' +
                ", insuranceurl_en='" + insuranceurl_en + '\'' +
                ", shjjsmurl_en='" + shjjsmurl_en + '\'' +
                ", tscssmurl_en='" + tscssmurl_en + '\'' +
                ", zcxyurl_en='" + zcxyurl_en + '\'' +
                ", zjxyurl_en='" + zjxyurl_en + '\'' +
                ", ystzurl_en='" + ystzurl_en + '\'' +
                ", gdbhome_en='" + gdbhome_en + '\'' +
                ", tslvxzurl_en='" + tslvxzurl_en + '\'' +
                ", djkschome_en='" + djkschome_en + '\'' +
                ", checkinProtocolUrl='" + checkinProtocolUrl + '\'' +
                ", checkinProtocolEnUrl='" + checkinProtocolEnUrl + '\'' +
                ", jplyhome='" + jplyhome + '\'' +
                ", jplyjb='" + jplyjb + '\'' +
                ", mmbkurl='" + mmbkurl + '\'' +
                ", yeeCard='" + yeeCard + '\'' +
                ", jplyxq='" + jplyxq + '\'' +
                ", isnewfare='" + isnewfare + '\'' +
                ", zhongzzs='" + zhongzzs + '\'' +
                ", zhongzzs_en='" + zhongzzs_en + '\'' +
                ", zhongzzsvip='" + zhongzzsvip + '\'' +
                ", zhongzzsvip_en='" + zhongzzsvip_en + '\'' +
                ", showAuthAlert='" + showAuthAlert + '\'' +
                ", hyzxurl='" + hyzxurl + '\'' +
                ", wxpcxurl='" + wxpcxurl + '\'' +
                ", city=" + city +
                ", airline=" + airline +
                ", classCode=" + classCode +
                ", ylywDate=" + ylywDate +
                ", ylywCity=" + ylywCity +
                '}';
    }
}
