package com.heaven.model.ifilm;


import java.util.List;

/**
 * Created by LeoLu on 2016/12/30.
 */

public class SubmitRequirements<T> {

    private List<T> film_durationList;  //时长
    private List<T> film_typeList;  //类型
    private List<T> budgetList;  //预算
    private List<T> adept_abilityList;  //增值服务
    private List<T> lead_time_typeList;  //交付时间
    private List<T> editor_styleList; //剪辑风格
    private List<T> distribution_channelList; //发行渠道
    private List<T> film_purposeList; //影片目的
    private List<T> material_sourceList; //素材来源
    private List<T> question_typeList;//问题订单类型


    public List<T> getFilm_durationList() {
        return film_durationList;
    }

    public void setFilm_durationList(List<T> film_durationList) {
        this.film_durationList = film_durationList;
    }

    public List<T> getFilm_typeList() {
        return film_typeList;
    }

    public void setFilm_typeList(List<T> film_typeList) {
        this.film_typeList = film_typeList;
    }

    public List<T> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<T> budgetList) {
        this.budgetList = budgetList;
    }

    public List<T> getLead_time_typeList() {
        return lead_time_typeList;
    }

    public void setLead_time_typeList(List<T> lead_time_typeList) {
        this.lead_time_typeList = lead_time_typeList;
    }

    public List<T> getAdept_abilityList() {
        return adept_abilityList;
    }

    public void setAdept_abilityList(List<T> adept_abilityList) {
        this.adept_abilityList = adept_abilityList;
    }

    public List<T> getEditor_styleList() {
        return editor_styleList;
    }

    public void setEditor_styleList(List<T> editor_styleList) {
        this.editor_styleList = editor_styleList;
    }

    public List<T> getDistribution_channelList() {
        return distribution_channelList;
    }

    public void setDistribution_channelList(List<T> distribution_channelList) {
        this.distribution_channelList = distribution_channelList;
    }

    public List<T> getFilm_purposeList() {
        return film_purposeList;
    }

    public void setFilm_purposeList(List<T> film_purposeList) {
        this.film_purposeList = film_purposeList;
    }

    public List<T> getMaterial_sourceList() {
        return material_sourceList;
    }

    public void setMaterial_sourceList(List<T> material_sourceList) {
        this.material_sourceList = material_sourceList;
    }

    public List<T> getQuestion_typeList() {
        return question_typeList;
    }

    public void setQuestion_typeList(List<T> question_typeList) {
        this.question_typeList = question_typeList;
    }
}
