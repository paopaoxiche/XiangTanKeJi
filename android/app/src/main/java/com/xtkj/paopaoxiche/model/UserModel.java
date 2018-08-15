package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
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

import java.io.File;

public class UserModel {

    private static UserModel instance = null;

    private PreferUtils preferUtils = PreferUtils.getInstance();

    private List<LoginListener> loginListenerList = null;

    private UserModel() {
        loginListenerList = new ArrayList<>();
    }

    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
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

        void modifyUserInfo(String modifyType);
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
                            saveData(data);
                            initData();
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

    public void updateUserInfo(String nickName, File file) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .update(nickName)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (loginListenerList == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.modifyUserInfo(AppConstant.NICK_NAME);
                            }
                        } else {
                            for (LoginListener loginListener : loginListenerList) {
                                loginListener.timeOut();
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
    
    private void saveData(LoginBean.DataBean data) {
        preferUtils.putString(AppConstant.TOKEN, data.getToken());
        preferUtils.putString(AppConstant.USER_ID, data.getId());
        if (data.getType() == 0) {
            preferUtils.putBoolean(AppConstant.IS_DRIVER, true);
        } else {
            preferUtils.putBoolean(AppConstant.IS_DRIVER, false);
        }
        preferUtils.putString(AppConstant.AVATAR, data.getAvatar());
        preferUtils.putString(AppConstant.NICK_NAME, data.getNickName());
        preferUtils.putString(AppConstant.PHONE, data.getUserPhone());
        preferUtils.putInt(AppConstant.SCORE, data.getScore());
        preferUtils.putLong(AppConstant.REGISTER_TIME, data.getRegTime());
    }

    public void initData() {
        UserInfo.setToken(preferUtils.getString(AppConstant.TOKEN));
        UserInfo.setId(preferUtils.getString(AppConstant.USER_ID));
        UserInfo.setDriver(preferUtils.getBoolean(AppConstant.IS_DRIVER,true));
        UserInfo.setAvatar(preferUtils.getString(AppConstant.AVATAR));
        UserInfo.setNickName(preferUtils.getString(AppConstant.NICK_NAME));
        UserInfo.setUserPhone(preferUtils.getString(AppConstant.PHONE));
//        UserInfo.setScore(preferUtils.getInt(AppConstant.SCORE));
        UserInfo.setRegTime(preferUtils.getLong(AppConstant.REGISTER_TIME, 0));
    }

    public static void release() {
        instance.loginListenerList = null;
        instance = null;
    }

}
