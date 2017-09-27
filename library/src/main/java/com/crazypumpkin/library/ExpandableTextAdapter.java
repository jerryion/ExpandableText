package com.crazypumpkin.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装了折叠展开文本动作的adapter
 * Created by CrazyPumPkin on 2017/9/27.
 */

public abstract class ExpandableTextAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数

    private final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态

    private final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开

    private int  mMaxLineCount;

    private String mTextExpand;

    private String mTextCollapse;

    private Map<String,Integer> mTextStates;

    public ExpandableTextAdapter() {
        super();
        mMaxLineCount = getMaxLineCount();
        mTextExpand = getExpandText();
        mTextCollapse = getCollapseText();
        mTextStates = new HashMap<>();
    }

    @Override
    public void onBindViewHolder(final T holder, final int position) {
        final String dataId = getItemDataId(position);
        Integer state = mTextStates.get(dataId);
        final TextView contentTV = (TextView) holder.itemView.findViewById(getExpandableTextViewId());
        final TextView triggerViewTV = (TextView) holder.itemView.findViewById(getTriggerViewId());
        //如果该item是第一次初始化，则去获取文本的行数
        if(state == null){
            contentTV.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回调会调用多次，获取完行数记得注销监听
                    contentTV.getViewTreeObserver().removeOnPreDrawListener(this);
                    if(contentTV.getLineCount() > mMaxLineCount){
                        contentTV.setMaxLines(mMaxLineCount);
                        triggerViewTV.setVisibility(View.VISIBLE);
                        triggerViewTV.setText(mTextExpand);
                        mTextStates.put(dataId, STATE_COLLAPSED);
                    }else{
                        triggerViewTV.setVisibility(View.GONE);
                        mTextStates.put(dataId, STATE_NOT_OVERFLOW);
                    }
                    return true;
                }
            });
            contentTV.setMaxLines(Integer.MAX_VALUE);
            contentTV.setText(Util.getContent(position));
        }else{
            //如果之前已经初始化过了，则使用保存的状态，无需再获取一次
            switch (state){
                case STATE_NOT_OVERFLOW:
                    triggerViewTV.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    contentTV.setMaxLines(mMaxLineCount);
                    triggerViewTV.setVisibility(View.VISIBLE);
                    triggerViewTV.setText(mTextExpand);
                    break;
                case STATE_EXPANDED:
                    contentTV.setMaxLines(Integer.MAX_VALUE);
                    triggerViewTV.setVisibility(View.VISIBLE);
                    triggerViewTV.setText(mTextCollapse);
                    break;
            }
            contentTV.setText(Util.getContent(position));
        }

        triggerViewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = mTextStates.get(dataId);
                //两种状态下对应动作
                if(state == STATE_COLLAPSED){
                    contentTV.setMaxLines(Integer.MAX_VALUE);
                    triggerViewTV.setText(mTextCollapse);
                    mTextStates.put(dataId, STATE_EXPANDED);
                }else if(state == STATE_EXPANDED){
                    contentTV.setMaxLines(mMaxLineCount);
                    triggerViewTV.setText(mTextExpand);
                    mTextStates.put(dataId, STATE_COLLAPSED);
                }
            }
        });
    }

    public void clearTextStates(){
        mTextStates.clear();
    }

    /**
     * 获取内容文本TextView的id
     * @return
     */
    protected abstract int getExpandableTextViewId();

    /**
     * 获取"全文""收起"按钮TextView的id
     * @return
     */
    protected abstract int getTriggerViewId();

    /**
     * 默认显示行数
     * @return
     */
    protected abstract int getMaxLineCount();

    /**
     * 获取代表某个item的唯一标示
     * @param position
     * @return
     */
    protected abstract String getItemDataId(int position);

    /**
     * 获取Expand的对应中文
     * @return e.g."全文" or "全部"
     */
    protected abstract String getExpandText();

    /**
     * 获取Collapse的对应中文
     * @return e.g."收起" or "折叠"
     */
    protected abstract String getCollapseText();
}
