package com.xtkj.paopaoxiche.application;

import com.xtkj.paopaoxiche.R;

import java.util.HashMap;
import java.util.Map;

public class SkyconValues {

    public static final Map<String,String> cnNameMap = new HashMap<String,String>() ;

    public static final Map<String,Integer> homeBgMap = new HashMap<String,Integer>() ;


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


        homeBgMap.put("CLEAR_DAY", R.drawable.bg_goodday);
        homeBgMap.put("CLEAR_NIGHT", R.drawable.bg_goodnight);
        homeBgMap.put("PARTLY_CLOUDY_DAY", R.drawable.bg_cloudy_day);
        homeBgMap.put("PARTLY_CLOUDY_NIGHT", R.drawable.bg_cloudy_night);
        homeBgMap.put("CLOUDY", R.drawable.bg_cloudy);
        homeBgMap.put("RAIN", R.drawable.bg_rainy);
        homeBgMap.put("SNOW", R.drawable.bg_snow);
        homeBgMap.put("WIND", R.drawable.bg_windy);
        homeBgMap.put("HAZE", R.drawable.bg_haze);

    }
}
