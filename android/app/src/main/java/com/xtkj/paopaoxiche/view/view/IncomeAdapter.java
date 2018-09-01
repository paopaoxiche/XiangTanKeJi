package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.IncomeBean;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeAdapter extends BaseAdapter {

    List<IncomeBean.DataBean> dataBeanList = new ArrayList<>();

    private Context context;

    public IncomeAdapter(Context context, List<IncomeBean.DataBean> dataBeans) {
        dataBeanList.addAll(dataBeans);
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_income, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.timeTextView = convertView.findViewById(R.id.time_text_view);
            holder.dayTotalTextView = (TextView) convertView.findViewById(R.id.day_total_text_view);
            holder.itemIncomeListView = (ListView) convertView.findViewById(R.id.item_income_list_view);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IncomeBean.DataBean dataBean = dataBeanList.get(position);

        String time = new SimpleDateFormat("MM-dd").format(new Date(dataBean.getTime()));
        holder.timeTextView.setText(time);

        String money = "共计收入" + dataBean.getTotalMoney() + "元";
        holder.dayTotalTextView.setText(money);

        ItemIncomeAdapter itemIncomeAdapter = new ItemIncomeAdapter(context, dataBean.getItems());
        holder.itemIncomeListView.setAdapter(itemIncomeAdapter);

        return convertView;
    }

    private class ViewHolder {
        TextView timeTextView;
        TextView dayTotalTextView;
        ListView itemIncomeListView;
    }
}
