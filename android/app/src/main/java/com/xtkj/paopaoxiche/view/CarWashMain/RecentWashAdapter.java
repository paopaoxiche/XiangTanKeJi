package com.xtkj.paopaoxiche.view.CarWashMain;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.RecentWashListBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecentWashAdapter extends BaseAdapter{


    private Context context;
    List<RecentWashListBean.DataBean> washRecordList = new ArrayList<>();

    public RecentWashAdapter(Context context, List<RecentWashListBean.DataBean> list) {
        washRecordList.addAll(list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return washRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        if (washRecordList.size() > position + 1) {
            return null;
        }
        return washRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (washRecordList.size() > position + 1) {
            return -1;
        }
        return washRecordList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recent_wash, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.nameTextView = convertView.findViewById(R.id.name_text_view);
            holder.washImageView = (ImageView) convertView.findViewById(R.id.goods_image_view);
            holder.carTypeTextView = (TextView) convertView.findViewById(R.id.car_type_text_view);
            holder.priceTextView = (TextView) convertView.findViewById(R.id.price_text_view);
            holder.timeTextView = convertView.findViewById(R.id.time_text_view);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.carTypeTextView.setText(washRecordList.get(position).getCarType());
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date(washRecordList.get(position).getTime()));
        holder.timeTextView.setText(time);
        holder.priceTextView.setText(String.format("¥%s", washRecordList.get(position).getPayPrice() + ""));
        Glide.with(context).load(washRecordList.get(position).getAvatar()).into(holder.washImageView);
        holder.nameTextView.setText(washRecordList.get(position).getNickname());

        return convertView;
    }

    private class ViewHolder {
        private TextView  nameTextView;
        private ImageView washImageView;
        private TextView  carTypeTextView;
        private TextView  priceTextView;
        private TextView  timeTextView;
    }
}
