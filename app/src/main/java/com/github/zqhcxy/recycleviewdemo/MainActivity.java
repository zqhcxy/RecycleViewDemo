package com.github.zqhcxy.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView Demo
 *
 * @author zqhcxy
 *         Created by zqhcxy on 2016/4/19.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void findView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("item" + i);
        }
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, list, recyclerview);
        recyclerview.setAdapter(myRecyclerViewAdapter);
    }


}
