package com.xtkj.paopaoxiche.bean;

import java.io.Serializable;
import java.util.List;

public class WashServicesBean {

    /**
     * code : 200
     * data : [{"businessStatus":1,"distance":25,"honor":3,"id":6,"image":"http://localhost:8080/avatars/2018/07/15/c5fcaae570fa4114b809f2c66b5b510f.jpg","lat":22.38,"lng":114.05,"name":"林的洗车场","price":23.49,"washCount":0,"washId":4}]
     * msg : 请求成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * businessStatus : 1
         * distance : 25
         * honor : 3
         * id : 6
         * image : http://localhost:8080/avatars/2018/07/15/c5fcaae570fa4114b809f2c66b5b510f.jpg
         * lat : 22.38
         * lng : 114.05
         * name : 林的洗车场
         * price : 23.49
         * washCount : 0
         * washId : 4
         */

        private int businessStatus;
        private int distance;
        private int honor;
        private int id;
        private String image;
        private String address = "默认的地址";
        private double lat;
        private double lng;
        private String name;
        private  String worktime;
        private  String facadeImg;
        private double price;
        private int washCount;
        private int washId;
        private String phoneNum;

        public int getBusinessStatus() {
            return businessStatus;
        }

        public void setBusinessStatus(int businessStatus) {
            this.businessStatus = businessStatus;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getHonor() {
            return honor;
        }

        public void setHonor(int honor) {
            this.honor = honor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getWashCount() {
            return washCount;
        }

        public void setWashCount(int washCount) {
            this.washCount = washCount;
        }

        public int getWashId() {
            return washId;
        }

        public void setWashId(int washId) {
            this.washId = washId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWorktime() {
            return worktime;
        }

        public void setWorktime(String worktime) {
            this.worktime = worktime;
        }

        public String getFacadeImg() {
            return facadeImg;
        }

        public void setFacadeImg(String facadeImg) {
            this.facadeImg = facadeImg;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }
    }
}
