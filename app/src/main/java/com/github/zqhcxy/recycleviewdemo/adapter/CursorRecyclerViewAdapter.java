package com.github.zqhcxy.recycleviewdemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.weight.BaseAbstractRecycleCursorAdapter;

/**
 * Created by zqh-pc on 2016/4/22.
 */
public class CursorRecyclerViewAdapter extends BaseAbstractRecycleCursorAdapter<CursorRecyclerViewAdapter.ViewHolder> {

    //    private Cursor mCursor;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    public CursorRecyclerViewAdapter(Context context) {
        super(context, null);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.recyclerviewitem, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        holder.menu_title.setText(cursor.getString(cursor.getColumnIndex("_data")));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView menu_title;

        public ViewHolder(View itemView) {
            super(itemView);
            menu_title = (TextView) itemView.findViewById(R.id.menu_title);
        }
    }
}
