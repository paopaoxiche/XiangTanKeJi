package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class WashRecordBean {

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
        int carModel;
        int carWashId;
        String describe;
        int id;
        boolean isActice;
        String name;
        float price;

        public int getCarModel() {
            return carModel;
        }

        public void setCarModel(int carModel) {
            this.carModel = carModel;
        }

        public int getCarWashId() {
            return carWashId;
        }

        public void setCarWashId(int carWashId) {
            this.carWashId = carWashId;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isActice() {
            return isActice;
        }

        public void setActice(boolean actice) {
            isActice = actice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
