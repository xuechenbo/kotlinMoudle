package com.monebac.com.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * created by xcb
 * on 2021/8/13
 */
public class Course2Bean implements Serializable, MultiItemEntity {

    private boolean isLastType;
    private boolean isSelect=false;
    private int parentPosition;
    private int campStageContentId;
    private int stageId;
    private String contentName;
    private int type;
    private int typeId;
    private String unlockFlag;
    private int duration;
    private String percent;
    private int campPeriodId;


    public boolean isLastType() {
        return isLastType;
    }

    public void setLastType(boolean lastType) {
        isLastType = lastType;
    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }



    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public int getCampPeriodId() {
        return campPeriodId;
    }

    public void setCampPeriodId(int campPeriodId) {
        this.campPeriodId = campPeriodId;
    }



    public int getCampStageContentId() {
        return campStageContentId;
    }

    public void setCampStageContentId(int campStageContentId) {
        this.campStageContentId = campStageContentId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getUnlockFlag() {
        return unlockFlag;
    }

    public void setUnlockFlag(String unlockFlag) {
        this.unlockFlag = unlockFlag;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }


    @Override
    public int getItemType() {
        return 2;
    }
}
