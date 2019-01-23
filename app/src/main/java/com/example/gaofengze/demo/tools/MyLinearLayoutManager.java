package com.example.gaofengze.demo.tools;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by gaofengze on 2018/12/11
 */
public class MyLinearLayoutManager extends LinearLayoutManager {
    private double size = 0;
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, double size) {
        super(context);
        this.size = size;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int count = state.getItemCount();
        if (count > 0 ) {
            int realHeight = 0;
            int realWidth = 0;
            View view = recycler.getViewForPosition(0);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                int measuredHeight = view.getMeasuredHeight();
                realWidth = realWidth > measuredWidth ? realWidth : measuredWidth;
                realHeight += measuredHeight * Math.min(size,count);
            }
            setMeasuredDimension(realWidth, realHeight);
        } else {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
        }
    }
}
