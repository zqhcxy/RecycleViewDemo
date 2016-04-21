package com.github.zqhcxy.recycleviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zqhcxy.recycleviewdemo.R;

import java.util.List;

/**
 * Created by zqh-pc on 2016/4/21.
 */
public class TypesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPES1 = 0;
    private static final int TYPES2 = 1;
    private List<String> mList;

    public TypesRecyclerViewAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? TYPES1 : TYPES2;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPES1) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewimageitem, null));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {

        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).menu_title.setText(mList.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView menu_title;

        public TextViewHolder(View itemView) {
            super(itemView);
            menu_title = (TextView) itemView.findViewById(R.id.menu_title);
        }
    }

}
