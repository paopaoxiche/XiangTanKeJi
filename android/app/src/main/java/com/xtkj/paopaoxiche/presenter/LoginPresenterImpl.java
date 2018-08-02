package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.application.GlobalFiled;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.PreferUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements ILoginContract.ILoginPresenter {

    ILoginContract.ILoginView loginView;

    public LoginPresenterImpl(ILoginContract.ILoginView iLoginView) {
        loginView = iLoginView;
        iLoginView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        String account= PreferUtils.getInstance(loginView.getContext()).getString(AppConstant.ACCOUNT);
        loginView.initAccount(account);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getMessageCode(String phone) {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(UserService.class)
                .getMessageCode(phone)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if(response.body().getCode()==200){
                            loginView.showToast(response.body().getMsg());
                        }else {
                            loginView.showToast(response.body().getMsg());
                            loginView.resetSentMsgButton();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        loginView.showToast(AppConstant.NET_ERROR);
                    }
                });

    }

    @Override
    public void doLogin(String account, long code,int type) {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(UserService.class)
                .Login(account,code,type)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        loginView.showToast("登陆成功");
                        PreferUtils.getInstance(loginView.getContext()).putString(AppConstant.TOKEN,response.body().getData().getToken());

                        GlobalFiled globalFiled = (GlobalFiled) loginView.getContext().getApplicationContext();
                        globalFiled.getUser().setToken(response.body().getData().getToken());

                        loginView.login();
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        loginView.showToast(AppConstant.NET_ERROR);
                    }
                });




        PreferUtils.getInstance(loginView.getContext()).putString(AppConstant.ACCOUNT,account);
    }
}
