package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xtkj.paopaoxiche.R;

public class MineItemView extends RelativeLayout{
    public MineItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.mine_item_relativelayout, this);
    }

    public MineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mine_item_relativelayout, this);
    }

    public MineItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.mine_item_relativelayout, this);
    }

    public MineItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.mine_item_relativelayout, this);
    }
}
