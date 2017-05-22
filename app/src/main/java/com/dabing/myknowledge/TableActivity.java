package com.dabing.myknowledge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.dabing.myknowledge.hostory.HistoryFragment;

/**
 * Created by MyKnowledge on 2017/5/16.
 */
public class TableActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        container = (FrameLayout) findViewById(R.id.container);
//        RadioGroup radioGroupTab = (RadioGroup) findViewById(R.id.radio_group_tab);
//
//        radioGroupTab.setOnCheckedChangeListener(this);
        HistoryFragment historyFragment = new HistoryFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,historyFragment);
        fragmentTransaction.show(historyFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
//            case R.id.history:
//
//                break;
//            case R.id.rank:
//                break;
//            case R.id.me:

//                break;
        }
    }
}
