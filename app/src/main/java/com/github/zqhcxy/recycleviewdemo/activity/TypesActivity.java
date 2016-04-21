package com.github.zqhcxy.recycleviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.adapter.TypesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView多类型Item实现
 *
 * @version 1.1
 */
public class TypesActivity extends AppCompatActivity {

    private RecyclerView types_recyclerview;
    private TypesRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        findView();
    }

    private void findView() {
        types_recyclerview = (RecyclerView) findViewById(R.id.types_recyclerview);

        types_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("你那爱笑的眼睛" + i);
        }
        adapter = new TypesRecyclerViewAdapter(list);
        types_recyclerview.setAdapter(adapter);
    }
}
