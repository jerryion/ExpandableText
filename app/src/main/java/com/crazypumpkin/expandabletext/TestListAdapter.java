package com.crazypumpkin.expandabletext;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by CrazyPumPkin on 16/8/2.
 */
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestHolder>{

    private Activity mContext;

    private final int MAX_LINE_COUNT = 3;

    private final int STATE_UNKNOW = -1;

    private final int STATE_NOT_OVERFLOW = 1;

    private final int STATE_COLLAPSED = 2;

    private final int STATE_EXPANDED = 3;

    private SparseArray<Integer> mTextStateList;

    public TestListAdapter(Activity context) {
        mContext = context;
        mTextStateList = new SparseArray<>();
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(mContext.getLayoutInflater().inflate(R.layout.item_test_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final TestHolder holder, final int position) {
        holder.head.setText(position+1+"");
        holder.name.setText(Util.getName(position));
        int state = mTextStateList.get(position, STATE_UNKNOW);
        //如果该item是第一次初始化，则去获取文本的行数
        if(state == STATE_UNKNOW){
            holder.content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回调会调用多次，获取完行数记得注销监听
                    holder.content.getViewTreeObserver().removeOnPreDrawListener(this);
                    if(holder.content.getLineCount() > MAX_LINE_COUNT){
                        holder.content.setMaxLines(MAX_LINE_COUNT);
                        holder.expandOrCollapse.setVisibility(View.VISIBLE);
                        holder.expandOrCollapse.setText("全文");
                        mTextStateList.put(position, STATE_COLLAPSED);
                    }else{
                        holder.expandOrCollapse.setVisibility(View.GONE);
                        mTextStateList.put(position, STATE_NOT_OVERFLOW);
                    }
                    return true;
                }
            });
            holder.content.setMaxLines(Integer.MAX_VALUE);
            holder.content.setText(Util.getContent(position));
        }else{
            //如果之前已经初始化过了，则使用保存的状态，无需再获取一次
            switch (state){
                case STATE_NOT_OVERFLOW:
                    holder.expandOrCollapse.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.expandOrCollapse.setText("全文");
                    break;
                case STATE_EXPANDED:
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.expandOrCollapse.setText("收起");
                    break;
            }
            holder.content.setText(Util.getContent(position));
        }

        holder.expandOrCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = mTextStateList.get(position, STATE_UNKNOW);
                if(state == STATE_COLLAPSED){
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setText("收起");
                    mTextStateList.put(position, STATE_EXPANDED);
                }else if(state == STATE_EXPANDED){
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setText("全文");
                    mTextStateList.put(position, STATE_COLLAPSED);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TestHolder extends RecyclerView.ViewHolder {

        public TextView head;

        public TextView name;

        public TextView content;

        public TextView expandOrCollapse;

        public TestHolder(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.tv_head);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            expandOrCollapse = (TextView) itemView.findViewById(R.id.tv_expand_or_collapse);
        }
    }
}
