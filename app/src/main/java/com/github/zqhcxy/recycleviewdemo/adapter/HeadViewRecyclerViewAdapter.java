package com.github.zqhcxy.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.mode.HeadViewMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqh on 2017/12/15.
 */
public class HeadViewRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int NORMALITEM_TYPE = 0;
    public static final int HEADVIEW_TYPE = 1;


    Context mContext;
    ArrayList<HeadViewMode> headViewModes;
    LayoutInflater inflater;

    private Map<Integer,View> headViews;


    public HeadViewRecyclerViewAdapter(Context context, ArrayList<HeadViewMode> lists) {
        mContext = context;
        headViewModes = lists;
        inflater = LayoutInflater.from(context);
        headViews=new HashMap<>();
    }

    @Override
    public int getItemViewType(int position) {
        return headViewModes.get(position).getType();
    }

    public HeadViewMode getItemData(int position){
        return headViewModes.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == HEADVIEW_TYPE) {
            View view = inflater.inflate(R.layout.headviewrecyclerview_item_head, parent, false);
            holder = new HeadViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.headviewrecycler_item_ly, parent, false);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final HeadViewMode headViewMode=headViewModes.get(position);
        int type=headViewMode.getType();
        if(type==NORMALITEM_TYPE){
            ItemViewHolder itemViewHolder= (ItemViewHolder) holder;
            itemViewHolder.mTitle.setText(headViewMode.getTitle());
            itemViewHolder.mImageView.setImageResource(headViewMode.getResulseId());
        } else if(type==HEADVIEW_TYPE){
            holder.itemView.setTag(position);
            headViews.put(position,holder.itemView);
            HeadViewHolder headViewHolder= (HeadViewHolder) holder;
            headViewHolder.mHeadViewTitle.setText(headViewMode.getTitle());
            headViewHolder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,headViewMode.getTitle()+" buttom",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (headViewModes != null) {
            return headViewModes.size();
        }
        return 0;
    }

    public View getHeadView(int position){
        return headViews.get(position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if(holder instanceof HeadViewHolder) {
            try {
                int tag = (int) holder.itemView.getTag();
                headViews.remove(tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {

        private TextView mHeadViewTitle;
        private Button mButton;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mHeadViewTitle= (TextView) itemView.findViewById(R.id.headview_title);
            mButton= (Button) itemView.findViewById(R.id.headview_btn);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle= (TextView) itemView.findViewById(R.id.item_title);
            mImageView=(ImageView)itemView.findViewById(R.id.item_imgItem);

        }
    }
}
