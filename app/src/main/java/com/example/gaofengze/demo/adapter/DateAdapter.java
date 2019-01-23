package com.example.gaofengze.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gaofengze.demo.R;
import com.example.gaofengze.demo.base.BaseRecyclerViewAdapter;
import com.example.gaofengze.demo.base.BaseRecyclerViewHolder;

/**
 * Created by gaofengze on 2018/12/10
 */
public class DateAdapter extends BaseRecyclerViewAdapter<String,DateAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_year,parent,false));
    }

    class ViewHolder extends BaseRecyclerViewHolder<String> {
        TextView tx;
        ViewHolder(View itemView) {
            super(itemView);
            tx = itemView.findViewById(R.id.item_year);
        }

        @Override
        public void onBindViewHolder(String data, int position) {
            tx.setText(data);
            if(position%2==1){
                tx.setBackgroundResource(R.color.blue);
            }else{
                tx.setBackgroundResource(R.color.blue_1);
            }
        }
    }
}
