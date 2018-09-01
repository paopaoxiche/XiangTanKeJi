package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class RecentWashListBean {

    private int code;
    private String msg;
    private List<RecentWashListBean.DataBean> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<RecentWashListBean.DataBean> getData() {
        return data;
    }

    public static class DataBean {
        String avatar;
        String carType;
        int id;
        String nickname;
        double payPrice;
        long time;

        public String getAvatar() {
            return avatar;
        }

        public String getCarType() {
            return carType;
        }

        public int getId() {
            return id;
        }

        public String getNickname() {
            return nickname;
        }

        public double getPayPrice() {
            return payPrice;
        }

        public long getTime() {
            return time;
        }
    }
}
