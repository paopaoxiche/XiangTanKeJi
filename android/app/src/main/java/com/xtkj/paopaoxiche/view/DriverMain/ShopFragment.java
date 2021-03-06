package com.xtkj.paopaoxiche.view.DriverMain;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.DriverMapModel;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.view.view.GoodsDetailDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFragment extends BaseFragmemt implements IDriverContract.IShopView {

    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;
    Unbinder unbinder;

    private IDriverContract.IShopPresenter shopPresenter;

    private WashShopBean.DataBean mData = null;
    private WashShopBean washShopBean = null;
    private ShopItemsAdapter shopItemsAdapter = null;

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    public ShopFragment(WashShopBean.DataBean data) {
        // Required empty public constructor
        this.mData = data;
    }

    public static ShopFragment newInstance(WashShopBean.DataBean data) {
        ShopFragment fragment = new ShopFragment(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        shopPresenter.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_shop, container, false);
        unbinder = ButterKnife.bind(this, view);

        Glide.with(getActivity()).load(mData.getAvatar()).into(shopImg);

        shopName.setText(mData.getName());
        shopRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));


        shopItemsAdapter = new ShopItemsAdapter(mData);
        shopRecycler.setAdapter(shopItemsAdapter);


//        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
//                .create(WashService.class)
//                .getRecommendCommodity(MyLocation.lng +"",MyLocation.lat + "",6)
//                .enqueue(new Callback<WashShopBean>() {
//                    @Override
//                    public void onResponse(Call<WashShopBean> call, Response<WashShopBean> response) {
//                        washShopBean = response.body();
//                        shopItemsAdapter = new ShopItemsAdapter(washShopBean);
//                        shopRecycler.setAdapter(shopItemsAdapter);
//                    }
//
//                    @Override
//                    public void onFailure(Call<WashShopBean> call, Throwable t) {
//
//                    }
//                });
        return view;
    }

    @Override
    public void setPresenter(IDriverContract.IShopPresenter iShopPresenter) {
        this.shopPresenter = iShopPresenter;
    }


    class ShopItemsAdapter extends RecyclerView.Adapter<ShopItemsAdapter.ViewHolder> implements View.OnClickListener{


        private WashShopBean.DataBean  data;
        private OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                new GoodsDetailDialog(getContext(), data.getList().get(position)).show();
            }
        };

        ShopItemsAdapter(WashShopBean.DataBean dataBean) {
            this.data = dataBean;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_selling_goods, viewGroup, false);
            view.setOnClickListener(this);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setTag(position);
            Glide.with(getActivity()).load(data.getList().get(position).getImage()).into(holder.sellingGoodsImg);
            holder.sellingGoodsPrice1.setText(String.format("¥%s", data.getList().get(position).getCurrentPrice()));
            holder.sellingGoodsPrice2.setText(String.format("¥%s", data.getList().get(position).getOriginPrice()));
            holder.sellingGoodsPrice2.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public int getItemCount() {
            if(data==null||data.getList()==null)return 0;
            return data.getList().size();
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener!=null) {
                onItemClickListener.onItemClick((Integer) v.getTag());
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.selling_goods_img)
            ImageView sellingGoodsImg;
            @BindView(R.id.selling_goods_price1)
            TextView sellingGoodsPrice1;
            @BindView(R.id.selling_goods_price2)
            TextView sellingGoodsPrice2;
            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
}
