package com.github.zqhcxy.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zqhcxy on 2016/4/19.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mList;
    private Context mContext;
    private RecyclerView recyclerview;
    private MainActivity mainActivity;


    public MyRecyclerViewAdapter(Context context, List<String> list, RecyclerView recyclerview) {
        mList = list;
        mContext = context;
        this.recyclerview = recyclerview;
        this.mainActivity = (MainActivity) context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mList != null && mList.size() > position)
            holder.menu_title.setText(mList.get(position));
        holder.setItemOnClickListener(new MyClicekListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (position != RecyclerView.NO_POSITION) {
                    if (isLongClick) {
                        Toast.makeText(mContext, "长按" + mList.get(position), Toast.LENGTH_SHORT).show();
                    } else {//  单击
                        switch (mainActivity.getCheckMode()) {
                            case R.id.rb_itemclick:
                                Toast.makeText(mContext, "点击" + mList.get(position), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.rb_itemadd:
                                addItem(view);
                                break;
                            case R.id.rb_itemdelete:
                                deleteItem(view);
                                break;
                            case R.id.rb_itemchange:
                                achangeItem(view);
                                break;
                        }
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 删除item
     *
     * @param view 要删除的item
     */
    private void deleteItem(View view) {
        int pos = recyclerview.getChildAdapterPosition(view);
        if (pos != RecyclerView.NO_POSITION) {
            mList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    /**
     * 添加item
     *
     * @param view 添加item在这个控件之上
     */
    private void addItem(View view) {
        int pos = recyclerview.getChildAdapterPosition(view);
        if (pos != RecyclerView.NO_POSITION) {
            mList.add(pos, "新加的item" + mList.size());//添加的位置，和添加的内容
            notifyItemInserted(pos);
        }
    }
    /**
     * 添加item
     *
     * @param view 添加item在这个控件之上
     */
    private void achangeItem(View view) {
        int pos = recyclerview.getChildAdapterPosition(view);
        if (pos != RecyclerView.NO_POSITION) {
            mList.set(pos, " 改变的item" + pos);//添加的位置，和添加的内容
            notifyItemChanged(pos);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView menu_title;
        private MyClicekListener mOnClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            menu_title = (TextView) itemView.findViewById(R.id.menu_title);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemOnClickListener(MyClicekListener onClickListener) {
            mOnClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            mOnClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}
