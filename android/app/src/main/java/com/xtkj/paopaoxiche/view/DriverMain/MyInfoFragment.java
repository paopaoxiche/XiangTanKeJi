package com.xtkj.paopaoxiche.view.DriverMain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;

public class MyInfoFragment extends BaseFragmemt{

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance() {
        MyInfoFragment fragment = new MyInfoFragment();
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
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }


}
