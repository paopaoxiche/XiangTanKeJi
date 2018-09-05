package com.xtkj.paopaoxiche.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.service.WashService;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterWashActivity extends BaseGaodeActivity implements DriverHomeModel.DriverHomeListener {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.address_marquee_text_view)
    TextView addressMarqueeTextView;
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
    @BindView(R.id.wash_name_edit_text)
    EditText washNameEditText;

    final int yingyezhizhaoType = 1;
    final int xichezhengType = 2;
    final int shenfenzhengzhengmianType = 3;
    final int shenfenzhenghoumianType = 4;

    File yingyezhizhaoFile;
    File xichezhengFile;
    File shenfenzhengzhengmianFile;
    File shenfenzhengfanmianFile;

    String phone;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wash);
        ButterKnife.bind(this);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                }, 1);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }

        DriverHomeModel.getInstance().addListener(this);
        DriverHomeModel.getInstance().initLocation(this);

        phone = getIntent().getExtras().getString("phone");
    }

    @Override
    public void getLocationSuccess(String address) {
        this.address = address;
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
                if (yingyezhizhaoFile != null
                        && shenfenzhengfanmianFile != null
                        && shenfenzhengzhengmianFile != null
                        && address != null) {
                    certification();
                } else {
                    if (address == null) {
                        Toast.makeText(this, "获取当前位置失败，请退出页面再重新进入", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "请上传工商认证文件", Toast.LENGTH_LONG).show();
                    }
                }
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

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    private void certification() {

        if (xichezhengFile == null) {
            xichezhengFile = yingyezhizhaoFile;
        }
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), yingyezhizhaoFile);
        MultipartBody.Part license =
                MultipartBody.Part.createFormData("license", yingyezhizhaoFile.getName(), requestFile1);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), xichezhengFile);
        MultipartBody.Part washCard =
                MultipartBody.Part.createFormData("washCard", xichezhengFile.getName(), requestFile2);

        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), shenfenzhengzhengmianFile);
        MultipartBody.Part idCardPositive =
                MultipartBody.Part.createFormData("idCardPositive", shenfenzhengzhengmianFile.getName(), requestFile3);

        RequestBody requestFile4 = RequestBody.create(MediaType.parse("multipart/form-data"), shenfenzhengfanmianFile);
        MultipartBody.Part idCardBack =
                MultipartBody.Part.createFormData("idCardBack", shenfenzhengfanmianFile.getName(), requestFile4);

        RequestBody phoneBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), phone);
        RequestBody addressBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), address);
        RequestBody xBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), MyLocation.lng);
        RequestBody yBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), MyLocation.lat);
        String name = washNameEditText.getText().toString();
        if (name == null || name.length() == 0) {
            name = phone;
        }
        RequestBody nameBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), name);

        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(WashService.class)
                .certification(phoneBody, nameBody, addressBody, xBody, yBody, license, washCard, idCardPositive, idCardBack)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() == 200) {
                            Toast.makeText(RegisterWashActivity.this, "提交成功，请等待审核", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterWashActivity.this, "提交失败，请检查提交内容", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(RegisterWashActivity.this, "网络连接失败，请稍后再试", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
