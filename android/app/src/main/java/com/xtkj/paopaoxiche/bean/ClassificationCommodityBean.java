package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class ClassificationCommodityBean {

    /**
     * code : 200
     * data : [{"address":"深圳市","detail":"保险业务1","discountPrice":"10.0","img":"http://101.200.63.245:8080/ad/2018/09/12/50b8602e04034e279e3f5b9676e10086.jpg","originalPrice":"15.0","phone":"13800138000","type":"保险业务","url":"http://www.baidu.com"}]
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

    public static class DataBean {
        /**
         * address : 深圳市
         * detail : 保险业务1
         * discountPrice : 10.0
         * img : http://101.200.63.245:8080/ad/2018/09/12/50b8602e04034e279e3f5b9676e10086.jpg
         * originalPrice : 15.0
         * phone : 13800138000
         * type : 保险业务
         * url : http://www.baidu.com
         */

        private String address;
        private String detail;
        private String discountPrice;
        private String img;
        private String originalPrice;
        private String phone;
        private String type;
        private String url;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
