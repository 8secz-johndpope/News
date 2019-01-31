package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2017/1/10.
 */

public class EditorAbilityExList {


    /**
     * id : null
     * editorId : null
     * ability : 1
     * stage : 2
     * status : null
     * abilityName : 掌握
     * stageName : 动画
     * stageTitle : 精通
     */

    private int id;
    private int editorId;
    private int ability;
    private int stage;
    private int status;
    private String abilityName;
    private String stageName;
    private String stageTitle;

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

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
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

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageTitle() {
        return stageTitle;
    }

    public void setStageTitle(String stageTitle) {
        this.stageTitle = stageTitle;
    }
}
