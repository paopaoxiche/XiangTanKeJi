package com.xtkj.paopaoxiche.view.DriverMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.view.WashCarMap.WashCarMapActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragmemt {


    TextView moreWashYard;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view){
        moreWashYard = view.findViewById((R.id.more_wash_yard));
        moreWashYard.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(),WashCarMapActivity.class);
            startActivity(intent);
        });

    }
}
