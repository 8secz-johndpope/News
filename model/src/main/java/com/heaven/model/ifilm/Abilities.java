package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/30.
 */

public class Abilities {


    /**
     * id : 100
     * editorId : 1
     * ability : 1
     * stage : 1
     * status : 1
     * abilityName : 动画
     * abilityValue : 动画
     * abilityDesc : 剪辑师擅长技能
     */

    private int id;
    private int editorId;
    private String ability;
    private int stage;
    private int status;
    private String abilityName;
    private String abilityValue;
    private String abilityDesc;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityValue() {
        return abilityValue;
    }

    public void setAbilityValue(String abilityValue) {
        this.abilityValue = abilityValue;
    }

    public String getAbilityDesc() {
        return abilityDesc;
    }

    public void setAbilityDesc(String abilityDesc) {
        this.abilityDesc = abilityDesc;
    }
}
