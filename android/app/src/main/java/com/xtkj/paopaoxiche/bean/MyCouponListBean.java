package com.xtkj.paopaoxiche.bean;

import java.util.List;

/**
 * 我的优惠券列表
 * Created by sky on 2018/8/26.
 */
public class MyCouponListBean {

    /**
     * code : 200
     * data : [{"couponType":1,"discount":"","endDate":"2018-08-31 07:22:17","id":1,"price":"5.0","status":1,"title":"满20减5",
     * "washHeader":"http://localhost:8080/avatars/null","washName":""}]
     * msg : 请求成功
     * pageIndex : 0
     * pageSize : 20
     * totalCount : 1
     */

    private int code;
    private String msg;
    private int pageIndex;
    private int pageSize;
    private int totalCount;
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * couponType : 1
         * discount :
         * endDate : 2018-08-31 07:22:17
         * id : 1
         * price : 5.0
         * status : 1
         * title : 满20减5
         * washHeader : http://localhost:8080/avatars/null
         * washName :
         */

        private int couponType;
        private String discount;
        private String endDate;
        private int id;
        private String price;
        private int status;
        private String title;
        private String washHeader;
        private String washName;

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWashHeader() {
            return washHeader;
        }

        public void setWashHeader(String washHeader) {
            this.washHeader = washHeader;
        }

        public String getWashName() {
            return washName;
        }

        public void setWashName(String washName) {
            this.washName = washName;
        }
    }
}
