package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class BannerAdBean {
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        public String getUrl() {
            return url;
        }

        public String getImg() {
            return img;
        }

        public String getMd5() {
            return md5;
        }

        private String url;
        private String md5;
        private String img;
    }
}
