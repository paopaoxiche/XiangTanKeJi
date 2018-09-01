package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2018/9/1.
 */
class AddCarDialog extends FullScreenWithStatusBarDialog implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.rb_big_car)
    RadioButton rbBigCar;
    @BindView(R.id.rb_middle_car)
    RadioButton rbMiddleCar;
    @BindView(R.id.rb_small_car)
    RadioButton rbSmallCar;
    @BindView(R.id.rg_car_select)
    RadioGroup rgCarSelect;
    @BindView(R.id.rl_car_type_choice)
    LinearLayout rlCarTypeChoice;
    @BindView(R.id.tv_car_certificate_info)
    TextView tvCarCertificateInfo;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.cover_photo)
    RelativeLayout coverPhoto;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.back_photo)
    RelativeLayout backPhoto;

    File coverFile;
    File backFile;

    int car_type = 3;

    public AddCarDialog(Context context) {
        super(context, true);
        setContentView(R.layout.dialog_add_car);
        ButterKnife.bind(this);
        backArrowImageButton.setOnClickListener(backButtonClickListener);
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

    @OnClick({R.id.cover_photo, R.id.back_photo, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cover_photo:
                EventBus.getDefault().post(new BaseEvent(BaseEvent.CAR_PHOTO_CONER));
                break;
            case R.id.back_photo:
                EventBus.getDefault().post(new BaseEvent(BaseEvent.CAR_PHOTO_BACK));
                break;
            case R.id.tv_commit:
                if (backFile != null && coverFile != null) {
                    commit();
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "请上传文件", Toast.LENGTH_LONG).show();
                }
        }
    }

    private void commit() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), coverFile);
        MultipartBody.Part coverBody = MultipartBody.Part.createFormData("cover", coverFile.getName(), requestFile);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), backFile);
        MultipartBody.Part backBody = MultipartBody.Part.createFormData("back", coverFile.getName(), requestFile1);
        RequestBody idBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(car_type));


        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .carRegister(car_type, coverBody, backBody)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() != 401) {
                            Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "提交失败，请重新登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(getContext(), "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent) {
        String path = baseEvent.getStringValue();
        if (baseEvent.getType() == BaseEvent.SET_CAR_COVER) {
            Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
            String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + File.separator + "MyTestImage" + File.separator
                    + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
            coverFile = compressImage(bitmap, targetPath);
            ivCover.setImageBitmap(bitmap);
            ivCover.setVisibility(View.VISIBLE);
        }
        if (baseEvent.getType() == BaseEvent.SET_CAR_BACK) {
            Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
            String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + File.separator + "MyTestImage" + File.separator
                    + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
            backFile = compressImage(bitmap, targetPath);
            ivBack.setImageBitmap(bitmap);
            ivBack.setVisibility(View.VISIBLE);
        }
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_big_car:
                car_type = 1;
                break;
            case R.id.rb_middle_car:
                car_type = 2;
                break;
            case R.id.rb_small_car:
                car_type = 3;
                break;
        }
    }
}
