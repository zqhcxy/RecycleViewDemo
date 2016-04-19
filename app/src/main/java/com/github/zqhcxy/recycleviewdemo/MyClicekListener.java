package com.github.zqhcxy.recycleviewdemo;

import android.view.View;

/**
 * 点击与长按事件
 * Created by zqh-pc on 2016/4/19.
 */
public interface MyClicekListener {
    void onClick(View view, int position, boolean isLongClick);
}
