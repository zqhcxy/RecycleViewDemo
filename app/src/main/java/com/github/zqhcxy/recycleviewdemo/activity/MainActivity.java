package com.github.zqhcxy.recycleviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.zqhcxy.recycleviewdemo.R;

/**
 * RecycleView Demo功能合集
 *
 * @author zqhcxy
 *         Created by zqhcxy on 2016/4/21.
 * @version 1.1
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nomal_Recyler;
    private Button typs_Recyler;
    private Button cursor_Recyler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
    }

    private void findView() {
        nomal_Recyler = (Button) findViewById(R.id.nomal_Recyler);
        typs_Recyler = (Button) findViewById(R.id.typs_Recyler);
        cursor_Recyler = (Button) findViewById(R.id.cursor_Recyler);

        nomal_Recyler.setOnClickListener(this);
        typs_Recyler.setOnClickListener(this);
        cursor_Recyler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nomal_Recyler:
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.typs_Recyler:
                Intent intent1 = new Intent(MainActivity.this, TypesActivity.class);
                startActivity(intent1);
                break;
            case R.id.cursor_Recyler:

                break;

        }

    }
}
