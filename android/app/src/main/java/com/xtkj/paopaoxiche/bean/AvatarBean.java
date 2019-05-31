package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class AvatarBean {

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
        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        private int id;
        private String img;
    }
}
