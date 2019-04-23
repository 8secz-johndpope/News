package com.neusoft.szair.model.flightproto;

import javax.xml.bind.annotation.XmlElement;

import io.protostuff.Tag;

public abstract class BookingResponseBaseVO {
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

    public String getOpResult() {
        return opResult;
    }

    @XmlElement(name="OP_RESULT")
    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

}
