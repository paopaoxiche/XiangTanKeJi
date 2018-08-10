package com.xtkj.paopaoxiche.http;

public class ApiField {
    public static final String BASEURL = "http://101.200.63.245:8080/";
    private static final String WEATHERURL = "https://api.caiyunapp.com/v2/kIoCAW4NzT21BFHQ/";

    public static String getBaseWeatherUrl(double j,double w){
        return WEATHERURL + j + "," + w +"/";
    }

}
