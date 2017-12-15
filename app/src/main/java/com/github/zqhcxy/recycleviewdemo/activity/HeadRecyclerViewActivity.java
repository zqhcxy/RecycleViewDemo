package com.github.zqhcxy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.adapter.HeadViewRecyclerViewAdapter;
import com.github.zqhcxy.recycleviewdemo.mode.HeadViewMode;

import java.util.ArrayList;

/**
 * 带有头部的布局的recyclerView应用
 */
public class HeadRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private static final int ITEM_COLUNM_COUN=3;

    private HeadViewRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_recycler_view);

        mRecyclerView=(RecyclerView)findViewById(R.id.head_rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,ITEM_COLUNM_COUN);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)){
                    case HeadViewRecyclerViewAdapter.HEADVIEW_TYPE:
                        return ITEM_COLUNM_COUN;
                    case HeadViewRecyclerViewAdapter.NORMALITEM_TYPE:
                       return 1;
                    default:
                        return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter=new HeadViewRecyclerViewAdapter(this,getItemdList());
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<HeadViewMode> getItemdList(){
        ArrayList<HeadViewMode> headViewModes=new ArrayList<>();
        for(int i=0;i<30;i++){
            HeadViewMode headViewMode=new HeadViewMode();
            if(i==0){
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第一个标题");
            }else if(i==9){
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第二个标题");
            }else if(i==18){
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第三个标题");
            }else{
                headViewMode.setType(HeadViewRecyclerViewAdapter.NORMALITEM_TYPE);
                headViewMode.setTitle("普通item"+i);
                headViewMode.setResulseId(R.mipmap.nav_bg_big);
            }
            headViewModes.add(headViewMode);
        }
        return headViewModes;
    }
}
