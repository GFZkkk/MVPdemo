package com.gaofengze.demo.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by gaofengze on 2018/10/26
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( getLayout() , container, false);
        loadData();
        return view;
    }

    /**
     * 加载视图
     */
    public abstract int getLayout();

    /**
     * 加载数据
     */
    public void loadData(){}

    /**
     * 刷新数据
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            loadData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
