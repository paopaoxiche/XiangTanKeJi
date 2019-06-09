package com.xtkj.paopaoxiche.application;

import com.xtkj.paopaoxiche.R;

import java.util.HashMap;
import java.util.Map;

public class SkyconValues {

    public static final Map<String,String> cnNameMap = new HashMap<String,String>() ;
    public static final Map<String,Integer> homeIconMap = new HashMap<String,Integer>() ;
    public static final Map<String,Integer> weatherBgMap = new HashMap<String,Integer>() ;

    public static final Map<String,Integer> forecastBgMap = new HashMap<String,Integer>() ;
    public static final Map<String,Integer> forecastIconMap = new HashMap<String,Integer>() ;

    static {

        cnNameMap.put("CLEAR_DAY","晴天") ;
        cnNameMap.put("CLEAR_NIGHT","晴夜") ;
        cnNameMap.put("PARTLY_CLOUDY_DAY","多云") ;
        cnNameMap.put("PARTLY_CLOUDY_NIGHT","多云") ;
        cnNameMap.put("CLOUDY","阴") ;
        cnNameMap.put("RAIN","雨") ;
        cnNameMap.put("SNOW","雪") ;
        cnNameMap.put("WIND","风") ;
        cnNameMap.put("HAZE","雾霾沙尘") ;


        homeIconMap.put("CLEAR_DAY", R.drawable.bg_goodday);
        homeIconMap.put("CLEAR_NIGHT", R.drawable.bg_goodnight);
        homeIconMap.put("PARTLY_CLOUDY_DAY", R.drawable.bg_cloudy);
        homeIconMap.put("PARTLY_CLOUDY_NIGHT", R.drawable.bg_cloudy);
        homeIconMap.put("CLOUDY", R.drawable.bg_cloudy);
        homeIconMap.put("RAIN", R.drawable.bg_rainy);
        homeIconMap.put("SNOW", R.drawable.bg_snow);
        homeIconMap.put("WIND", R.drawable.bg_windy);
        homeIconMap.put("HAZE",R.drawable.bg_nothing);


        weatherBgMap.put("CLEAR_DAY", R.drawable.bg_weather_good_day);
        weatherBgMap.put("CLEAR_NIGHT", R.drawable.bg_weather_good_night);
        weatherBgMap.put("PARTLY_CLOUDY_DAY", R.drawable.bg_weather_cloudy_day);
        weatherBgMap.put("PARTLY_CLOUDY_NIGHT", R.drawable.bg_weather_cloudy_night);
        weatherBgMap.put("CLOUDY", R.drawable.bg_weather_cloudy);
        weatherBgMap.put("RAIN", R.drawable.bg_weather_snow);
        weatherBgMap.put("SNOW", R.drawable.bg_weather_snow);
        weatherBgMap.put("WIND", R.drawable.bg_weather_windy);
        weatherBgMap.put("HAZE",R.drawable.bg_weather_haze);


        forecastBgMap.put("CLEAR_DAY", R.drawable.bg_forecast_good_day);
        forecastBgMap.put("CLEAR_NIGHT", R.drawable.bg_forecast_good_night);
        forecastBgMap.put("PARTLY_CLOUDY_DAY", R.drawable.bg_forecast_cloudy);
        forecastBgMap.put("PARTLY_CLOUDY_NIGHT", R.drawable.bg_forecast_cloudy);
        forecastBgMap.put("CLOUDY", R.drawable.bg_forecast_cloudy);
        forecastBgMap.put("RAIN", R.drawable.bg_forecast_rain);
        forecastBgMap.put("SNOW", R.drawable.bg_forecast_snow);
        forecastBgMap.put("WIND", R.drawable.bg_forecast_wind);
        forecastBgMap.put("HAZE", R.drawable.bg_nothing);

        forecastIconMap.put("CLEAR_DAY", R.drawable.forecast_good_day);
        forecastIconMap.put("CLEAR_NIGHT", R.drawable.forecast_good_night);
        forecastIconMap.put("PARTLY_CLOUDY_DAY", R.drawable.forecast_cloudy_day);
        forecastIconMap.put("PARTLY_CLOUDY_NIGHT", R.drawable.forecast_cloudy_night);
        forecastIconMap.put("CLOUDY", R.drawable.forecast_cloudy);
        forecastIconMap.put("RAIN", R.drawable.forecast_rain);
        forecastIconMap.put("SNOW", R.drawable.forecast_snow);
        forecastIconMap.put("WIND", R.drawable.forecast_wind);
        forecastIconMap.put("HAZE",R.drawable.forecast_haze);
    }
}
