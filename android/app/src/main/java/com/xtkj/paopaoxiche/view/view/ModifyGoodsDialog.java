package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.model.GoodsModel;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.widget.CashierInputFilter;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyGoodsDialog extends FullScreenWithStatusBarDialog {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_current_price)
    EditText goodsCurrentPrice;
    @BindView(R.id.goods_original_price)
    EditText goodsOriginalPrice;
    @BindView(R.id.describe_edit_text)
    EditText describeEditText;
    @BindView(R.id.upload_button)
    Button uploadButton;
    @BindView(R.id.goods_image_view)
    ImageView goodsImageView;

    int id = 0;

    File imageFile;

    public ModifyGoodsDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_goods);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        InputFilter[] filters = {new CashierInputFilter()};
        goodsCurrentPrice.setFilters(filters);
        goodsOriginalPrice.setFilters(filters);
    }

    public ModifyGoodsDialog(Context context, boolean statusBarVisible, WashShopBean.DataBean dataBean) {
        this(context, statusBarVisible);
        id = dataBean.getId();
        goodsName.setText(dataBean.getName());
        goodsCurrentPrice.setText(dataBean.getCurrentPrice() + "");
        goodsOriginalPrice.setText(dataBean.getOriginPrice() + "");
        describeEditText.setText(dataBean.getDescribe());
        Glide.with(getContext()).load(dataBean.getImage()).into(goodsImageView);
    }

    @Override
    public void show() {
        super.show();
        EventBus.getDefault().register(this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.complete_button, R.id.upload_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_button:
                if (!TextUtils.isEmpty(describeEditText.getText().toString())
                        && !TextUtils.isEmpty(goodsName.getText().toString())
                        && !TextUtils.isEmpty(goodsCurrentPrice.getText().toString())
                        && !TextUtils.isEmpty(goodsOriginalPrice.getText().toString())
                        && (imageFile != null || id != 0)) {
                    GoodsModel.getInstance().addGoods(id, goodsName.getText().toString(),
                            goodsCurrentPrice.getText().toString(),
                            goodsOriginalPrice.getText().toString(),
                            describeEditText.getText().toString(),
                            imageFile);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "请正确输入信息", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.upload_button:
                EventBus.getDefault().post(new BaseEvent(BaseEvent.GOODS_IMAGE));
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent) {
        switch (baseEvent.getType()) {
            case BaseEvent.SET_GOODS_IMAGE:
                setImagePath(baseEvent.getStringValue());
                break;
        }
    }

    /**
     * 设置上传的图片地址
     */
    public void setImagePath(String path) {
        Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
        String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + "MyTestImage" + File.separator
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
        imageFile = compressImage(bitmap, targetPath);
        goodsImageView.setImageBitmap(bitmap);
        goodsImageView.setVisibility(View.VISIBLE);
    }

    private File compressImage(Bitmap bm, String targetPath) {

        int quality = 80;//压缩比例0-100

        File outputFile = new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return outputFile;
    }


}
