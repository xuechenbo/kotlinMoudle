package com.monebac.com.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.monebac.com.R;

import java.util.List;

/**
 * created by xcb
 * on 2021/8/13
 */
public class BeginClassAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int ITEM_FIRST_LEVEL = 1;
    public static final int ITEM_SECOND_LEVEL = 2;
    private Context context;
    private int mPostion = 0;

    public BeginClassAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        this.context = context;
        addItemType(ITEM_FIRST_LEVEL, R.layout.item_level1);
        addItemType(ITEM_SECOND_LEVEL, R.layout.item_level2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

        switch (helper.getItemViewType()) {
            case ITEM_FIRST_LEVEL:
                final Course1Bean item_temp = (Course1Bean) item;
//                helper.setText(R.id.tv_leve1Title, item_temp.getStageName());
                helper.addOnClickListener(R.id.linItem);
                break;
            case ITEM_SECOND_LEVEL:
                final Course2Bean item_temp2 = (Course2Bean) item;
                helper/*.setText(R.id.tv_titleName, item_temp2.getContentName())*/
//                        .setText(R.id.tv_classTime, item_temp2.getDuration() / 60 + "分钟")
//                        .setText(R.id.tv_studyTime, item_temp2.getPercent().equals("0") ? "" : "已学习" + item_temp2.getPercent() + "%")
//                        .setGone(R.id.iv_lock, item_temp2.getUnlockFlag().equals("2") ? true : false)
//                        .setGone(R.id.tv_lastStudy,item_temp2.isLastType())
//                        .setGone(R.id.tv_classTime, item_temp2.getType() != 3)
//                        .addOnClickListener(R.id.tv_titleName)
                        .addOnClickListener(R.id.con_Item)
                        .addOnClickListener(R.id.iv_lock);
//                if (item_temp2.isLastType()) {
//                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.scxd);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    ((TextView) helper.getView(R.id.tv_titleName)).setCompoundDrawables(null, null, drawable, null);
//                } else {
//                    ((TextView) helper.getView(R.id.tv_titleName)).setCompoundDrawables(null, null, null, null);
//                }
//
                if (item_temp2.isSelect()) {
                    helper.setTextColor(R.id.tv_titleName, context.getResources().getColor(R.color.classBlueColor));
                    helper.setTextColor(R.id.tv_classTime, context.getResources().getColor(R.color.classBlueColor));
                    helper.setTextColor(R.id.tv_studyTime, context.getResources().getColor(R.color.classBlueColor));
                    Glide.with(context).load(R.drawable.icon_play_class).into((ImageView) helper.getView(R.id.iv_type));
                } else {
                    helper.setTextColor(R.id.tv_titleName, context.getResources().getColor(R.color.classBlackColor));
                    helper.setTextColor(R.id.tv_classTime, context.getResources().getColor(R.color.classBlackColor1));
                    helper.setTextColor(R.id.tv_studyTime, context.getResources().getColor(R.color.classBlackColor1));
                    Glide.with(context).load(R.drawable.icon_stop_class).into((ImageView) helper.getView(R.id.iv_type));
                }
                break;
        }
    }

    public void setSelectPostion(int postion) {
        mPostion = postion;
    }


}
