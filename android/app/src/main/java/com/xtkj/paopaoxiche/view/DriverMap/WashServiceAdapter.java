package com.xtkj.paopaoxiche.view.DriverMap;

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
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.contract.IDriverMapContract;
import com.xtkj.paopaoxiche.presenter.DriverMapPresenterImpl;
import com.xtkj.paopaoxiche.utils.LocationUtils;
import com.xtkj.paopaoxiche.view.WashService.WashServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WashServiceAdapter extends RecyclerView.Adapter<WashServiceAdapter.ViewHolder> {
    WashServicesBean datas = null;
    Context context;
    IDriverMapContract.IDriverMapView mView ;

    public WashServiceAdapter(WashServicesBean washServicesBean, Context c) {
        this.datas = washServicesBean;
        this.context = c;
        this.mView = (IDriverMapContract.IDriverMapView)c;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wash_car_map, viewGroup, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.name.setText(datas.getData().get(position).getName());
        Glide.with(context).load(datas.getData().get(position).getImage()).into(viewHolder.image);
        viewHolder.price.setText(String.format("￥%s", datas.getData().get(position).getPrice()));
        int distance = (int) LocationUtils.getDistance(Double.parseDouble(MyLocation.lat),Double.parseDouble(MyLocation.lng),datas.getData().get(position).getLat(),datas.getData().get(position).getLng());
        if(distance > 1000){
            viewHolder.distance.setText(String.format("%skm", (distance * 1.0) / 1000));
        }else  viewHolder.distance.setText(String.format("%sm", distance));
        viewHolder.location.setText(datas.getData().get(position).getName());
        viewHolder.navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.startNavigation(datas.getData().get(position).getLng(),datas.getData().get(position).getLat());
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WashServiceActivity.class);
                intent.putExtra("washId", datas.getData().get(position).getWashId());
                context.startActivity(intent);
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        if (datas == null) return 0;
        return datas.getData().size();
    }


            //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.location)
        TextView location;
        @BindView(R.id.navigation)
        ImageView navigation;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.distance)
        TextView distance;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
