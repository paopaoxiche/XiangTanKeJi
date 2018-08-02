package com.xtkj.paopaoxiche.view.DriverMain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

public class HomeYardsAdapter extends RecyclerView.Adapter<HomeYardsAdapter.ViewHolder> {
    public String[] datas = null;
    public HomeYardsAdapter(String[] datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_yard,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.length;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView honorTimes;
        public TextView washTimes;
        public TextView yardSpend;
        public TextView yardDistance;
        public ViewHolder(View view){
            super(view);
            honorTimes = (TextView) view.findViewById(R.id.honor_times);
            washTimes = (TextView) view.findViewById(R.id.wash_times);
            yardSpend = (TextView) view.findViewById(R.id.yard_spend);
            yardDistance = (TextView) view.findViewById(R.id.yard_distance);
        }
    }
}
