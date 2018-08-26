package com.xtkj.paopaoxiche.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

public class SureDialog extends Dialog implements View.OnClickListener {
	private Button btnCancel;
	private Button btnSure;
	private TextView tvMessage;
	private ClickListener listener;

	public SureDialog(Context context, int themeResId) {
		super(context, themeResId);
		setContentView(R.layout.dialog_delete);
		initView();
		initData();
	}

	private void initView() {
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		btnSure = (Button) findViewById(R.id.btn_sure);
		tvMessage = (TextView) findViewById(R.id.tv_message);
	}

	private void initData() {
		btnCancel.setOnClickListener(this);
		btnSure.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_cancel:
				dismiss();
				break;

			case R.id.btn_sure:
				dismiss();
				if (listener != null) {
					listener.sure(this);
				}
				break;
		}
	}

	public interface ClickListener {
		void sure(SureDialog dialog);
	}

	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}

	/**
	 * 设置cancel看见属性
	 * @param visibility 看见属性
	 */
	public void setCancelBtnVisibility(int visibility) {
		if (visibility != View.GONE && visibility != View.INVISIBLE && visibility != View.VISIBLE) {
			return;
		}
		btnCancel.setVisibility(visibility);
	}

	/**
	 * 设置Dialog的显示消息
	 * @param message 显示消息
	 */
	public void setMessage(String message) {
		if (message == null) {
			message = "";
		}
		tvMessage.setText(message);
	}
}
