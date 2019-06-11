package com.xtkj.paopaoxiche.bean;

import com.google.gson.annotations.SerializedName;

public class CreateConsumeBean {


    /**
     * code : 200
     * data : {"aliPay":"","wxPay":{"appid":"wx129f550ad02f9cd2","noncestr":"8Wc1kpjfS4nNOpNT","outTradeNo":"15352785553241000000201","package":"Sign=WXPay","partnerid":"1511685231","prepayid":"wx26181558324587605b9640a31757251354","sign":"F29E92D68647264A5E083DAC1C92F595","timestamp":"1535278556"},"payType":1}
     * msg : 请求成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * aliPay :
         * wxPay : {"appid":"wx129f550ad02f9cd2","noncestr":"8Wc1kpjfS4nNOpNT","outTradeNo":"15352785553241000000201","package":"Sign=WXPay","partnerid":"1511685231","prepayid":"wx26181558324587605b9640a31757251354","sign":"F29E92D68647264A5E083DAC1C92F595","timestamp":"1535278556"}
         * payType : 1
         */

        private String aliPay;
        private WxPayBean wxPay;
        private int payType;
        private int consumeId;

        public int getConsumeId() {
            return consumeId;
        }

        public void setConsumeId(int consumeId) {
            this.consumeId = consumeId;
        }

        public String getAliPay() {
            return aliPay;
        }

        public void setAliPay(String aliPay) {
            this.aliPay = aliPay;
        }

        public WxPayBean getWxPay() {
            return wxPay;
        }

        public void setWxPay(WxPayBean wxPay) {
            this.wxPay = wxPay;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public static class WxPayBean {
            /**
             * appid : wx129f550ad02f9cd2
             * noncestr : 8Wc1kpjfS4nNOpNT
             * outTradeNo : 15352785553241000000201
             * package : Sign=WXPay
             * partnerid : 1511685231
             * prepayid : wx26181558324587605b9640a31757251354
             * sign : F29E92D68647264A5E083DAC1C92F595
             * timestamp : 1535278556
             */

            private String appid;
            private String noncestr;
            private String outTradeNo;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private String sign;
            private String timestamp;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getOutTradeNo() {
                return outTradeNo;
            }

            public void setOutTradeNo(String outTradeNo) {
                this.outTradeNo = outTradeNo;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
