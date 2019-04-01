package com.gaofengze.demo.ui.activity;


import android.os.Bundle;

import com.gaofengze.demo.R;
import com.gaofengze.demo.base.BaseActivity;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Created by gaofengze on 2019/3/6
 */
public class TestActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }


}
