package com.monebac.com.recycler;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * created by xcb
 * on 2021/8/13
 */
public class Course1Bean extends AbstractExpandableItem<Course2Bean> implements Serializable, MultiItemEntity {



    private int postion;
    private int campStageId;
    private int campPeriodId;
    private String stageName;
    private boolean isSelect=false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Course1Bean(int postion) {
        this.postion = postion;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getCampStageId() {
        return campStageId;
    }

    public void setCampStageId(int campStageId) {
        this.campStageId = campStageId;
    }

    public int getCampPeriodId() {
        return campPeriodId;
    }

    public void setCampPeriodId(int campPeriodId) {
        this.campPeriodId = campPeriodId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
