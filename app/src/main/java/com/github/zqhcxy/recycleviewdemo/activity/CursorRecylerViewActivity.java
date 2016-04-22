package com.github.zqhcxy.recycleviewdemo.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.zqhcxy.recycleviewdemo.R;
import com.github.zqhcxy.recycleviewdemo.adapter.CursorRecyclerViewAdapter;

/**
 * 实现cursorAdapter的RecylerView
 */

public class CursorRecylerViewActivity extends AppCompatActivity {

    private RecyclerView cursor_recyclerview;
    private CursorRecyclerViewAdapter cursorRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_recyler_view);
        findView();
        getCursor();
    }

    private void findView() {
        cursor_recyclerview = (RecyclerView) findViewById(R.id.cursor_recyclerview);
//        cursor_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        cursor_recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        cursor_recyclerview.setHasFixedSize(true);
        cursorRecyclerViewAdapter = new CursorRecyclerViewAdapter(this);
        cursor_recyclerview.setAdapter(cursorRecyclerViewAdapter);
    }

    private void getCursor() {
        Cursor cursor = null;
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE;
        String key_DATA = MediaStore.Images.Media.DATA;
        String key_ID = MediaStore.Images.Media._ID;
        String selection;
        selection = key_MIME_TYPE + "=? or "
                + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=? or " + key_MIME_TYPE + "=?";
        Log.e("zqh-allphoto", "photo-uri: " + mImageUri.toString());
        ContentResolver mContentResolver = getContentResolver();
        // 只查询jpg和png的图片,按最新修改排序
        try {
            cursor = mContentResolver.query(mImageUri,
                    new String[]{key_DATA, key_ID}, selection,
                    new String[]{"image/jpg", "image/jpeg", "image/png", "image/gif", "image/vnd.wap.wbmp"},
                    MediaStore.Images.Media.DATE_TAKEN + " desc");
            if (cursor != null)
                Log.e("zqh-allphoto", "cursor: " + cursor.getCount());
        } catch (Exception e) {
            Log.e("cursor-Error", e.getLocalizedMessage());
        }
        cursorRecyclerViewAdapter.changeCursor(cursor);
    }
}
