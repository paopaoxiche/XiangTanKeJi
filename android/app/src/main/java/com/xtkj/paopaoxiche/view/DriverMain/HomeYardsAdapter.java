package com.xtkj.paopaoxiche.view.DriverMain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServicesBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeYardsAdapter extends RecyclerView.Adapter<HomeYardsAdapter.ViewHolder> {
    public ArrayList<WashServicesBean.DataBean> dataList = null;

    public HomeYardsAdapter(WashServicesBean washServicesBean) {
        this.dataList = (ArrayList<WashServicesBean.DataBean>) washServicesBean.getData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_yard, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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
