package com.crazypumpkin.expandabletext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazypumpkin.library.ExpandableTextAdapter;
import com.crazypumpkin.library.Util;

/**
 * Created by CrazyPumPkin on 16/8/2.
 */
public class TestListAdapter extends ExpandableTextAdapter<TestListAdapter.TestHolder> {

    private Context mContext;

    public TestListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(LayoutInflater.from(mContext).inflate(R.layout.item_test_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final TestHolder holder, final int position) {
        super.onBindViewHolder(holder,position);
        holder.head.setText(String.valueOf(position+1));
        holder.name.setText(Util.getName(position));

    }

    @Override
    protected int getExpandableTextViewId() {
        return R.id.tv_content;
    }

    @Override
    protected int getTriggerViewId() {
        return R.id.tv_expand_or_collapse;
    }

    @Override
    protected int getMaxLineCount() {
        return 3;
    }

    @Override
    protected String getItemDataId(int position) {
        return String.valueOf(position);
    }

    @Override
    protected String getExpandText() {
        return "全文";
    }

    @Override
    protected String getCollapseText() {
        return "收起";
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TestHolder extends RecyclerView.ViewHolder {

        public TextView head;

        public TextView name;

        public TestHolder(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.tv_head);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}

