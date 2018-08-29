package com.xtkj.paopaoxiche.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.utils.UriUtils;
import com.xtkj.paopaoxiche.widget.MarqueeTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterWashActivity extends AppCompatActivity implements DriverHomeModel.DriverHomeListener {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.address_marquee_text_view)
    MarqueeTextView addressMarqueeTextView;
    @BindView(R.id.upload_yingyezhizhao_button)
    Button uploadYingyezhizhaoButton;
    @BindView(R.id.yingyezhizhao_image_button)
    ImageView yingyezhizhaoImageButton;
    @BindView(R.id.upload_xichezheng_button)
    Button uploadXichezhengButton;
    @BindView(R.id.xichezheng_image_button)
    ImageView xichezhengImageButton;
    @BindView(R.id.upload_zhengmian_button)
    Button uploadZhengmianButton;
    @BindView(R.id.zhengmian_image_button)
    ImageView zhengmianImageButton;
    @BindView(R.id.upload_fanmian_button)
    Button uploadFanmianButton;
    @BindView(R.id.fanmian_image_button)
    ImageView fanmianImageButton;

    final int yingyezhizhaoType = 1;
    final int xichezhengType = 2;
    final int shenfenzhengzhengmianType = 3;
    final int shenfenzhenghoumianType = 4;

    File yingyezhizhaoFile;
    File xichezhengFile;
    File shenfenzhengzhengmianFile;
    File shenfenzhengfanmianFile;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wash);
        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                }, 1);

        DriverHomeModel.getInstance().addListener(this);
        DriverHomeModel.getInstance().initLocation(this);

        phone = getIntent().getExtras().getString("phone");
    }

    @Override
    public void getLocationSuccess(String address) {
        addressMarqueeTextView.setText(address);
    }

    @Override
    public void getWashServicesSuccess(WashServicesBean washServicesBean) {

    }

    @Override
    public void getWashServicesFail() {

    }

    @Override
    public void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean) {

    }

    @Override
    public void getRealTimeWeatherFailed() {

    }

    @Override
    public void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean) {

    }

    @Override
    public void getForecastWeatherFailed() {

    }

    @Override
    public void getCommoditySuccess(WashShopBean washShopBean) {

    }

    @Override
    public void getCommodityFailed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DriverHomeModel.getInstance().removeListener(this);
    }

    @OnClick({R.id.back_arrow_image_button, R.id.complete_button, R.id.upload_yingyezhizhao_button, R.id.upload_xichezheng_button, R.id.upload_zhengmian_button, R.id.upload_fanmian_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_arrow_image_button:
                finish();
                break;
            case R.id.complete_button:
                break;
            case R.id.upload_yingyezhizhao_button:
                pickImage(yingyezhizhaoType);
                break;
            case R.id.upload_xichezheng_button:
                pickImage(xichezhengType);
                break;
            case R.id.upload_zhengmian_button:
                pickImage(shenfenzhengzhengmianType);
                break;
            case R.id.upload_fanmian_button:
                pickImage(shenfenzhenghoumianType);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = UriUtils.getImagePath(this, data.getData());
        switch (requestCode) {
            case yingyezhizhaoType:
                yingyezhizhaoFile = setImagePath(path, yingyezhizhaoImageButton);
                break;
            case xichezhengType:
                xichezhengFile = setImagePath(path, xichezhengImageButton);
                break;
            case shenfenzhengzhengmianType:
                shenfenzhengzhengmianFile = setImagePath(path, zhengmianImageButton);
                break;
            case shenfenzhenghoumianType:
                shenfenzhengfanmianFile = setImagePath(path, fanmianImageButton);
                break;
        }
    }

    private void pickImage(int type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, type);
    }

    public File setImagePath(String path, ImageView imageView) {
        Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
        String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + "MyTestImage" + File.separator
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
        imageView.setImageBitmap(bitmap);
        return compressImage(bitmap, targetPath);
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
