package com.xtkj.paopaoxiche.view.WashService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WashServiceActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.service_items)
    LinearLayout serviceItems;
    @BindView(R.id.ship_list)
    LinearLayout shipList;
    @BindView(R.id.pay_button)
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_service);
        ButterKnife.bind(this);
    }
}
