package com.xtkj.paopaoxiche.view.view;

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
import com.xtkj.paopaoxiche.bean.WashShopBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends BaseAdapter{

    private Context context;

    List<WashShopBean.DataBean> goodsList = new ArrayList<>();

    public GoodsListAdapter(Context context, List<WashShopBean.DataBean> dataBeans) {
        goodsList.addAll(dataBeans);
        this.context = context;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return goodsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_list, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.goodsImageView = (ImageView) convertView.findViewById(R.id.goods_image_view);
            holder.describeTextView = (TextView) convertView.findViewById(R.id.goods_describe_text_view);
            holder.currentPriceTextView = (TextView) convertView.findViewById(R.id.current_price_text_view);
            holder.originalPriceTextView = convertView.findViewById(R.id.original_price_text_view);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.describeTextView.setText(goodsList.get(position).getDescribe());
        holder.originalPriceTextView.setText(String.format("¥%s", goodsList.get(position).getOriginPrice()));
        holder.currentPriceTextView.setText(String.format("¥%s", goodsList.get(position).getCurrentPrice()));
        Glide.with(context).load(goodsList.get(position).getImage()).into(holder.goodsImageView);
        holder.originalPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        return convertView;
    }

    private class ViewHolder {
        private ImageView goodsImageView;
        private TextView  describeTextView;
        private TextView  currentPriceTextView;
        private TextView  originalPriceTextView;
    }
}
