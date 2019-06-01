package com.xtkj.paopaoxiche.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseDialog;
import com.xtkj.paopaoxiche.bean.WashTimeBean;
import com.xtkj.paopaoxiche.callback.WashTimeCallback;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Date;

public class WashDatePickerDialog extends AppCompatDialog implements View.OnClickListener, TimePicker.OnTimeChangedListener {

    TimePicker timePicker;
    Button confirmButton;
    TextView switchTimeTextView;
    TextView chooseTimeTextView;

    boolean isStartTime = true;
    WashTimeBean startTime;
    WashTimeBean endTime;
    WashTimeCallback callback;


    public WashDatePickerDialog(Context context, WashTimeBean startTime, WashTimeBean endTime, WashTimeCallback washTimeCallback) {
        super(context, R.style.dialog_full_screen_with_status_bar_style);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_wash_date_picker, null);
        setContentView(view);
        initStyle(context);

        timePicker = view.findViewById(R.id.wash_time_picker);
        confirmButton = view.findViewById(R.id.time_confirm_button);
        switchTimeTextView = view.findViewById(R.id.choose_time_text_view);
        chooseTimeTextView = view.findViewById(R.id.please_choose_time_text_view);

        this.startTime = startTime;
        this.endTime = endTime;
        this.callback = washTimeCallback;

        confirmButton.setOnClickListener(this);
        switchTimeTextView.setOnClickListener(this);
        timePicker.setIs24HourView(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(startTime.hour);
            timePicker.setMinute(startTime.minute);
        }
        timePicker.setOnTimeChangedListener(this);
        String minute = endTime.minute >= 10 ? endTime.minute + "" : "0" + endTime.minute;
        switchTimeTextView.setText("结束时间 " + endTime.hour + ":" + minute);
    }

    @Override
    public void show() {
        super.show();

    }

    private void initStyle(Context context) {
        setCancelable(true);
        try {
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
//            int animation = R.style.SharePanelAnimation;
//            getWindow().setWindowAnimations(animation);
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (!(context instanceof Activity)) {
                getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.choose_time_text_view) {
            isStartTime = !isStartTime;
            String minute = new String();
            if (!isStartTime) {
                minute = startTime.minute >= 10 ? startTime.minute + "" : "0" + startTime.minute;
                chooseTimeTextView.setText("请选择结束时间");
                switchTimeTextView.setText("开始时间 " + startTime.hour + ":" + minute);
            } else {
                minute = endTime.minute >= 10 ? endTime.minute + "" : "0" + endTime.minute;
                chooseTimeTextView.setText("请选择开始时间");
                switchTimeTextView.setText("结束时间 " + endTime.hour + ":" + minute);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isStartTime) {
                    int m = startTime.minute;
                    timePicker.setHour(startTime.hour);
                    timePicker.setMinute(m);
                } else {
                    int m = endTime.minute;
                    timePicker.setHour(endTime.hour);
                    timePicker.setMinute(m);
                }
            }
        } else if (v.getId() == R.id.time_confirm_button) {
            callback.confirmWashTime(startTime, endTime);
            dismiss();
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (isStartTime) {
            startTime.hour = hourOfDay;
            startTime.minute = minute;
        } else {
            endTime.hour = hourOfDay;
            endTime.minute = minute;
        }
    }
}
