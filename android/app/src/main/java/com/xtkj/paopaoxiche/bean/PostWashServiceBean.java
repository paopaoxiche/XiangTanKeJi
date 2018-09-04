package com.xtkj.paopaoxiche.bean;

public class PostWashServiceBean {

    private int washServiceId;
    private String commoditys = new String();
    private int couponId;
    private int payType;

    public int getWashServiceId() {
        return washServiceId;
    }

    public void setWashServiceId(int washServiceId) {
        this.washServiceId = washServiceId;
    }

    public String getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(String commoditys) {
        this.commoditys = commoditys;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
