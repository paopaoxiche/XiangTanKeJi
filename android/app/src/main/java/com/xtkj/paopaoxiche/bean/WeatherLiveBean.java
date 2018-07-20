package com.xtkj.paopaoxiche.bean;

public class WeatherLiveBean {


    /**
     * code : 200
     * data : {"airpressure":"997.0","condition":1,"feelst":"28.7","humidity":"79.0","rain":"0.0","successful":true,"temperature":"28.6","winddirect":"东南风","windpower":"微风","windspeed":"1.6"}
     * msg : 请求成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * airpressure : 997.0
         * condition : 1
         * feelst : 28.7
         * humidity : 79.0
         * rain : 0.0
         * successful : true
         * temperature : 28.6
         * winddirect : 东南风
         * windpower : 微风
         * windspeed : 1.6
         */

        private String airpressure;
        private String condition;
        private String feelst;
        private String humidity;
        private String rain;
        private boolean successful;
        private String temperature;
        private String winddirect;
        private String windpower;
        private String windspeed;

        public String getAirpressure() {
            return airpressure;
        }

        public void setAirpressure(String airpressure) {
            this.airpressure = airpressure;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getFeelst() {
            return feelst;
        }

        public void setFeelst(String feelst) {
            this.feelst = feelst;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getRain() {
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public boolean isSuccessful() {
            return successful;
        }

        public void setSuccessful(boolean successful) {
            this.successful = successful;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }
    }
}

