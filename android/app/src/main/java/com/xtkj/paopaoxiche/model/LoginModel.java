package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.UserInfo;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.PreferUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {

    private static LoginModel instance = null;

    private List<LoginListener> loginListenerList = null;

    private LoginModel() {
        loginListenerList = new ArrayList<>();
    }

    public static LoginModel getInstance() {
        if (instance == null) {
            instance = new LoginModel();
        }
        return instance;
    }

    public void addListener(LoginListener loginListener) {
        loginListenerList.add(loginListener);
    }

    public void removeListener(LoginListener loginListener) {
        loginListenerList.remove(loginListener);
    }

    public interface LoginListener {
        void getCodeSuccess();
        void getCodeFail();
        void loginSuccess(LoginBean.DataBean dataBean);
        void loginFail();
        void timeOut();
        void checkTokenSuccess();
    }

    public void checkCarWashToken(){
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .checkCarWash()
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() != 401) {
                            if (loginListenerList == null) {
                                return;
                            }
                            if (response.body().getCode() != 401) {
                                for (LoginListener loginListener : loginListenerList) {
                                    loginListener.checkTokenSuccess();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        if (loginListenerList == null) {
                            return;
                        }
                        for (LoginListener loginListener : loginListenerList) {
                            loginListener.timeOut();
                        }
                    }
                });
    }

    public void checkDriverToken(){
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .checkCarOwner()
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (loginListenerList == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.checkTokenSuccess();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        if (loginListenerList == null) {
                            return;
                        }
                        for (LoginListener loginListener : loginListenerList) {
                            loginListener.timeOut();
                        }
                    }
                });
    }

    public void getMessageCode(String phone) {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(UserService.class)
                .getMessageCode(phone)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (loginListenerList == null) {
                            return;
                        }
                        if (response.body().getCode() == 200) {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.getCodeSuccess();
                            }
                        } else {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.getCodeFail();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        if (loginListenerList == null) {
                            return;
                        }
                        for (LoginListener loginListener : loginListenerList) {
                            loginListener.timeOut();
                        }
                    }
                });
    }

    public void doLogin(String account, long code, int type) {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(UserService.class)
                .Login(account, code, type)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        if (loginListenerList == null) {
                            return;
                        }
                        LoginBean.DataBean data = response.body().getData();
                        if (response.body().getCode() == 200) {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.loginSuccess(data);
                            }
                        } else {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.loginFail();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        if (loginListenerList == null) {
                            return;
                        }
                        for (LoginListener loginListener : loginListenerList) {
                            loginListener.timeOut();
                        }
                    }
                });
    }

    public static void release() {
        instance.loginListenerList = null;
        instance = null;
    }

}
