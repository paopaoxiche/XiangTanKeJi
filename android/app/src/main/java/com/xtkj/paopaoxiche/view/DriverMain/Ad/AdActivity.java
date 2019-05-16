package com.xtkj.paopaoxiche.view.DriverMain.Ad;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.model.DriverHomeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdActivity extends BaseActivity {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ad_recycler)
    RecyclerView adRecycler;

    private AdAdpter adAdpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);

        adRecycler.setLayoutManager(new LinearLayoutManager(this));
        adAdpter = new AdAdpter(DriverHomeModel.getInstance().getAdStrings(),this);
        adRecycler.setAdapter(adAdpter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {


    }
}
