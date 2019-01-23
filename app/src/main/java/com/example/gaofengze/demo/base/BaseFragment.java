package com.example.gaofengze.demo.base;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( getLayout() , container, false);
        init(view);
        loadData();
        return view;
    }

    /**
     * 加载视图
     */
    public abstract int getLayout();

    /**
     * 绑定控件
     */
    public abstract void init(View view);

    /**
     * 加载数据
     */
    public abstract void loadData();

    /**
     * 刷新数据
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            loadData();
        }
    }

}
