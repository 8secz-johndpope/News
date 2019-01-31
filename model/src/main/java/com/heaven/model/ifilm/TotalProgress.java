package com.heaven.model.ifilm;

/**
 * Created by heaven on 2017/1/8.
 */

public class TotalProgress {
    private int totalPercent;
    private int totalNum;
    private int finishedNum;

    public int getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(int totalPercent) {
        this.totalPercent = totalPercent;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(int finishedNum) {
        this.finishedNum = finishedNum;
    }

    @Override
    public String toString() {
        return "TotalProgress{" +
                "totalPercent=" + totalPercent +
                ", totalNum=" + totalNum +
                ", finishedNum=" + finishedNum +
                '}';
    }
}
