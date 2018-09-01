package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.IncomeBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemIncomeAdapter extends BaseAdapter {

    List<IncomeBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();

    private Context context;

    public ItemIncomeAdapter(Context context, List<IncomeBean.DataBean.ItemsBean> itemsBeans) {
        itemsBeanList.addAll(itemsBeans);
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemsBeanList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_item_income, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.carTypeTextView = convertView.findViewById(R.id.car_type_text_view);
            holder.moneyTextView = (TextView) convertView.findViewById(R.id.money_text_view);
            holder.carImageView = convertView.findViewById(R.id.car_image_view);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IncomeBean.DataBean.ItemsBean itemsBean = itemsBeanList.get(position);

        holder.carTypeTextView.setText(itemsBean.getCarTypeText());
        holder.moneyTextView.setText(itemsBean.getMoney() + "");
        switch (itemsBean.getCarType()) {
            case 1:
                holder.carImageView.setImageResource(R.drawable.icon_car1);
                break;
            case 2:
                holder.carImageView.setImageResource(R.drawable.icon_car2);
                break;
            case 3:
                holder.carImageView.setImageResource(R.drawable.icon_car3);
                break;
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView carImageView;
        TextView carTypeTextView;
        TextView moneyTextView;
    }
}
