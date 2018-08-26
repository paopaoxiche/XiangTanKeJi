package com.xtkj.paopaoxiche.bean;

import java.util.List;

/**
 * 所有优惠券列表
 * Created by sky on 2018/8/26.
 */
public class CouponListBean {
    /**
     * code : 200
     * data : [{"couponName":"满20减5","denomination":5,"detail":"满20减5","endTime":1535699882000,"id":1,"issuer":0,"points":50,
     * "startTime":1533367081000}]
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
         * couponName : 满20减5
         * denomination : 5
         * detail : 满20减5
         * endTime : 1535699882000
         * id : 1
         * issuer : 0
         * points : 50
         * startTime : 1533367081000
         */

        private String couponName;
        private int denomination;
        private String detail;
        private long endTime;
        private int id;
        private int issuer;
        private int points;
        private long startTime;

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public int getDenomination() {
            return denomination;
        }

        public void setDenomination(int denomination) {
            this.denomination = denomination;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIssuer() {
            return issuer;
        }

        public void setIssuer(int issuer) {
            this.issuer = issuer;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }
}
