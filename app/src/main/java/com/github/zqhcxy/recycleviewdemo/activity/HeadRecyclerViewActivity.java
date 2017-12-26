package com.github.zqhcxy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.adapter.HeadViewRecyclerViewAdapter;
import com.github.zqhcxy.recycleviewdemo.mode.HcPinnedControl;
import com.github.zqhcxy.recycleviewdemo.mode.HeadViewMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * a、带有头部的布局的recyclerView应用
 * b、悬浮置顶的标题headbview
 *
 * @author zqh by 2017-12-26
 */
public class HeadRecyclerViewActivity extends AppCompatActivity implements HcPinnedControl.HcPinnedControlInterface {

    private RecyclerView mRecyclerView;
    private RelativeLayout pinned_ly;

    private static final int ITEM_COLUNM_COUN = 3;

    private HeadViewRecyclerViewAdapter mAdapter;
    private HcPinnedControl mHcPinnedControl;

    private Map<Integer,Integer> monthPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.head_rv);
        pinned_ly = (RelativeLayout) findViewById(R.id.pinned_ly);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, ITEM_COLUNM_COUN);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)) {
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
        mAdapter = new HeadViewRecyclerViewAdapter(this, getItemdList());
        mRecyclerView.setAdapter(mAdapter);

        mHcPinnedControl = new HcPinnedControl(this);
        mHcPinnedControl.initControl(mRecyclerView, pinned_ly);

    }


    /**
     * 虚构数据集
     *
     * @return
     */
    private ArrayList<HeadViewMode> getItemdList() {
        monthPositions=new HashMap<>();
        monthPositions.put(0,9);
        monthPositions.put(9,18);
        ArrayList<HeadViewMode> headViewModes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HeadViewMode headViewMode = new HeadViewMode();
            if (i == 0) {
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第一个标题");
            } else if (i == 9) {
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第二个标题");
            } else if (i == 18) {
                headViewMode.setType(HeadViewRecyclerViewAdapter.HEADVIEW_TYPE);
                headViewMode.setTitle("第三个标题");
            } else {
                headViewMode.setType(HeadViewRecyclerViewAdapter.NORMALITEM_TYPE);
                headViewMode.setTitle("普通item" + i);
                headViewMode.setResulseId(R.mipmap.nav_bg_big);
            }
            headViewModes.add(headViewMode);
        }
        return headViewModes;
    }

    @Override
    public View onCreateView(ViewGroup parentView) {
        return LayoutInflater.from(this).inflate(R.layout.headviewrecyclerview_item_head, parentView, false);
    }

    @Override
    public void onBindView(int position, View headView) {
        if(headView==null) return;
        TextView textView = (TextView) headView.findViewById(R.id.headview_title);
        HeadViewMode headViewMode = mAdapter.getItemData(position);
        if(textView==null) return;
        textView.setText(headViewMode.getTitle());
    }

    private View cachaeView;

    @Override
    public void showHeadView(int position, boolean show) {
        if(cachaeView!=null) cachaeView.setVisibility(View.VISIBLE);
        View view = mAdapter.getHeadView(position);
        if (view == null) return;
        cachaeView=view;
        if (show) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean isHeadView(int position) {
        return mAdapter.getItemViewType(position) == HeadViewRecyclerViewAdapter.HEADVIEW_TYPE;
    }

    @Override
    public int getNextHeadViewPosition(int position) {
        return monthPositions.containsKey(position)?monthPositions.get(position):position;
    }

    @Override
    public View getHeadView(int position) {
        return mAdapter.getHeadView(position);
    }

    @Override
    public int getmonthPosition(int position) {
        int pos;
        if(position<9){
            pos=0;
        }else if(position<18){
            pos=9;
        }else{
            pos=18;
        }

        return pos;
    }
}
