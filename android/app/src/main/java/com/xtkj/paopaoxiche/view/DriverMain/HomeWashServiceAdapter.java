package com.xtkj.paopaoxiche.view.DriverMain;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.view.WashService.WashServiceActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeWashServiceAdapter extends RecyclerView.Adapter<HomeWashServiceAdapter.ViewHolder> {
    public ArrayList<WashServicesBean.DataBean> dataList = null;
    public Context mContext;

    public HomeWashServiceAdapter(WashServicesBean washServicesBean,Context context) {
        this.dataList = (ArrayList<WashServicesBean.DataBean>) washServicesBean.getData();
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_yard, viewGroup, false);


        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Glide.with(mContext).load(dataList.get(position).getImage()).into(viewHolder.userImg);
        viewHolder.userName.setText(dataList.get(position).getName());
        viewHolder.yardSpend.setText(String.format("%s", dataList.get(position).getPrice()));
        viewHolder.honorTimes.setText(String.format("%s", dataList.get(position).getHonor()));
        viewHolder.washTimes.setText(String.format("%s", dataList.get(position).getWashCount()));
        viewHolder.yardDistance.setText(String.format("%sm", dataList.get(position).getDistance()));
        viewHolder.itemView.setOnClickListener(view1 -> {
            Intent intent = new Intent(mContext, WashServiceActivity.class);
            intent.putExtra("washId", dataList.get(position).getWashId());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_img)
        ImageView userImg;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.honor_times)
        TextView honorTimes;
        @BindView(R.id.wash_times)
        TextView washTimes;
        @BindView(R.id.yard_spend)
        TextView yardSpend;
        @BindView(R.id.yard_distance)
        TextView yardDistance;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
