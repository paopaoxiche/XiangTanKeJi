package com.xtkj.paopaoxiche.view.DriverMain.Ad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdAdpter extends RecyclerView.Adapter<AdAdpter.ViewHolder> {



    private ArrayList<String> data;
    public Context mContext;

    public AdAdpter(ArrayList<String> strings, Context context) {
        this.data = strings;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ad, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemAd.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data != null) return data.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ad)
        TextView itemAd;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
