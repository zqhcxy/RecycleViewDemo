package com.github.zqhcxy.recycleviewdemo.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通的RecycleView Demo
 * <p>简单的实现RecyclerView的显示、添加、删除功能、动画。
 *
 * @author zqhcxy
 *         Created by zqhcxy on 2016/4/19.
 * @version 1.1
 */
public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerview;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private RadioGroup bt_rg;
    private CheckBox cb_animator;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout refresh_ly;

    DefaultItemAnimator mDefaultItemAnimator = new DefaultItemAnimator();

    private List<String> list;
    private int lastVisibleItem;
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://下啦
                    Log.i("zqh", "啊啦啦啦德玛西亚");
                    refresh_ly.setRefreshing(false);
                    break;
                case 1://上啦
                    Log.i("zqh", "我的大刀");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclernomal);

        findView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void findView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        refresh_ly = (SwipeRefreshLayout) findViewById(R.id.refresh_ly);

        //横向和纵向
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        bt_rg = (RadioGroup) findViewById(R.id.bt_rg);
        cb_animator = (CheckBox) findViewById(R.id.cb_animator);
        cb_animator.setEnabled(false);//动画暂时没有实现
        cb_animator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置动画
                recyclerview.setItemAnimator(isChecked ? mDefaultItemAnimator : mDefaultItemAnimator);
            }
        });

        refresh_ly.setOnRefreshListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("item" + i);
        }
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, list, recyclerview);
        recyclerview.setAdapter(myRecyclerViewAdapter);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("recyclerScroll", dx + ":" + dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("recyclerScroll", newState + "");

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == myRecyclerViewAdapter.getItemCount()) {
                    handler.sendEmptyMessageDelayed(1, 3000);
                }
            }
        });
    }


    public int getCheckMode() {
        return bt_rg.getCheckedRadioButtonId();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
    }


}
