package com.github.zqhcxy.recycleviewdemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

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
        //横向和纵向
//        types_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //瀑布流效果的
        types_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        types_recyclerview.addItemDecoration(decoration);
        //grideView
//        types_recyclerview.setLayoutManager(new GridLayoutManager(this,3));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("你那爱笑的眼睛" + i);
        }
        adapter = new TypesRecyclerViewAdapter(list);
        types_recyclerview.setAdapter(adapter);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
