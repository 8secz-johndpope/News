package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/15.
 */

public class FilmCategory implements Cloneable {


    /**
     * dictId : 1
     * dictType : film_type
     * typeDesc : 影片类型
     * dictCode : 1
     * dictName : 亲子
     * dictValue : 亲子
     * parentId : null
     * comment : null
     * sort : 1
     * status : 1
     * appIcon :
     */

    private int dictId;  //主键唯一标识
    private String dictType;
    private String typeDesc;
    private int dictCode;
    private String dictName; //名字
    private int colorRes; //  文字颜色
    private String dictValue;//名字
    private String parentId;
    private String comment;
    private int sort;
    private int status;
    private String appIcon; //图片
    private String appSubIcon;//图片另一种形态
    private int iconRes;  //本地图片
    private boolean isSelected;//是否选中

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public int getDictCode() {
        return dictCode;
    }

    public void setDictCode(int dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getAppSubIcon() {
        return appSubIcon;
    }

    public void setAppSubIcon(String appSubIcon) {
        this.appSubIcon = appSubIcon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public FilmCategory clone() {
        FilmCategory filmCategory = null;
        try {
            filmCategory = (FilmCategory) super.clone();
            filmCategory.isSelected = false;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return filmCategory;
    }
}
