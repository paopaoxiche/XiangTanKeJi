package com.xtkj.paopaoxiche.bean;

public class CarWashInfoBean {

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

    public class DataBean {
        private int authStatus;
        private int businessState;
        private int id;
        private int washCount;
        private String honor;

        public int getBusinessState() {
            return businessState;
        }

        public void setBusinessState(int businessState) {
            this.businessState = businessState;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWashCount() {
            return washCount;
        }

        public void setWashCount(int washCount) {
            this.washCount = washCount;
        }

        public String getHonor() {
            return honor;
        }

        public void setHonor(String honor) {
            this.honor = honor;
        }
    }
}
