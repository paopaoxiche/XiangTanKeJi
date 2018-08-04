package com.xtkj.paopaoxiche.view.DriverMain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.IDriverContract;

public class ShopFragment extends BaseFragmemt implements IDriverContract.IShopView{

    private IDriverContract.IShopPresenter shopPresenter;


    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_shop, container, false);
    }

    @Override
    public void setPresenter(IDriverContract.IShopPresenter iShopPresenter) {
        this.shopPresenter = iShopPresenter;
    }
}
