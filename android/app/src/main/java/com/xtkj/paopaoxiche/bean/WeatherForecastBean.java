package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class WeatherForecastBean {


    /**
     * status : ok
     * lang : zh_CN
     * result : {"hourly":{"status":"ok","description":"多云，今天傍晚19点钟后转小雨","skycon":[{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 18:00"},{"value":"RAIN","datetime":"2018-08-10 19:00"},{"value":"RAIN","datetime":"2018-08-10 20:00"},{"value":"RAIN","datetime":"2018-08-10 21:00"},{"value":"RAIN","datetime":"2018-08-10 22:00"},{"value":"CLOUDY","datetime":"2018-08-10 23:00"},{"value":"CLOUDY","datetime":"2018-08-11 00:00"},{"value":"CLOUDY","datetime":"2018-08-11 01:00"},{"value":"CLOUDY","datetime":"2018-08-11 02:00"},{"value":"CLOUDY","datetime":"2018-08-11 03:00"},{"value":"CLOUDY","datetime":"2018-08-11 04:00"},{"value":"CLOUDY","datetime":"2018-08-11 05:00"},{"value":"CLOUDY","datetime":"2018-08-11 06:00"},{"value":"CLOUDY","datetime":"2018-08-11 07:00"},{"value":"CLOUDY","datetime":"2018-08-11 08:00"},{"value":"CLOUDY","datetime":"2018-08-11 09:00"},{"value":"CLOUDY","datetime":"2018-08-11 10:00"},{"value":"CLOUDY","datetime":"2018-08-11 11:00"},{"value":"CLOUDY","datetime":"2018-08-11 12:00"},{"value":"CLOUDY","datetime":"2018-08-11 13:00"},{"value":"RAIN","datetime":"2018-08-11 14:00"},{"value":"RAIN","datetime":"2018-08-11 15:00"},{"value":"RAIN","datetime":"2018-08-11 16:00"},{"value":"RAIN","datetime":"2018-08-11 17:00"},{"value":"RAIN","datetime":"2018-08-11 18:00"},{"value":"RAIN","datetime":"2018-08-11 19:00"},{"value":"RAIN","datetime":"2018-08-11 20:00"},{"value":"RAIN","datetime":"2018-08-11 21:00"},{"value":"RAIN","datetime":"2018-08-11 22:00"},{"value":"RAIN","datetime":"2018-08-11 23:00"},{"value":"RAIN","datetime":"2018-08-12 00:00"},{"value":"RAIN","datetime":"2018-08-12 01:00"},{"value":"RAIN","datetime":"2018-08-12 02:00"},{"value":"RAIN","datetime":"2018-08-12 03:00"},{"value":"RAIN","datetime":"2018-08-12 04:00"},{"value":"RAIN","datetime":"2018-08-12 05:00"},{"value":"CLOUDY","datetime":"2018-08-12 06:00"},{"value":"CLOUDY","datetime":"2018-08-12 07:00"},{"value":"RAIN","datetime":"2018-08-12 08:00"},{"value":"RAIN","datetime":"2018-08-12 09:00"},{"value":"RAIN","datetime":"2018-08-12 10:00"},{"value":"RAIN","datetime":"2018-08-12 11:00"},{"value":"RAIN","datetime":"2018-08-12 12:00"},{"value":"RAIN","datetime":"2018-08-12 13:00"},{"value":"RAIN","datetime":"2018-08-12 14:00"},{"value":"RAIN","datetime":"2018-08-12 15:00"},{"value":"RAIN","datetime":"2018-08-12 16:00"}],"cloudrate":[{"value":0.3,"datetime":"2018-08-10 17:00"},{"value":0.3,"datetime":"2018-08-10 18:00"},{"value":1,"datetime":"2018-08-10 19:00"},{"value":1,"datetime":"2018-08-10 20:00"},{"value":1,"datetime":"2018-08-10 21:00"},{"value":1,"datetime":"2018-08-10 22:00"},{"value":1,"datetime":"2018-08-10 23:00"},{"value":1,"datetime":"2018-08-11 00:00"},{"value":1,"datetime":"2018-08-11 01:00"},{"value":1,"datetime":"2018-08-11 02:00"},{"value":1,"datetime":"2018-08-11 03:00"},{"value":1,"datetime":"2018-08-11 04:00"},{"value":1,"datetime":"2018-08-11 05:00"},{"value":1,"datetime":"2018-08-11 06:00"},{"value":1,"datetime":"2018-08-11 07:00"},{"value":1,"datetime":"2018-08-11 08:00"},{"value":1,"datetime":"2018-08-11 09:00"},{"value":1,"datetime":"2018-08-11 10:00"},{"value":1,"datetime":"2018-08-11 11:00"},{"value":1,"datetime":"2018-08-11 12:00"},{"value":1,"datetime":"2018-08-11 13:00"},{"value":1,"datetime":"2018-08-11 14:00"},{"value":1,"datetime":"2018-08-11 15:00"},{"value":1,"datetime":"2018-08-11 16:00"},{"value":1,"datetime":"2018-08-11 17:00"},{"value":1,"datetime":"2018-08-11 18:00"},{"value":1,"datetime":"2018-08-11 19:00"},{"value":1,"datetime":"2018-08-11 20:00"},{"value":1,"datetime":"2018-08-11 21:00"},{"value":1,"datetime":"2018-08-11 22:00"},{"value":1,"datetime":"2018-08-11 23:00"},{"value":1,"datetime":"2018-08-12 00:00"},{"value":1,"datetime":"2018-08-12 01:00"},{"value":1,"datetime":"2018-08-12 02:00"},{"value":1,"datetime":"2018-08-12 03:00"},{"value":1,"datetime":"2018-08-12 04:00"},{"value":1,"datetime":"2018-08-12 05:00"},{"value":1,"datetime":"2018-08-12 06:00"},{"value":1,"datetime":"2018-08-12 07:00"},{"value":1,"datetime":"2018-08-12 08:00"},{"value":1,"datetime":"2018-08-12 09:00"},{"value":1,"datetime":"2018-08-12 10:00"},{"value":1,"datetime":"2018-08-12 11:00"},{"value":1,"datetime":"2018-08-12 12:00"},{"value":1,"datetime":"2018-08-12 13:00"},{"value":1,"datetime":"2018-08-12 14:00"},{"value":0.99,"datetime":"2018-08-12 15:00"},{"value":0.96,"datetime":"2018-08-12 16:00"}],"aqi":[{"value":62,"datetime":"2018-08-10 17:00"},{"value":35,"datetime":"2018-08-10 18:00"},{"value":34,"datetime":"2018-08-10 19:00"},{"value":31,"datetime":"2018-08-10 20:00"},{"value":30,"datetime":"2018-08-10 21:00"},{"value":30,"datetime":"2018-08-10 22:00"},{"value":30,"datetime":"2018-08-10 23:00"},{"value":33,"datetime":"2018-08-11 00:00"},{"value":34,"datetime":"2018-08-11 01:00"},{"value":35,"datetime":"2018-08-11 02:00"},{"value":34,"datetime":"2018-08-11 03:00"},{"value":31,"datetime":"2018-08-11 04:00"},{"value":27,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":24,"datetime":"2018-08-11 07:00"},{"value":24,"datetime":"2018-08-11 08:00"},{"value":24,"datetime":"2018-08-11 09:00"},{"value":24,"datetime":"2018-08-11 10:00"},{"value":24,"datetime":"2018-08-11 11:00"},{"value":23,"datetime":"2018-08-11 12:00"},{"value":22,"datetime":"2018-08-11 13:00"},{"value":22,"datetime":"2018-08-11 14:00"},{"value":22,"datetime":"2018-08-11 15:00"},{"value":23,"datetime":"2018-08-11 16:00"},{"value":23,"datetime":"2018-08-11 17:00"},{"value":23,"datetime":"2018-08-11 18:00"},{"value":22,"datetime":"2018-08-11 19:00"},{"value":22,"datetime":"2018-08-11 20:00"},{"value":22,"datetime":"2018-08-11 21:00"},{"value":23,"datetime":"2018-08-11 22:00"},{"value":23,"datetime":"2018-08-11 23:00"},{"value":22,"datetime":"2018-08-12 00:00"},{"value":20,"datetime":"2018-08-12 01:00"},{"value":18,"datetime":"2018-08-12 02:00"},{"value":14,"datetime":"2018-08-12 03:00"},{"value":11,"datetime":"2018-08-12 04:00"},{"value":8,"datetime":"2018-08-12 05:00"},{"value":8,"datetime":"2018-08-12 06:00"},{"value":8,"datetime":"2018-08-12 07:00"},{"value":8,"datetime":"2018-08-12 08:00"},{"value":8,"datetime":"2018-08-12 09:00"},{"value":8,"datetime":"2018-08-12 10:00"},{"value":8,"datetime":"2018-08-12 11:00"},{"value":8,"datetime":"2018-08-12 12:00"},{"value":10,"datetime":"2018-08-12 13:00"},{"value":11,"datetime":"2018-08-12 14:00"},{"value":11,"datetime":"2018-08-12 15:00"},{"value":11,"datetime":"2018-08-12 16:00"}],"humidity":[{"value":0.61,"datetime":"2018-08-10 17:00"},{"value":0.69,"datetime":"2018-08-10 18:00"},{"value":0.75,"datetime":"2018-08-10 19:00"},{"value":0.78,"datetime":"2018-08-10 20:00"},{"value":0.8,"datetime":"2018-08-10 21:00"},{"value":0.81,"datetime":"2018-08-10 22:00"},{"value":0.81,"datetime":"2018-08-10 23:00"},{"value":0.81,"datetime":"2018-08-11 00:00"},{"value":0.82,"datetime":"2018-08-11 01:00"},{"value":0.82,"datetime":"2018-08-11 02:00"},{"value":0.83,"datetime":"2018-08-11 03:00"},{"value":0.83,"datetime":"2018-08-11 04:00"},{"value":0.83,"datetime":"2018-08-11 05:00"},{"value":0.84,"datetime":"2018-08-11 06:00"},{"value":0.84,"datetime":"2018-08-11 07:00"},{"value":0.83,"datetime":"2018-08-11 08:00"},{"value":0.78,"datetime":"2018-08-11 09:00"},{"value":0.73,"datetime":"2018-08-11 10:00"},{"value":0.67,"datetime":"2018-08-11 11:00"},{"value":0.62,"datetime":"2018-08-11 12:00"},{"value":0.59,"datetime":"2018-08-11 13:00"},{"value":0.57,"datetime":"2018-08-11 14:00"},{"value":0.58,"datetime":"2018-08-11 15:00"},{"value":0.6,"datetime":"2018-08-11 16:00"},{"value":0.64,"datetime":"2018-08-11 17:00"},{"value":0.7,"datetime":"2018-08-11 18:00"},{"value":0.77,"datetime":"2018-08-11 19:00"},{"value":0.82,"datetime":"2018-08-11 20:00"},{"value":0.83,"datetime":"2018-08-11 21:00"},{"value":0.83,"datetime":"2018-08-11 22:00"},{"value":0.81,"datetime":"2018-08-11 23:00"},{"value":0.81,"datetime":"2018-08-12 00:00"},{"value":0.81,"datetime":"2018-08-12 01:00"},{"value":0.82,"datetime":"2018-08-12 02:00"},{"value":0.83,"datetime":"2018-08-12 03:00"},{"value":0.85,"datetime":"2018-08-12 04:00"},{"value":0.85,"datetime":"2018-08-12 05:00"},{"value":0.85,"datetime":"2018-08-12 06:00"},{"value":0.85,"datetime":"2018-08-12 07:00"},{"value":0.85,"datetime":"2018-08-12 08:00"},{"value":0.87,"datetime":"2018-08-12 09:00"},{"value":0.88,"datetime":"2018-08-12 10:00"},{"value":0.86,"datetime":"2018-08-12 11:00"},{"value":0.78,"datetime":"2018-08-12 12:00"},{"value":0.69,"datetime":"2018-08-12 13:00"},{"value":0.6,"datetime":"2018-08-12 14:00"},{"value":0.56,"datetime":"2018-08-12 15:00"},{"value":0.55,"datetime":"2018-08-12 16:00"}],"pres":[{"value":99523.6446458357,"datetime":"2018-08-10 17:00"},{"value":99515.5613853441,"datetime":"2018-08-10 18:00"},{"value":99518.171166162,"datetime":"2018-08-10 19:00"},{"value":99541.0013277177,"datetime":"2018-08-10 20:00"},{"value":99587.563853488,"datetime":"2018-08-10 21:00"},{"value":99637.3093031439,"datetime":"2018-08-10 22:00"},{"value":99663.6728804052,"datetime":"2018-08-10 23:00"},{"value":99648.2618189219,"datetime":"2018-08-11 00:00"},{"value":99605.3714720644,"datetime":"2018-08-11 01:00"},{"value":99557.4692231338,"datetime":"2018-08-11 02:00"},{"value":99522.8979679377,"datetime":"2018-08-11 03:00"},{"value":99503.5026523102,"datetime":"2018-08-11 04:00"},{"value":99497.0037345927,"datetime":"2018-08-11 05:00"},{"value":99501.2759577473,"datetime":"2018-08-11 06:00"},{"value":99514.8112032209,"datetime":"2018-08-11 07:00"},{"value":99536.2556370818,"datetime":"2018-08-11 08:00"},{"value":99562.2975528794,"datetime":"2018-08-11 09:00"},{"value":99581.7937540879,"datetime":"2018-08-11 10:00"},{"value":99581.6431716626,"datetime":"2018-08-11 11:00"},{"value":99552.1690890055,"datetime":"2018-08-11 12:00"},{"value":99497.3921993042,"datetime":"2018-08-11 13:00"},{"value":99424.7575481928,"datetime":"2018-08-11 14:00"},{"value":99343.9995065755,"datetime":"2018-08-11 15:00"},{"value":99274.0097464358,"datetime":"2018-08-11 16:00"},{"value":99235.9692650271,"datetime":"2018-08-11 17:00"},{"value":99245.3907760485,"datetime":"2018-08-11 18:00"},{"value":99295.1138589811,"datetime":"2018-08-11 19:00"},{"value":99372.3098097514,"datetime":"2018-08-11 20:00"},{"value":99461.3258485006,"datetime":"2018-08-11 21:00"},{"value":99535.2128922272,"datetime":"2018-08-11 22:00"},{"value":99564.1977821443,"datetime":"2018-08-11 23:00"},{"value":99529.3974878087,"datetime":"2018-08-12 00:00"},{"value":99455.4894921521,"datetime":"2018-08-12 01:00"},{"value":99378.0414064498,"datetime":"2018-08-12 02:00"},{"value":99326.3932346277,"datetime":"2018-08-12 03:00"},{"value":99304.9745512121,"datetime":"2018-08-12 04:00"},{"value":99311.9873233799,"datetime":"2018-08-12 05:00"},{"value":99344.3398966088,"datetime":"2018-08-12 06:00"},{"value":99393.7661295793,"datetime":"2018-08-12 07:00"},{"value":99450.7062592728,"datetime":"2018-08-12 08:00"},{"value":99504.234777582,"datetime":"2018-08-12 09:00"},{"value":99537.9631960439,"datetime":"2018-08-12 10:00"},{"value":99534.1372811071,"datetime":"2018-08-12 11:00"},{"value":99482.6025485121,"datetime":"2018-08-12 12:00"},{"value":99403.6035111682,"datetime":"2018-08-12 13:00"},{"value":99324.9844312771,"datetime":"2018-08-12 14:00"},{"value":99269.2934570245,"datetime":"2018-08-12 15:00"},{"value":99237.894280532,"datetime":"2018-08-12 16:00"}],"pm25":[{"value":25,"datetime":"2018-08-10 17:00"},{"value":24,"datetime":"2018-08-10 18:00"},{"value":23,"datetime":"2018-08-10 19:00"},{"value":21,"datetime":"2018-08-10 20:00"},{"value":20,"datetime":"2018-08-10 21:00"},{"value":20,"datetime":"2018-08-10 22:00"},{"value":20,"datetime":"2018-08-10 23:00"},{"value":22,"datetime":"2018-08-11 00:00"},{"value":23,"datetime":"2018-08-11 01:00"},{"value":24,"datetime":"2018-08-11 02:00"},{"value":23,"datetime":"2018-08-11 03:00"},{"value":21,"datetime":"2018-08-11 04:00"},{"value":18,"datetime":"2018-08-11 05:00"},{"value":17,"datetime":"2018-08-11 06:00"},{"value":16,"datetime":"2018-08-11 07:00"},{"value":16,"datetime":"2018-08-11 08:00"},{"value":16,"datetime":"2018-08-11 09:00"},{"value":16,"datetime":"2018-08-11 10:00"},{"value":16,"datetime":"2018-08-11 11:00"},{"value":15,"datetime":"2018-08-11 12:00"},{"value":14,"datetime":"2018-08-11 13:00"},{"value":14,"datetime":"2018-08-11 14:00"},{"value":14,"datetime":"2018-08-11 15:00"},{"value":15,"datetime":"2018-08-11 16:00"},{"value":15,"datetime":"2018-08-11 17:00"},{"value":15,"datetime":"2018-08-11 18:00"},{"value":14,"datetime":"2018-08-11 19:00"},{"value":14,"datetime":"2018-08-11 20:00"},{"value":14,"datetime":"2018-08-11 21:00"},{"value":15,"datetime":"2018-08-11 22:00"},{"value":15,"datetime":"2018-08-11 23:00"},{"value":14,"datetime":"2018-08-12 00:00"},{"value":13,"datetime":"2018-08-12 01:00"},{"value":11,"datetime":"2018-08-12 02:00"},{"value":8,"datetime":"2018-08-12 03:00"},{"value":6,"datetime":"2018-08-12 04:00"},{"value":4,"datetime":"2018-08-12 05:00"},{"value":4,"datetime":"2018-08-12 06:00"},{"value":4,"datetime":"2018-08-12 07:00"},{"value":4,"datetime":"2018-08-12 08:00"},{"value":4,"datetime":"2018-08-12 09:00"},{"value":4,"datetime":"2018-08-12 10:00"},{"value":4,"datetime":"2018-08-12 11:00"},{"value":4,"datetime":"2018-08-12 12:00"},{"value":5,"datetime":"2018-08-12 13:00"},{"value":6,"datetime":"2018-08-12 14:00"},{"value":6,"datetime":"2018-08-12 15:00"},{"value":6,"datetime":"2018-08-12 16:00"}],"precipitation":[{"value":0,"datetime":"2018-08-10 17:00"},{"value":0,"datetime":"2018-08-10 18:00"},{"value":1.1824,"datetime":"2018-08-10 19:00"},{"value":1.0751,"datetime":"2018-08-10 20:00"},{"value":0.6826,"datetime":"2018-08-10 21:00"},{"value":0.2864,"datetime":"2018-08-10 22:00"},{"value":0,"datetime":"2018-08-10 23:00"},{"value":0,"datetime":"2018-08-11 00:00"},{"value":0,"datetime":"2018-08-11 01:00"},{"value":0,"datetime":"2018-08-11 02:00"},{"value":0,"datetime":"2018-08-11 03:00"},{"value":0,"datetime":"2018-08-11 04:00"},{"value":0,"datetime":"2018-08-11 05:00"},{"value":0,"datetime":"2018-08-11 06:00"},{"value":0,"datetime":"2018-08-11 07:00"},{"value":0,"datetime":"2018-08-11 08:00"},{"value":0,"datetime":"2018-08-11 09:00"},{"value":0,"datetime":"2018-08-11 10:00"},{"value":0,"datetime":"2018-08-11 11:00"},{"value":0.0438,"datetime":"2018-08-11 12:00"},{"value":0.0596,"datetime":"2018-08-11 13:00"},{"value":0.0652,"datetime":"2018-08-11 14:00"},{"value":0.068,"datetime":"2018-08-11 15:00"},{"value":0.139,"datetime":"2018-08-11 16:00"},{"value":0.3656,"datetime":"2018-08-11 17:00"},{"value":0.7871,"datetime":"2018-08-11 18:00"},{"value":1.2511,"datetime":"2018-08-11 19:00"},{"value":1.5575,"datetime":"2018-08-11 20:00"},{"value":1.564,"datetime":"2018-08-11 21:00"},{"value":1.359,"datetime":"2018-08-11 22:00"},{"value":1.0889,"datetime":"2018-08-11 23:00"},{"value":0.8701,"datetime":"2018-08-12 00:00"},{"value":0.7005,"datetime":"2018-08-12 01:00"},{"value":0.5481,"datetime":"2018-08-12 02:00"},{"value":0.3893,"datetime":"2018-08-12 03:00"},{"value":0.234,"datetime":"2018-08-12 04:00"},{"value":0.1004,"datetime":"2018-08-12 05:00"},{"value":0,"datetime":"2018-08-12 06:00"},{"value":0,"datetime":"2018-08-12 07:00"},{"value":0.2374,"datetime":"2018-08-12 08:00"},{"value":0.6365,"datetime":"2018-08-12 09:00"},{"value":1.0779,"datetime":"2018-08-12 10:00"},{"value":1.3592,"datetime":"2018-08-12 11:00"},{"value":1.3344,"datetime":"2018-08-12 12:00"},{"value":1.0837,"datetime":"2018-08-12 13:00"},{"value":0.7435,"datetime":"2018-08-12 14:00"},{"value":0.4301,"datetime":"2018-08-12 15:00"},{"value":0.1777,"datetime":"2018-08-12 16:00"}],"wind":[{"direction":35.34,"speed":19.85,"datetime":"2018-08-10 17:00"},{"direction":37.67,"speed":15.49,"datetime":"2018-08-10 18:00"},{"direction":35.96,"speed":10.52,"datetime":"2018-08-10 19:00"},{"direction":27.57,"speed":6.89,"datetime":"2018-08-10 20:00"},{"direction":16.21,"speed":6.08,"datetime":"2018-08-10 21:00"},{"direction":10.18,"speed":7.03,"datetime":"2018-08-10 22:00"},{"direction":6.71,"speed":8.24,"datetime":"2018-08-10 23:00"},{"direction":1.34,"speed":8.75,"datetime":"2018-08-11 00:00"},{"direction":354.29,"speed":8.83,"datetime":"2018-08-11 01:00"},{"direction":347.6,"speed":8.98,"datetime":"2018-08-11 02:00"},{"direction":343.39,"speed":9.43,"datetime":"2018-08-11 03:00"},{"direction":340.44,"speed":10.09,"datetime":"2018-08-11 04:00"},{"direction":336.76,"speed":10.86,"datetime":"2018-08-11 05:00"},{"direction":332,"speed":11.76,"datetime":"2018-08-11 06:00"},{"direction":330.08,"speed":12.75,"datetime":"2018-08-11 07:00"},{"direction":334.74,"speed":13.74,"datetime":"2018-08-11 08:00"},{"direction":346.35,"speed":15.17,"datetime":"2018-08-11 09:00"},{"direction":356.76,"speed":17.39,"datetime":"2018-08-11 10:00"},{"direction":359.92,"speed":19.46,"datetime":"2018-08-11 11:00"},{"direction":354.47,"speed":20.49,"datetime":"2018-08-11 12:00"},{"direction":345.37,"speed":21.01,"datetime":"2018-08-11 13:00"},{"direction":338.31,"speed":21.14,"datetime":"2018-08-11 14:00"},{"direction":337.19,"speed":20.15,"datetime":"2018-08-11 15:00"},{"direction":341,"speed":17.76,"datetime":"2018-08-11 16:00"},{"direction":348.83,"speed":14.08,"datetime":"2018-08-11 17:00"},{"direction":2.14,"speed":9.53,"datetime":"2018-08-11 18:00"},{"direction":26.44,"speed":5.75,"datetime":"2018-08-11 19:00"},{"direction":52.75,"speed":3.77,"datetime":"2018-08-11 20:00"},{"direction":30.05,"speed":2.3,"datetime":"2018-08-11 21:00"},{"direction":328.54,"speed":3.88,"datetime":"2018-08-11 22:00"},{"direction":312.25,"speed":7.04,"datetime":"2018-08-11 23:00"},{"direction":305.17,"speed":8.98,"datetime":"2018-08-12 00:00"},{"direction":301.37,"speed":9.77,"datetime":"2018-08-12 01:00"},{"direction":302.39,"speed":9.97,"datetime":"2018-08-12 02:00"},{"direction":310.16,"speed":10.21,"datetime":"2018-08-12 03:00"},{"direction":321.45,"speed":11.04,"datetime":"2018-08-12 04:00"},{"direction":331.27,"speed":12.65,"datetime":"2018-08-12 05:00"},{"direction":337.24,"speed":14.69,"datetime":"2018-08-12 06:00"},{"direction":340.86,"speed":16.39,"datetime":"2018-08-12 07:00"},{"direction":344.02,"speed":17.03,"datetime":"2018-08-12 08:00"},{"direction":348.08,"speed":16.3,"datetime":"2018-08-12 09:00"},{"direction":352.68,"speed":15.16,"datetime":"2018-08-12 10:00"},{"direction":355.72,"speed":14.73,"datetime":"2018-08-12 11:00"},{"direction":355.58,"speed":15.69,"datetime":"2018-08-12 12:00"},{"direction":356.48,"speed":17.24,"datetime":"2018-08-12 13:00"},{"direction":2.95,"speed":18.34,"datetime":"2018-08-12 14:00"},{"direction":17.2,"speed":18.99,"datetime":"2018-08-12 15:00"},{"direction":32.62,"speed":19.9,"datetime":"2018-08-12 16:00"}],"temperature":[{"value":34,"datetime":"2018-08-10 17:00"},{"value":31.83,"datetime":"2018-08-10 18:00"},{"value":30.67,"datetime":"2018-08-10 19:00"},{"value":29.2,"datetime":"2018-08-10 20:00"},{"value":28.73,"datetime":"2018-08-10 21:00"},{"value":28.27,"datetime":"2018-08-10 22:00"},{"value":27.2,"datetime":"2018-08-10 23:00"},{"value":27,"datetime":"2018-08-11 00:00"},{"value":26.8,"datetime":"2018-08-11 01:00"},{"value":26.3,"datetime":"2018-08-11 02:00"},{"value":26.2,"datetime":"2018-08-11 03:00"},{"value":26.1,"datetime":"2018-08-11 04:00"},{"value":26,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":26.5,"datetime":"2018-08-11 07:00"},{"value":27.1,"datetime":"2018-08-11 08:00"},{"value":27.7,"datetime":"2018-08-11 09:00"},{"value":28.3,"datetime":"2018-08-11 10:00"},{"value":29,"datetime":"2018-08-11 11:00"},{"value":29.5,"datetime":"2018-08-11 12:00"},{"value":30,"datetime":"2018-08-11 13:00"},{"value":30.5,"datetime":"2018-08-11 14:00"},{"value":31,"datetime":"2018-08-11 15:00"},{"value":31,"datetime":"2018-08-11 16:00"},{"value":31,"datetime":"2018-08-11 17:00"},{"value":31,"datetime":"2018-08-11 18:00"},{"value":30.61,"datetime":"2018-08-11 19:00"},{"value":30.01,"datetime":"2018-08-11 20:00"},{"value":29.47,"datetime":"2018-08-11 21:00"},{"value":29.07,"datetime":"2018-08-11 22:00"},{"value":28.76,"datetime":"2018-08-11 23:00"},{"value":28.39,"datetime":"2018-08-12 00:00"},{"value":27.7,"datetime":"2018-08-12 01:00"},{"value":26.97,"datetime":"2018-08-12 02:00"},{"value":26.54,"datetime":"2018-08-12 03:00"},{"value":26.21,"datetime":"2018-08-12 04:00"},{"value":26.05,"datetime":"2018-08-12 05:00"},{"value":26.53,"datetime":"2018-08-12 06:00"},{"value":27.01,"datetime":"2018-08-12 07:00"},{"value":27.5,"datetime":"2018-08-12 08:00"},{"value":28.18,"datetime":"2018-08-12 09:00"},{"value":26,"datetime":"2018-08-12 10:00"},{"value":29.26,"datetime":"2018-08-12 11:00"},{"value":28.92,"datetime":"2018-08-12 12:00"},{"value":28.29,"datetime":"2018-08-12 13:00"},{"value":27.81,"datetime":"2018-08-12 14:00"},{"value":30.9,"datetime":"2018-08-12 15:00"},{"value":28.91,"datetime":"2018-08-12 16:00"}]},"forecast_keypoint":"附近正在下雨，出门还是带把伞吧~","primary":0,"daily":{"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2018-08-10"},{"index":"3","desc":"易发","datetime":"2018-08-11"},{"index":"3","desc":"易发","datetime":"2018-08-12"},{"index":"3","desc":"易发","datetime":"2018-08-13"},{"index":"3","desc":"易发","datetime":"2018-08-14"}],"skycon_20h_32h":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}],"temperature":[{"date":"2018-08-10","max":34,"avg":29.99,"min":26},{"date":"2018-08-11","max":31,"avg":28.54,"min":26},{"date":"2018-08-12","max":30.9,"avg":28.29,"min":26},{"date":"2018-08-13","max":30,"avg":27,"min":26},{"date":"2018-08-14","max":32,"avg":29.18,"min":27}],"skycon":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}],"cloudrate":[{"date":"2018-08-10","max":1,"avg":0.8,"min":0.3},{"date":"2018-08-11","max":1,"avg":1,"min":1},{"date":"2018-08-12","max":1,"avg":0.96,"min":0.78},{"date":"2018-08-13","max":1,"avg":1,"min":0.99},{"date":"2018-08-14","max":1,"avg":0.98,"min":0.89}],"aqi":[{"date":"2018-08-10","max":70,"avg":36,"min":30},{"date":"2018-08-11","max":35,"avg":25.42,"min":22},{"date":"2018-08-12","max":30,"avg":13.12,"min":8},{"date":"2018-08-13","max":39,"avg":21.25,"min":7},{"date":"2018-08-14","max":14,"avg":7.5,"min":7}],"comfort":[{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"3","desc":"热","datetime":"2018-08-11"},{"index":"0","desc":"闷热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"0","desc":"闷热","datetime":"2018-08-14"}],"humidity":[{"date":"2018-08-10","max":0.81,"avg":0.75,"min":0.52},{"date":"2018-08-11","max":0.84,"avg":0.75,"min":0.57},{"date":"2018-08-12","max":0.88,"avg":0.76,"min":0.55},{"date":"2018-08-13","max":0.93,"avg":0.9,"min":0.81},{"date":"2018-08-14","max":0.95,"avg":0.88,"min":0.79}],"astro":[{"date":"2018-08-10","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-11","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-12","sunset":{"time":"19:16"},"sunrise":{"time":"06:20"}},{"date":"2018-08-13","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}},{"date":"2018-08-14","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}}],"pres":[{"date":"2018-08-10","max":99899.66,"avg":99569.56,"min":99515.56},{"date":"2018-08-11","max":99648.26,"avg":99475.6,"min":99235.97},{"date":"2018-08-12","max":99537.96,"avg":99379.13,"min":99226.85},{"date":"2018-08-13","max":99417.3,"avg":99262.42,"min":99118.35},{"date":"2018-08-14","max":99661.08,"avg":99293.56,"min":99131.19}],"ultraviolet":[{"index":"1","desc":"最弱","datetime":"2018-08-10"},{"index":"1","desc":"最弱","datetime":"2018-08-11"},{"index":"1","desc":"最弱","datetime":"2018-08-12"},{"index":"1","desc":"最弱","datetime":"2018-08-13"},{"index":"1","desc":"最弱","datetime":"2018-08-14"}],"pm25":[{"date":"2018-08-10","max":50,"avg":21.86,"min":20},{"date":"2018-08-11","max":24,"avg":16.75,"min":14},{"date":"2018-08-12","max":20,"avg":7.67,"min":4},{"date":"2018-08-13","max":27,"avg":13.62,"min":3},{"date":"2018-08-14","max":8,"avg":3.38,"min":3}],"dressing":[{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"2","desc":"很热","datetime":"2018-08-11"},{"index":"2","desc":"很热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"3","desc":"热","datetime":"2018-08-14"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2018-08-10"},{"index":"3","desc":"较不适宜","datetime":"2018-08-11"},{"index":"3","desc":"较不适宜","datetime":"2018-08-12"},{"index":"3","desc":"较不适宜","datetime":"2018-08-13"},{"index":"3","desc":"较不适宜","datetime":"2018-08-14"}],"precipitation":[{"date":"2018-08-10","max":1.1824,"avg":0.4609,"min":0},{"date":"2018-08-11","max":1.564,"avg":0.3479,"min":0},{"date":"2018-08-12","max":1.3592,"avg":0.4445,"min":0},{"date":"2018-08-13","max":12.645,"avg":4.3305,"min":0},{"date":"2018-08-14","max":1.4058,"avg":0.707,"min":0.1332}],"wind":[{"date":"2018-08-10","max":{"direction":24.76,"speed":23.94},"avg":{"direction":18.31,"speed":15.26},"min":{"direction":16.21,"speed":6.08}},{"date":"2018-08-11","max":{"direction":338.31,"speed":21.14},"avg":{"direction":346.42,"speed":12.25},"min":{"direction":30.05,"speed":2.3}},{"date":"2018-08-12","max":{"direction":32.62,"speed":19.9},"avg":{"direction":349.15,"speed":13.75},"min":{"direction":356.35,"speed":5.96}},{"date":"2018-08-13","max":{"direction":286.23,"speed":27.1},"avg":{"direction":282.74,"speed":22.19},"min":{"direction":264.69,"speed":14.08}},{"date":"2018-08-14","max":{"direction":176.49,"speed":28.95},"avg":{"direction":176.1,"speed":17.31},"min":{"direction":234.32,"speed":1.85}}],"skycon_08h_20h":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}]},"minutely":{"status":"ok","description":"附近正在下雨，出门还是带把伞吧~","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}}
     * server_time : 1533892083
     * api_status : active
     * tzshift : 28800
     * api_version : v2.2
     * unit : metric
     * location : [21.675134,109.199098]
     */

    private String status;
    private String lang;
    private ResultBean result;
    private double server_time;
    private String api_status;
    private double tzshift;
    private String api_version;
    private String unit;
    private List<Double> location;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public double getServer_time() {
        return server_time;
    }

    public void setServer_time(double server_time) {
        this.server_time = server_time;
    }

    public String getApi_status() {
        return api_status;
    }

    public void setApi_status(String api_status) {
        this.api_status = api_status;
    }

    public double getTzshift() {
        return tzshift;
    }

    public void setTzshift(double tzshift) {
        this.tzshift = tzshift;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public static class ResultBean {
        /**
         * hourly : {"status":"ok","description":"多云，今天傍晚19点钟后转小雨","skycon":[{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 18:00"},{"value":"RAIN","datetime":"2018-08-10 19:00"},{"value":"RAIN","datetime":"2018-08-10 20:00"},{"value":"RAIN","datetime":"2018-08-10 21:00"},{"value":"RAIN","datetime":"2018-08-10 22:00"},{"value":"CLOUDY","datetime":"2018-08-10 23:00"},{"value":"CLOUDY","datetime":"2018-08-11 00:00"},{"value":"CLOUDY","datetime":"2018-08-11 01:00"},{"value":"CLOUDY","datetime":"2018-08-11 02:00"},{"value":"CLOUDY","datetime":"2018-08-11 03:00"},{"value":"CLOUDY","datetime":"2018-08-11 04:00"},{"value":"CLOUDY","datetime":"2018-08-11 05:00"},{"value":"CLOUDY","datetime":"2018-08-11 06:00"},{"value":"CLOUDY","datetime":"2018-08-11 07:00"},{"value":"CLOUDY","datetime":"2018-08-11 08:00"},{"value":"CLOUDY","datetime":"2018-08-11 09:00"},{"value":"CLOUDY","datetime":"2018-08-11 10:00"},{"value":"CLOUDY","datetime":"2018-08-11 11:00"},{"value":"CLOUDY","datetime":"2018-08-11 12:00"},{"value":"CLOUDY","datetime":"2018-08-11 13:00"},{"value":"RAIN","datetime":"2018-08-11 14:00"},{"value":"RAIN","datetime":"2018-08-11 15:00"},{"value":"RAIN","datetime":"2018-08-11 16:00"},{"value":"RAIN","datetime":"2018-08-11 17:00"},{"value":"RAIN","datetime":"2018-08-11 18:00"},{"value":"RAIN","datetime":"2018-08-11 19:00"},{"value":"RAIN","datetime":"2018-08-11 20:00"},{"value":"RAIN","datetime":"2018-08-11 21:00"},{"value":"RAIN","datetime":"2018-08-11 22:00"},{"value":"RAIN","datetime":"2018-08-11 23:00"},{"value":"RAIN","datetime":"2018-08-12 00:00"},{"value":"RAIN","datetime":"2018-08-12 01:00"},{"value":"RAIN","datetime":"2018-08-12 02:00"},{"value":"RAIN","datetime":"2018-08-12 03:00"},{"value":"RAIN","datetime":"2018-08-12 04:00"},{"value":"RAIN","datetime":"2018-08-12 05:00"},{"value":"CLOUDY","datetime":"2018-08-12 06:00"},{"value":"CLOUDY","datetime":"2018-08-12 07:00"},{"value":"RAIN","datetime":"2018-08-12 08:00"},{"value":"RAIN","datetime":"2018-08-12 09:00"},{"value":"RAIN","datetime":"2018-08-12 10:00"},{"value":"RAIN","datetime":"2018-08-12 11:00"},{"value":"RAIN","datetime":"2018-08-12 12:00"},{"value":"RAIN","datetime":"2018-08-12 13:00"},{"value":"RAIN","datetime":"2018-08-12 14:00"},{"value":"RAIN","datetime":"2018-08-12 15:00"},{"value":"RAIN","datetime":"2018-08-12 16:00"}],"cloudrate":[{"value":0.3,"datetime":"2018-08-10 17:00"},{"value":0.3,"datetime":"2018-08-10 18:00"},{"value":1,"datetime":"2018-08-10 19:00"},{"value":1,"datetime":"2018-08-10 20:00"},{"value":1,"datetime":"2018-08-10 21:00"},{"value":1,"datetime":"2018-08-10 22:00"},{"value":1,"datetime":"2018-08-10 23:00"},{"value":1,"datetime":"2018-08-11 00:00"},{"value":1,"datetime":"2018-08-11 01:00"},{"value":1,"datetime":"2018-08-11 02:00"},{"value":1,"datetime":"2018-08-11 03:00"},{"value":1,"datetime":"2018-08-11 04:00"},{"value":1,"datetime":"2018-08-11 05:00"},{"value":1,"datetime":"2018-08-11 06:00"},{"value":1,"datetime":"2018-08-11 07:00"},{"value":1,"datetime":"2018-08-11 08:00"},{"value":1,"datetime":"2018-08-11 09:00"},{"value":1,"datetime":"2018-08-11 10:00"},{"value":1,"datetime":"2018-08-11 11:00"},{"value":1,"datetime":"2018-08-11 12:00"},{"value":1,"datetime":"2018-08-11 13:00"},{"value":1,"datetime":"2018-08-11 14:00"},{"value":1,"datetime":"2018-08-11 15:00"},{"value":1,"datetime":"2018-08-11 16:00"},{"value":1,"datetime":"2018-08-11 17:00"},{"value":1,"datetime":"2018-08-11 18:00"},{"value":1,"datetime":"2018-08-11 19:00"},{"value":1,"datetime":"2018-08-11 20:00"},{"value":1,"datetime":"2018-08-11 21:00"},{"value":1,"datetime":"2018-08-11 22:00"},{"value":1,"datetime":"2018-08-11 23:00"},{"value":1,"datetime":"2018-08-12 00:00"},{"value":1,"datetime":"2018-08-12 01:00"},{"value":1,"datetime":"2018-08-12 02:00"},{"value":1,"datetime":"2018-08-12 03:00"},{"value":1,"datetime":"2018-08-12 04:00"},{"value":1,"datetime":"2018-08-12 05:00"},{"value":1,"datetime":"2018-08-12 06:00"},{"value":1,"datetime":"2018-08-12 07:00"},{"value":1,"datetime":"2018-08-12 08:00"},{"value":1,"datetime":"2018-08-12 09:00"},{"value":1,"datetime":"2018-08-12 10:00"},{"value":1,"datetime":"2018-08-12 11:00"},{"value":1,"datetime":"2018-08-12 12:00"},{"value":1,"datetime":"2018-08-12 13:00"},{"value":1,"datetime":"2018-08-12 14:00"},{"value":0.99,"datetime":"2018-08-12 15:00"},{"value":0.96,"datetime":"2018-08-12 16:00"}],"aqi":[{"value":62,"datetime":"2018-08-10 17:00"},{"value":35,"datetime":"2018-08-10 18:00"},{"value":34,"datetime":"2018-08-10 19:00"},{"value":31,"datetime":"2018-08-10 20:00"},{"value":30,"datetime":"2018-08-10 21:00"},{"value":30,"datetime":"2018-08-10 22:00"},{"value":30,"datetime":"2018-08-10 23:00"},{"value":33,"datetime":"2018-08-11 00:00"},{"value":34,"datetime":"2018-08-11 01:00"},{"value":35,"datetime":"2018-08-11 02:00"},{"value":34,"datetime":"2018-08-11 03:00"},{"value":31,"datetime":"2018-08-11 04:00"},{"value":27,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":24,"datetime":"2018-08-11 07:00"},{"value":24,"datetime":"2018-08-11 08:00"},{"value":24,"datetime":"2018-08-11 09:00"},{"value":24,"datetime":"2018-08-11 10:00"},{"value":24,"datetime":"2018-08-11 11:00"},{"value":23,"datetime":"2018-08-11 12:00"},{"value":22,"datetime":"2018-08-11 13:00"},{"value":22,"datetime":"2018-08-11 14:00"},{"value":22,"datetime":"2018-08-11 15:00"},{"value":23,"datetime":"2018-08-11 16:00"},{"value":23,"datetime":"2018-08-11 17:00"},{"value":23,"datetime":"2018-08-11 18:00"},{"value":22,"datetime":"2018-08-11 19:00"},{"value":22,"datetime":"2018-08-11 20:00"},{"value":22,"datetime":"2018-08-11 21:00"},{"value":23,"datetime":"2018-08-11 22:00"},{"value":23,"datetime":"2018-08-11 23:00"},{"value":22,"datetime":"2018-08-12 00:00"},{"value":20,"datetime":"2018-08-12 01:00"},{"value":18,"datetime":"2018-08-12 02:00"},{"value":14,"datetime":"2018-08-12 03:00"},{"value":11,"datetime":"2018-08-12 04:00"},{"value":8,"datetime":"2018-08-12 05:00"},{"value":8,"datetime":"2018-08-12 06:00"},{"value":8,"datetime":"2018-08-12 07:00"},{"value":8,"datetime":"2018-08-12 08:00"},{"value":8,"datetime":"2018-08-12 09:00"},{"value":8,"datetime":"2018-08-12 10:00"},{"value":8,"datetime":"2018-08-12 11:00"},{"value":8,"datetime":"2018-08-12 12:00"},{"value":10,"datetime":"2018-08-12 13:00"},{"value":11,"datetime":"2018-08-12 14:00"},{"value":11,"datetime":"2018-08-12 15:00"},{"value":11,"datetime":"2018-08-12 16:00"}],"humidity":[{"value":0.61,"datetime":"2018-08-10 17:00"},{"value":0.69,"datetime":"2018-08-10 18:00"},{"value":0.75,"datetime":"2018-08-10 19:00"},{"value":0.78,"datetime":"2018-08-10 20:00"},{"value":0.8,"datetime":"2018-08-10 21:00"},{"value":0.81,"datetime":"2018-08-10 22:00"},{"value":0.81,"datetime":"2018-08-10 23:00"},{"value":0.81,"datetime":"2018-08-11 00:00"},{"value":0.82,"datetime":"2018-08-11 01:00"},{"value":0.82,"datetime":"2018-08-11 02:00"},{"value":0.83,"datetime":"2018-08-11 03:00"},{"value":0.83,"datetime":"2018-08-11 04:00"},{"value":0.83,"datetime":"2018-08-11 05:00"},{"value":0.84,"datetime":"2018-08-11 06:00"},{"value":0.84,"datetime":"2018-08-11 07:00"},{"value":0.83,"datetime":"2018-08-11 08:00"},{"value":0.78,"datetime":"2018-08-11 09:00"},{"value":0.73,"datetime":"2018-08-11 10:00"},{"value":0.67,"datetime":"2018-08-11 11:00"},{"value":0.62,"datetime":"2018-08-11 12:00"},{"value":0.59,"datetime":"2018-08-11 13:00"},{"value":0.57,"datetime":"2018-08-11 14:00"},{"value":0.58,"datetime":"2018-08-11 15:00"},{"value":0.6,"datetime":"2018-08-11 16:00"},{"value":0.64,"datetime":"2018-08-11 17:00"},{"value":0.7,"datetime":"2018-08-11 18:00"},{"value":0.77,"datetime":"2018-08-11 19:00"},{"value":0.82,"datetime":"2018-08-11 20:00"},{"value":0.83,"datetime":"2018-08-11 21:00"},{"value":0.83,"datetime":"2018-08-11 22:00"},{"value":0.81,"datetime":"2018-08-11 23:00"},{"value":0.81,"datetime":"2018-08-12 00:00"},{"value":0.81,"datetime":"2018-08-12 01:00"},{"value":0.82,"datetime":"2018-08-12 02:00"},{"value":0.83,"datetime":"2018-08-12 03:00"},{"value":0.85,"datetime":"2018-08-12 04:00"},{"value":0.85,"datetime":"2018-08-12 05:00"},{"value":0.85,"datetime":"2018-08-12 06:00"},{"value":0.85,"datetime":"2018-08-12 07:00"},{"value":0.85,"datetime":"2018-08-12 08:00"},{"value":0.87,"datetime":"2018-08-12 09:00"},{"value":0.88,"datetime":"2018-08-12 10:00"},{"value":0.86,"datetime":"2018-08-12 11:00"},{"value":0.78,"datetime":"2018-08-12 12:00"},{"value":0.69,"datetime":"2018-08-12 13:00"},{"value":0.6,"datetime":"2018-08-12 14:00"},{"value":0.56,"datetime":"2018-08-12 15:00"},{"value":0.55,"datetime":"2018-08-12 16:00"}],"pres":[{"value":99523.6446458357,"datetime":"2018-08-10 17:00"},{"value":99515.5613853441,"datetime":"2018-08-10 18:00"},{"value":99518.171166162,"datetime":"2018-08-10 19:00"},{"value":99541.0013277177,"datetime":"2018-08-10 20:00"},{"value":99587.563853488,"datetime":"2018-08-10 21:00"},{"value":99637.3093031439,"datetime":"2018-08-10 22:00"},{"value":99663.6728804052,"datetime":"2018-08-10 23:00"},{"value":99648.2618189219,"datetime":"2018-08-11 00:00"},{"value":99605.3714720644,"datetime":"2018-08-11 01:00"},{"value":99557.4692231338,"datetime":"2018-08-11 02:00"},{"value":99522.8979679377,"datetime":"2018-08-11 03:00"},{"value":99503.5026523102,"datetime":"2018-08-11 04:00"},{"value":99497.0037345927,"datetime":"2018-08-11 05:00"},{"value":99501.2759577473,"datetime":"2018-08-11 06:00"},{"value":99514.8112032209,"datetime":"2018-08-11 07:00"},{"value":99536.2556370818,"datetime":"2018-08-11 08:00"},{"value":99562.2975528794,"datetime":"2018-08-11 09:00"},{"value":99581.7937540879,"datetime":"2018-08-11 10:00"},{"value":99581.6431716626,"datetime":"2018-08-11 11:00"},{"value":99552.1690890055,"datetime":"2018-08-11 12:00"},{"value":99497.3921993042,"datetime":"2018-08-11 13:00"},{"value":99424.7575481928,"datetime":"2018-08-11 14:00"},{"value":99343.9995065755,"datetime":"2018-08-11 15:00"},{"value":99274.0097464358,"datetime":"2018-08-11 16:00"},{"value":99235.9692650271,"datetime":"2018-08-11 17:00"},{"value":99245.3907760485,"datetime":"2018-08-11 18:00"},{"value":99295.1138589811,"datetime":"2018-08-11 19:00"},{"value":99372.3098097514,"datetime":"2018-08-11 20:00"},{"value":99461.3258485006,"datetime":"2018-08-11 21:00"},{"value":99535.2128922272,"datetime":"2018-08-11 22:00"},{"value":99564.1977821443,"datetime":"2018-08-11 23:00"},{"value":99529.3974878087,"datetime":"2018-08-12 00:00"},{"value":99455.4894921521,"datetime":"2018-08-12 01:00"},{"value":99378.0414064498,"datetime":"2018-08-12 02:00"},{"value":99326.3932346277,"datetime":"2018-08-12 03:00"},{"value":99304.9745512121,"datetime":"2018-08-12 04:00"},{"value":99311.9873233799,"datetime":"2018-08-12 05:00"},{"value":99344.3398966088,"datetime":"2018-08-12 06:00"},{"value":99393.7661295793,"datetime":"2018-08-12 07:00"},{"value":99450.7062592728,"datetime":"2018-08-12 08:00"},{"value":99504.234777582,"datetime":"2018-08-12 09:00"},{"value":99537.9631960439,"datetime":"2018-08-12 10:00"},{"value":99534.1372811071,"datetime":"2018-08-12 11:00"},{"value":99482.6025485121,"datetime":"2018-08-12 12:00"},{"value":99403.6035111682,"datetime":"2018-08-12 13:00"},{"value":99324.9844312771,"datetime":"2018-08-12 14:00"},{"value":99269.2934570245,"datetime":"2018-08-12 15:00"},{"value":99237.894280532,"datetime":"2018-08-12 16:00"}],"pm25":[{"value":25,"datetime":"2018-08-10 17:00"},{"value":24,"datetime":"2018-08-10 18:00"},{"value":23,"datetime":"2018-08-10 19:00"},{"value":21,"datetime":"2018-08-10 20:00"},{"value":20,"datetime":"2018-08-10 21:00"},{"value":20,"datetime":"2018-08-10 22:00"},{"value":20,"datetime":"2018-08-10 23:00"},{"value":22,"datetime":"2018-08-11 00:00"},{"value":23,"datetime":"2018-08-11 01:00"},{"value":24,"datetime":"2018-08-11 02:00"},{"value":23,"datetime":"2018-08-11 03:00"},{"value":21,"datetime":"2018-08-11 04:00"},{"value":18,"datetime":"2018-08-11 05:00"},{"value":17,"datetime":"2018-08-11 06:00"},{"value":16,"datetime":"2018-08-11 07:00"},{"value":16,"datetime":"2018-08-11 08:00"},{"value":16,"datetime":"2018-08-11 09:00"},{"value":16,"datetime":"2018-08-11 10:00"},{"value":16,"datetime":"2018-08-11 11:00"},{"value":15,"datetime":"2018-08-11 12:00"},{"value":14,"datetime":"2018-08-11 13:00"},{"value":14,"datetime":"2018-08-11 14:00"},{"value":14,"datetime":"2018-08-11 15:00"},{"value":15,"datetime":"2018-08-11 16:00"},{"value":15,"datetime":"2018-08-11 17:00"},{"value":15,"datetime":"2018-08-11 18:00"},{"value":14,"datetime":"2018-08-11 19:00"},{"value":14,"datetime":"2018-08-11 20:00"},{"value":14,"datetime":"2018-08-11 21:00"},{"value":15,"datetime":"2018-08-11 22:00"},{"value":15,"datetime":"2018-08-11 23:00"},{"value":14,"datetime":"2018-08-12 00:00"},{"value":13,"datetime":"2018-08-12 01:00"},{"value":11,"datetime":"2018-08-12 02:00"},{"value":8,"datetime":"2018-08-12 03:00"},{"value":6,"datetime":"2018-08-12 04:00"},{"value":4,"datetime":"2018-08-12 05:00"},{"value":4,"datetime":"2018-08-12 06:00"},{"value":4,"datetime":"2018-08-12 07:00"},{"value":4,"datetime":"2018-08-12 08:00"},{"value":4,"datetime":"2018-08-12 09:00"},{"value":4,"datetime":"2018-08-12 10:00"},{"value":4,"datetime":"2018-08-12 11:00"},{"value":4,"datetime":"2018-08-12 12:00"},{"value":5,"datetime":"2018-08-12 13:00"},{"value":6,"datetime":"2018-08-12 14:00"},{"value":6,"datetime":"2018-08-12 15:00"},{"value":6,"datetime":"2018-08-12 16:00"}],"precipitation":[{"value":0,"datetime":"2018-08-10 17:00"},{"value":0,"datetime":"2018-08-10 18:00"},{"value":1.1824,"datetime":"2018-08-10 19:00"},{"value":1.0751,"datetime":"2018-08-10 20:00"},{"value":0.6826,"datetime":"2018-08-10 21:00"},{"value":0.2864,"datetime":"2018-08-10 22:00"},{"value":0,"datetime":"2018-08-10 23:00"},{"value":0,"datetime":"2018-08-11 00:00"},{"value":0,"datetime":"2018-08-11 01:00"},{"value":0,"datetime":"2018-08-11 02:00"},{"value":0,"datetime":"2018-08-11 03:00"},{"value":0,"datetime":"2018-08-11 04:00"},{"value":0,"datetime":"2018-08-11 05:00"},{"value":0,"datetime":"2018-08-11 06:00"},{"value":0,"datetime":"2018-08-11 07:00"},{"value":0,"datetime":"2018-08-11 08:00"},{"value":0,"datetime":"2018-08-11 09:00"},{"value":0,"datetime":"2018-08-11 10:00"},{"value":0,"datetime":"2018-08-11 11:00"},{"value":0.0438,"datetime":"2018-08-11 12:00"},{"value":0.0596,"datetime":"2018-08-11 13:00"},{"value":0.0652,"datetime":"2018-08-11 14:00"},{"value":0.068,"datetime":"2018-08-11 15:00"},{"value":0.139,"datetime":"2018-08-11 16:00"},{"value":0.3656,"datetime":"2018-08-11 17:00"},{"value":0.7871,"datetime":"2018-08-11 18:00"},{"value":1.2511,"datetime":"2018-08-11 19:00"},{"value":1.5575,"datetime":"2018-08-11 20:00"},{"value":1.564,"datetime":"2018-08-11 21:00"},{"value":1.359,"datetime":"2018-08-11 22:00"},{"value":1.0889,"datetime":"2018-08-11 23:00"},{"value":0.8701,"datetime":"2018-08-12 00:00"},{"value":0.7005,"datetime":"2018-08-12 01:00"},{"value":0.5481,"datetime":"2018-08-12 02:00"},{"value":0.3893,"datetime":"2018-08-12 03:00"},{"value":0.234,"datetime":"2018-08-12 04:00"},{"value":0.1004,"datetime":"2018-08-12 05:00"},{"value":0,"datetime":"2018-08-12 06:00"},{"value":0,"datetime":"2018-08-12 07:00"},{"value":0.2374,"datetime":"2018-08-12 08:00"},{"value":0.6365,"datetime":"2018-08-12 09:00"},{"value":1.0779,"datetime":"2018-08-12 10:00"},{"value":1.3592,"datetime":"2018-08-12 11:00"},{"value":1.3344,"datetime":"2018-08-12 12:00"},{"value":1.0837,"datetime":"2018-08-12 13:00"},{"value":0.7435,"datetime":"2018-08-12 14:00"},{"value":0.4301,"datetime":"2018-08-12 15:00"},{"value":0.1777,"datetime":"2018-08-12 16:00"}],"wind":[{"direction":35.34,"speed":19.85,"datetime":"2018-08-10 17:00"},{"direction":37.67,"speed":15.49,"datetime":"2018-08-10 18:00"},{"direction":35.96,"speed":10.52,"datetime":"2018-08-10 19:00"},{"direction":27.57,"speed":6.89,"datetime":"2018-08-10 20:00"},{"direction":16.21,"speed":6.08,"datetime":"2018-08-10 21:00"},{"direction":10.18,"speed":7.03,"datetime":"2018-08-10 22:00"},{"direction":6.71,"speed":8.24,"datetime":"2018-08-10 23:00"},{"direction":1.34,"speed":8.75,"datetime":"2018-08-11 00:00"},{"direction":354.29,"speed":8.83,"datetime":"2018-08-11 01:00"},{"direction":347.6,"speed":8.98,"datetime":"2018-08-11 02:00"},{"direction":343.39,"speed":9.43,"datetime":"2018-08-11 03:00"},{"direction":340.44,"speed":10.09,"datetime":"2018-08-11 04:00"},{"direction":336.76,"speed":10.86,"datetime":"2018-08-11 05:00"},{"direction":332,"speed":11.76,"datetime":"2018-08-11 06:00"},{"direction":330.08,"speed":12.75,"datetime":"2018-08-11 07:00"},{"direction":334.74,"speed":13.74,"datetime":"2018-08-11 08:00"},{"direction":346.35,"speed":15.17,"datetime":"2018-08-11 09:00"},{"direction":356.76,"speed":17.39,"datetime":"2018-08-11 10:00"},{"direction":359.92,"speed":19.46,"datetime":"2018-08-11 11:00"},{"direction":354.47,"speed":20.49,"datetime":"2018-08-11 12:00"},{"direction":345.37,"speed":21.01,"datetime":"2018-08-11 13:00"},{"direction":338.31,"speed":21.14,"datetime":"2018-08-11 14:00"},{"direction":337.19,"speed":20.15,"datetime":"2018-08-11 15:00"},{"direction":341,"speed":17.76,"datetime":"2018-08-11 16:00"},{"direction":348.83,"speed":14.08,"datetime":"2018-08-11 17:00"},{"direction":2.14,"speed":9.53,"datetime":"2018-08-11 18:00"},{"direction":26.44,"speed":5.75,"datetime":"2018-08-11 19:00"},{"direction":52.75,"speed":3.77,"datetime":"2018-08-11 20:00"},{"direction":30.05,"speed":2.3,"datetime":"2018-08-11 21:00"},{"direction":328.54,"speed":3.88,"datetime":"2018-08-11 22:00"},{"direction":312.25,"speed":7.04,"datetime":"2018-08-11 23:00"},{"direction":305.17,"speed":8.98,"datetime":"2018-08-12 00:00"},{"direction":301.37,"speed":9.77,"datetime":"2018-08-12 01:00"},{"direction":302.39,"speed":9.97,"datetime":"2018-08-12 02:00"},{"direction":310.16,"speed":10.21,"datetime":"2018-08-12 03:00"},{"direction":321.45,"speed":11.04,"datetime":"2018-08-12 04:00"},{"direction":331.27,"speed":12.65,"datetime":"2018-08-12 05:00"},{"direction":337.24,"speed":14.69,"datetime":"2018-08-12 06:00"},{"direction":340.86,"speed":16.39,"datetime":"2018-08-12 07:00"},{"direction":344.02,"speed":17.03,"datetime":"2018-08-12 08:00"},{"direction":348.08,"speed":16.3,"datetime":"2018-08-12 09:00"},{"direction":352.68,"speed":15.16,"datetime":"2018-08-12 10:00"},{"direction":355.72,"speed":14.73,"datetime":"2018-08-12 11:00"},{"direction":355.58,"speed":15.69,"datetime":"2018-08-12 12:00"},{"direction":356.48,"speed":17.24,"datetime":"2018-08-12 13:00"},{"direction":2.95,"speed":18.34,"datetime":"2018-08-12 14:00"},{"direction":17.2,"speed":18.99,"datetime":"2018-08-12 15:00"},{"direction":32.62,"speed":19.9,"datetime":"2018-08-12 16:00"}],"temperature":[{"value":34,"datetime":"2018-08-10 17:00"},{"value":31.83,"datetime":"2018-08-10 18:00"},{"value":30.67,"datetime":"2018-08-10 19:00"},{"value":29.2,"datetime":"2018-08-10 20:00"},{"value":28.73,"datetime":"2018-08-10 21:00"},{"value":28.27,"datetime":"2018-08-10 22:00"},{"value":27.2,"datetime":"2018-08-10 23:00"},{"value":27,"datetime":"2018-08-11 00:00"},{"value":26.8,"datetime":"2018-08-11 01:00"},{"value":26.3,"datetime":"2018-08-11 02:00"},{"value":26.2,"datetime":"2018-08-11 03:00"},{"value":26.1,"datetime":"2018-08-11 04:00"},{"value":26,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":26.5,"datetime":"2018-08-11 07:00"},{"value":27.1,"datetime":"2018-08-11 08:00"},{"value":27.7,"datetime":"2018-08-11 09:00"},{"value":28.3,"datetime":"2018-08-11 10:00"},{"value":29,"datetime":"2018-08-11 11:00"},{"value":29.5,"datetime":"2018-08-11 12:00"},{"value":30,"datetime":"2018-08-11 13:00"},{"value":30.5,"datetime":"2018-08-11 14:00"},{"value":31,"datetime":"2018-08-11 15:00"},{"value":31,"datetime":"2018-08-11 16:00"},{"value":31,"datetime":"2018-08-11 17:00"},{"value":31,"datetime":"2018-08-11 18:00"},{"value":30.61,"datetime":"2018-08-11 19:00"},{"value":30.01,"datetime":"2018-08-11 20:00"},{"value":29.47,"datetime":"2018-08-11 21:00"},{"value":29.07,"datetime":"2018-08-11 22:00"},{"value":28.76,"datetime":"2018-08-11 23:00"},{"value":28.39,"datetime":"2018-08-12 00:00"},{"value":27.7,"datetime":"2018-08-12 01:00"},{"value":26.97,"datetime":"2018-08-12 02:00"},{"value":26.54,"datetime":"2018-08-12 03:00"},{"value":26.21,"datetime":"2018-08-12 04:00"},{"value":26.05,"datetime":"2018-08-12 05:00"},{"value":26.53,"datetime":"2018-08-12 06:00"},{"value":27.01,"datetime":"2018-08-12 07:00"},{"value":27.5,"datetime":"2018-08-12 08:00"},{"value":28.18,"datetime":"2018-08-12 09:00"},{"value":26,"datetime":"2018-08-12 10:00"},{"value":29.26,"datetime":"2018-08-12 11:00"},{"value":28.92,"datetime":"2018-08-12 12:00"},{"value":28.29,"datetime":"2018-08-12 13:00"},{"value":27.81,"datetime":"2018-08-12 14:00"},{"value":30.9,"datetime":"2018-08-12 15:00"},{"value":28.91,"datetime":"2018-08-12 16:00"}]}
         * forecast_keypoint : 附近正在下雨，出门还是带把伞吧~
         * primary : 0
         * daily : {"status":"ok","coldRisk":[{"index":"4","desc":"极易发","datetime":"2018-08-10"},{"index":"3","desc":"易发","datetime":"2018-08-11"},{"index":"3","desc":"易发","datetime":"2018-08-12"},{"index":"3","desc":"易发","datetime":"2018-08-13"},{"index":"3","desc":"易发","datetime":"2018-08-14"}],"skycon_20h_32h":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}],"temperature":[{"date":"2018-08-10","max":34,"avg":29.99,"min":26},{"date":"2018-08-11","max":31,"avg":28.54,"min":26},{"date":"2018-08-12","max":30.9,"avg":28.29,"min":26},{"date":"2018-08-13","max":30,"avg":27,"min":26},{"date":"2018-08-14","max":32,"avg":29.18,"min":27}],"skycon":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}],"cloudrate":[{"date":"2018-08-10","max":1,"avg":0.8,"min":0.3},{"date":"2018-08-11","max":1,"avg":1,"min":1},{"date":"2018-08-12","max":1,"avg":0.96,"min":0.78},{"date":"2018-08-13","max":1,"avg":1,"min":0.99},{"date":"2018-08-14","max":1,"avg":0.98,"min":0.89}],"aqi":[{"date":"2018-08-10","max":70,"avg":36,"min":30},{"date":"2018-08-11","max":35,"avg":25.42,"min":22},{"date":"2018-08-12","max":30,"avg":13.12,"min":8},{"date":"2018-08-13","max":39,"avg":21.25,"min":7},{"date":"2018-08-14","max":14,"avg":7.5,"min":7}],"comfort":[{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"3","desc":"热","datetime":"2018-08-11"},{"index":"0","desc":"闷热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"0","desc":"闷热","datetime":"2018-08-14"}],"humidity":[{"date":"2018-08-10","max":0.81,"avg":0.75,"min":0.52},{"date":"2018-08-11","max":0.84,"avg":0.75,"min":0.57},{"date":"2018-08-12","max":0.88,"avg":0.76,"min":0.55},{"date":"2018-08-13","max":0.93,"avg":0.9,"min":0.81},{"date":"2018-08-14","max":0.95,"avg":0.88,"min":0.79}],"astro":[{"date":"2018-08-10","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-11","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-12","sunset":{"time":"19:16"},"sunrise":{"time":"06:20"}},{"date":"2018-08-13","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}},{"date":"2018-08-14","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}}],"pres":[{"date":"2018-08-10","max":99899.66,"avg":99569.56,"min":99515.56},{"date":"2018-08-11","max":99648.26,"avg":99475.6,"min":99235.97},{"date":"2018-08-12","max":99537.96,"avg":99379.13,"min":99226.85},{"date":"2018-08-13","max":99417.3,"avg":99262.42,"min":99118.35},{"date":"2018-08-14","max":99661.08,"avg":99293.56,"min":99131.19}],"ultraviolet":[{"index":"1","desc":"最弱","datetime":"2018-08-10"},{"index":"1","desc":"最弱","datetime":"2018-08-11"},{"index":"1","desc":"最弱","datetime":"2018-08-12"},{"index":"1","desc":"最弱","datetime":"2018-08-13"},{"index":"1","desc":"最弱","datetime":"2018-08-14"}],"pm25":[{"date":"2018-08-10","max":50,"avg":21.86,"min":20},{"date":"2018-08-11","max":24,"avg":16.75,"min":14},{"date":"2018-08-12","max":20,"avg":7.67,"min":4},{"date":"2018-08-13","max":27,"avg":13.62,"min":3},{"date":"2018-08-14","max":8,"avg":3.38,"min":3}],"dressing":[{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"2","desc":"很热","datetime":"2018-08-11"},{"index":"2","desc":"很热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"3","desc":"热","datetime":"2018-08-14"}],"carWashing":[{"index":"3","desc":"较不适宜","datetime":"2018-08-10"},{"index":"3","desc":"较不适宜","datetime":"2018-08-11"},{"index":"3","desc":"较不适宜","datetime":"2018-08-12"},{"index":"3","desc":"较不适宜","datetime":"2018-08-13"},{"index":"3","desc":"较不适宜","datetime":"2018-08-14"}],"precipitation":[{"date":"2018-08-10","max":1.1824,"avg":0.4609,"min":0},{"date":"2018-08-11","max":1.564,"avg":0.3479,"min":0},{"date":"2018-08-12","max":1.3592,"avg":0.4445,"min":0},{"date":"2018-08-13","max":12.645,"avg":4.3305,"min":0},{"date":"2018-08-14","max":1.4058,"avg":0.707,"min":0.1332}],"wind":[{"date":"2018-08-10","max":{"direction":24.76,"speed":23.94},"avg":{"direction":18.31,"speed":15.26},"min":{"direction":16.21,"speed":6.08}},{"date":"2018-08-11","max":{"direction":338.31,"speed":21.14},"avg":{"direction":346.42,"speed":12.25},"min":{"direction":30.05,"speed":2.3}},{"date":"2018-08-12","max":{"direction":32.62,"speed":19.9},"avg":{"direction":349.15,"speed":13.75},"min":{"direction":356.35,"speed":5.96}},{"date":"2018-08-13","max":{"direction":286.23,"speed":27.1},"avg":{"direction":282.74,"speed":22.19},"min":{"direction":264.69,"speed":14.08}},{"date":"2018-08-14","max":{"direction":176.49,"speed":28.95},"avg":{"direction":176.1,"speed":17.31},"min":{"direction":234.32,"speed":1.85}}],"skycon_08h_20h":[{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}]}
         * minutely : {"status":"ok","description":"附近正在下雨，出门还是带把伞吧~","probability":[0,0,0,0],"datasource":"radar","precipitation_2h":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"precipitation":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}
         */

        private HourlyBean hourly;
        private String forecast_keypoint;
        private Double primary;
        private DailyBean daily;
        private MinutelyBean minutely;

        public HourlyBean getHourly() {
            return hourly;
        }

        public void setHourly(HourlyBean hourly) {
            this.hourly = hourly;
        }

        public String getForecast_keypoint() {
            return forecast_keypoint;
        }

        public void setForecast_keypoint(String forecast_keypoint) {
            this.forecast_keypoint = forecast_keypoint;
        }

        public Double getPrimary() {
            return primary;
        }

        public void setPrimary(Double primary) {
            this.primary = primary;
        }

        public DailyBean getDaily() {
            return daily;
        }

        public void setDaily(DailyBean daily) {
            this.daily = daily;
        }

        public MinutelyBean getMinutely() {
            return minutely;
        }

        public void setMinutely(MinutelyBean minutely) {
            this.minutely = minutely;
        }

        public static class HourlyBean {
            /**
             * status : ok
             * description : 多云，今天傍晚19点钟后转小雨
             * skycon : [{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 17:00"},{"value":"PARTLY_CLOUDY_DAY","datetime":"2018-08-10 18:00"},{"value":"RAIN","datetime":"2018-08-10 19:00"},{"value":"RAIN","datetime":"2018-08-10 20:00"},{"value":"RAIN","datetime":"2018-08-10 21:00"},{"value":"RAIN","datetime":"2018-08-10 22:00"},{"value":"CLOUDY","datetime":"2018-08-10 23:00"},{"value":"CLOUDY","datetime":"2018-08-11 00:00"},{"value":"CLOUDY","datetime":"2018-08-11 01:00"},{"value":"CLOUDY","datetime":"2018-08-11 02:00"},{"value":"CLOUDY","datetime":"2018-08-11 03:00"},{"value":"CLOUDY","datetime":"2018-08-11 04:00"},{"value":"CLOUDY","datetime":"2018-08-11 05:00"},{"value":"CLOUDY","datetime":"2018-08-11 06:00"},{"value":"CLOUDY","datetime":"2018-08-11 07:00"},{"value":"CLOUDY","datetime":"2018-08-11 08:00"},{"value":"CLOUDY","datetime":"2018-08-11 09:00"},{"value":"CLOUDY","datetime":"2018-08-11 10:00"},{"value":"CLOUDY","datetime":"2018-08-11 11:00"},{"value":"CLOUDY","datetime":"2018-08-11 12:00"},{"value":"CLOUDY","datetime":"2018-08-11 13:00"},{"value":"RAIN","datetime":"2018-08-11 14:00"},{"value":"RAIN","datetime":"2018-08-11 15:00"},{"value":"RAIN","datetime":"2018-08-11 16:00"},{"value":"RAIN","datetime":"2018-08-11 17:00"},{"value":"RAIN","datetime":"2018-08-11 18:00"},{"value":"RAIN","datetime":"2018-08-11 19:00"},{"value":"RAIN","datetime":"2018-08-11 20:00"},{"value":"RAIN","datetime":"2018-08-11 21:00"},{"value":"RAIN","datetime":"2018-08-11 22:00"},{"value":"RAIN","datetime":"2018-08-11 23:00"},{"value":"RAIN","datetime":"2018-08-12 00:00"},{"value":"RAIN","datetime":"2018-08-12 01:00"},{"value":"RAIN","datetime":"2018-08-12 02:00"},{"value":"RAIN","datetime":"2018-08-12 03:00"},{"value":"RAIN","datetime":"2018-08-12 04:00"},{"value":"RAIN","datetime":"2018-08-12 05:00"},{"value":"CLOUDY","datetime":"2018-08-12 06:00"},{"value":"CLOUDY","datetime":"2018-08-12 07:00"},{"value":"RAIN","datetime":"2018-08-12 08:00"},{"value":"RAIN","datetime":"2018-08-12 09:00"},{"value":"RAIN","datetime":"2018-08-12 10:00"},{"value":"RAIN","datetime":"2018-08-12 11:00"},{"value":"RAIN","datetime":"2018-08-12 12:00"},{"value":"RAIN","datetime":"2018-08-12 13:00"},{"value":"RAIN","datetime":"2018-08-12 14:00"},{"value":"RAIN","datetime":"2018-08-12 15:00"},{"value":"RAIN","datetime":"2018-08-12 16:00"}]
             * cloudrate : [{"value":0.3,"datetime":"2018-08-10 17:00"},{"value":0.3,"datetime":"2018-08-10 18:00"},{"value":1,"datetime":"2018-08-10 19:00"},{"value":1,"datetime":"2018-08-10 20:00"},{"value":1,"datetime":"2018-08-10 21:00"},{"value":1,"datetime":"2018-08-10 22:00"},{"value":1,"datetime":"2018-08-10 23:00"},{"value":1,"datetime":"2018-08-11 00:00"},{"value":1,"datetime":"2018-08-11 01:00"},{"value":1,"datetime":"2018-08-11 02:00"},{"value":1,"datetime":"2018-08-11 03:00"},{"value":1,"datetime":"2018-08-11 04:00"},{"value":1,"datetime":"2018-08-11 05:00"},{"value":1,"datetime":"2018-08-11 06:00"},{"value":1,"datetime":"2018-08-11 07:00"},{"value":1,"datetime":"2018-08-11 08:00"},{"value":1,"datetime":"2018-08-11 09:00"},{"value":1,"datetime":"2018-08-11 10:00"},{"value":1,"datetime":"2018-08-11 11:00"},{"value":1,"datetime":"2018-08-11 12:00"},{"value":1,"datetime":"2018-08-11 13:00"},{"value":1,"datetime":"2018-08-11 14:00"},{"value":1,"datetime":"2018-08-11 15:00"},{"value":1,"datetime":"2018-08-11 16:00"},{"value":1,"datetime":"2018-08-11 17:00"},{"value":1,"datetime":"2018-08-11 18:00"},{"value":1,"datetime":"2018-08-11 19:00"},{"value":1,"datetime":"2018-08-11 20:00"},{"value":1,"datetime":"2018-08-11 21:00"},{"value":1,"datetime":"2018-08-11 22:00"},{"value":1,"datetime":"2018-08-11 23:00"},{"value":1,"datetime":"2018-08-12 00:00"},{"value":1,"datetime":"2018-08-12 01:00"},{"value":1,"datetime":"2018-08-12 02:00"},{"value":1,"datetime":"2018-08-12 03:00"},{"value":1,"datetime":"2018-08-12 04:00"},{"value":1,"datetime":"2018-08-12 05:00"},{"value":1,"datetime":"2018-08-12 06:00"},{"value":1,"datetime":"2018-08-12 07:00"},{"value":1,"datetime":"2018-08-12 08:00"},{"value":1,"datetime":"2018-08-12 09:00"},{"value":1,"datetime":"2018-08-12 10:00"},{"value":1,"datetime":"2018-08-12 11:00"},{"value":1,"datetime":"2018-08-12 12:00"},{"value":1,"datetime":"2018-08-12 13:00"},{"value":1,"datetime":"2018-08-12 14:00"},{"value":0.99,"datetime":"2018-08-12 15:00"},{"value":0.96,"datetime":"2018-08-12 16:00"}]
             * aqi : [{"value":62,"datetime":"2018-08-10 17:00"},{"value":35,"datetime":"2018-08-10 18:00"},{"value":34,"datetime":"2018-08-10 19:00"},{"value":31,"datetime":"2018-08-10 20:00"},{"value":30,"datetime":"2018-08-10 21:00"},{"value":30,"datetime":"2018-08-10 22:00"},{"value":30,"datetime":"2018-08-10 23:00"},{"value":33,"datetime":"2018-08-11 00:00"},{"value":34,"datetime":"2018-08-11 01:00"},{"value":35,"datetime":"2018-08-11 02:00"},{"value":34,"datetime":"2018-08-11 03:00"},{"value":31,"datetime":"2018-08-11 04:00"},{"value":27,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":24,"datetime":"2018-08-11 07:00"},{"value":24,"datetime":"2018-08-11 08:00"},{"value":24,"datetime":"2018-08-11 09:00"},{"value":24,"datetime":"2018-08-11 10:00"},{"value":24,"datetime":"2018-08-11 11:00"},{"value":23,"datetime":"2018-08-11 12:00"},{"value":22,"datetime":"2018-08-11 13:00"},{"value":22,"datetime":"2018-08-11 14:00"},{"value":22,"datetime":"2018-08-11 15:00"},{"value":23,"datetime":"2018-08-11 16:00"},{"value":23,"datetime":"2018-08-11 17:00"},{"value":23,"datetime":"2018-08-11 18:00"},{"value":22,"datetime":"2018-08-11 19:00"},{"value":22,"datetime":"2018-08-11 20:00"},{"value":22,"datetime":"2018-08-11 21:00"},{"value":23,"datetime":"2018-08-11 22:00"},{"value":23,"datetime":"2018-08-11 23:00"},{"value":22,"datetime":"2018-08-12 00:00"},{"value":20,"datetime":"2018-08-12 01:00"},{"value":18,"datetime":"2018-08-12 02:00"},{"value":14,"datetime":"2018-08-12 03:00"},{"value":11,"datetime":"2018-08-12 04:00"},{"value":8,"datetime":"2018-08-12 05:00"},{"value":8,"datetime":"2018-08-12 06:00"},{"value":8,"datetime":"2018-08-12 07:00"},{"value":8,"datetime":"2018-08-12 08:00"},{"value":8,"datetime":"2018-08-12 09:00"},{"value":8,"datetime":"2018-08-12 10:00"},{"value":8,"datetime":"2018-08-12 11:00"},{"value":8,"datetime":"2018-08-12 12:00"},{"value":10,"datetime":"2018-08-12 13:00"},{"value":11,"datetime":"2018-08-12 14:00"},{"value":11,"datetime":"2018-08-12 15:00"},{"value":11,"datetime":"2018-08-12 16:00"}]
             * humidity : [{"value":0.61,"datetime":"2018-08-10 17:00"},{"value":0.69,"datetime":"2018-08-10 18:00"},{"value":0.75,"datetime":"2018-08-10 19:00"},{"value":0.78,"datetime":"2018-08-10 20:00"},{"value":0.8,"datetime":"2018-08-10 21:00"},{"value":0.81,"datetime":"2018-08-10 22:00"},{"value":0.81,"datetime":"2018-08-10 23:00"},{"value":0.81,"datetime":"2018-08-11 00:00"},{"value":0.82,"datetime":"2018-08-11 01:00"},{"value":0.82,"datetime":"2018-08-11 02:00"},{"value":0.83,"datetime":"2018-08-11 03:00"},{"value":0.83,"datetime":"2018-08-11 04:00"},{"value":0.83,"datetime":"2018-08-11 05:00"},{"value":0.84,"datetime":"2018-08-11 06:00"},{"value":0.84,"datetime":"2018-08-11 07:00"},{"value":0.83,"datetime":"2018-08-11 08:00"},{"value":0.78,"datetime":"2018-08-11 09:00"},{"value":0.73,"datetime":"2018-08-11 10:00"},{"value":0.67,"datetime":"2018-08-11 11:00"},{"value":0.62,"datetime":"2018-08-11 12:00"},{"value":0.59,"datetime":"2018-08-11 13:00"},{"value":0.57,"datetime":"2018-08-11 14:00"},{"value":0.58,"datetime":"2018-08-11 15:00"},{"value":0.6,"datetime":"2018-08-11 16:00"},{"value":0.64,"datetime":"2018-08-11 17:00"},{"value":0.7,"datetime":"2018-08-11 18:00"},{"value":0.77,"datetime":"2018-08-11 19:00"},{"value":0.82,"datetime":"2018-08-11 20:00"},{"value":0.83,"datetime":"2018-08-11 21:00"},{"value":0.83,"datetime":"2018-08-11 22:00"},{"value":0.81,"datetime":"2018-08-11 23:00"},{"value":0.81,"datetime":"2018-08-12 00:00"},{"value":0.81,"datetime":"2018-08-12 01:00"},{"value":0.82,"datetime":"2018-08-12 02:00"},{"value":0.83,"datetime":"2018-08-12 03:00"},{"value":0.85,"datetime":"2018-08-12 04:00"},{"value":0.85,"datetime":"2018-08-12 05:00"},{"value":0.85,"datetime":"2018-08-12 06:00"},{"value":0.85,"datetime":"2018-08-12 07:00"},{"value":0.85,"datetime":"2018-08-12 08:00"},{"value":0.87,"datetime":"2018-08-12 09:00"},{"value":0.88,"datetime":"2018-08-12 10:00"},{"value":0.86,"datetime":"2018-08-12 11:00"},{"value":0.78,"datetime":"2018-08-12 12:00"},{"value":0.69,"datetime":"2018-08-12 13:00"},{"value":0.6,"datetime":"2018-08-12 14:00"},{"value":0.56,"datetime":"2018-08-12 15:00"},{"value":0.55,"datetime":"2018-08-12 16:00"}]
             * pres : [{"value":99523.6446458357,"datetime":"2018-08-10 17:00"},{"value":99515.5613853441,"datetime":"2018-08-10 18:00"},{"value":99518.171166162,"datetime":"2018-08-10 19:00"},{"value":99541.0013277177,"datetime":"2018-08-10 20:00"},{"value":99587.563853488,"datetime":"2018-08-10 21:00"},{"value":99637.3093031439,"datetime":"2018-08-10 22:00"},{"value":99663.6728804052,"datetime":"2018-08-10 23:00"},{"value":99648.2618189219,"datetime":"2018-08-11 00:00"},{"value":99605.3714720644,"datetime":"2018-08-11 01:00"},{"value":99557.4692231338,"datetime":"2018-08-11 02:00"},{"value":99522.8979679377,"datetime":"2018-08-11 03:00"},{"value":99503.5026523102,"datetime":"2018-08-11 04:00"},{"value":99497.0037345927,"datetime":"2018-08-11 05:00"},{"value":99501.2759577473,"datetime":"2018-08-11 06:00"},{"value":99514.8112032209,"datetime":"2018-08-11 07:00"},{"value":99536.2556370818,"datetime":"2018-08-11 08:00"},{"value":99562.2975528794,"datetime":"2018-08-11 09:00"},{"value":99581.7937540879,"datetime":"2018-08-11 10:00"},{"value":99581.6431716626,"datetime":"2018-08-11 11:00"},{"value":99552.1690890055,"datetime":"2018-08-11 12:00"},{"value":99497.3921993042,"datetime":"2018-08-11 13:00"},{"value":99424.7575481928,"datetime":"2018-08-11 14:00"},{"value":99343.9995065755,"datetime":"2018-08-11 15:00"},{"value":99274.0097464358,"datetime":"2018-08-11 16:00"},{"value":99235.9692650271,"datetime":"2018-08-11 17:00"},{"value":99245.3907760485,"datetime":"2018-08-11 18:00"},{"value":99295.1138589811,"datetime":"2018-08-11 19:00"},{"value":99372.3098097514,"datetime":"2018-08-11 20:00"},{"value":99461.3258485006,"datetime":"2018-08-11 21:00"},{"value":99535.2128922272,"datetime":"2018-08-11 22:00"},{"value":99564.1977821443,"datetime":"2018-08-11 23:00"},{"value":99529.3974878087,"datetime":"2018-08-12 00:00"},{"value":99455.4894921521,"datetime":"2018-08-12 01:00"},{"value":99378.0414064498,"datetime":"2018-08-12 02:00"},{"value":99326.3932346277,"datetime":"2018-08-12 03:00"},{"value":99304.9745512121,"datetime":"2018-08-12 04:00"},{"value":99311.9873233799,"datetime":"2018-08-12 05:00"},{"value":99344.3398966088,"datetime":"2018-08-12 06:00"},{"value":99393.7661295793,"datetime":"2018-08-12 07:00"},{"value":99450.7062592728,"datetime":"2018-08-12 08:00"},{"value":99504.234777582,"datetime":"2018-08-12 09:00"},{"value":99537.9631960439,"datetime":"2018-08-12 10:00"},{"value":99534.1372811071,"datetime":"2018-08-12 11:00"},{"value":99482.6025485121,"datetime":"2018-08-12 12:00"},{"value":99403.6035111682,"datetime":"2018-08-12 13:00"},{"value":99324.9844312771,"datetime":"2018-08-12 14:00"},{"value":99269.2934570245,"datetime":"2018-08-12 15:00"},{"value":99237.894280532,"datetime":"2018-08-12 16:00"}]
             * pm25 : [{"value":25,"datetime":"2018-08-10 17:00"},{"value":24,"datetime":"2018-08-10 18:00"},{"value":23,"datetime":"2018-08-10 19:00"},{"value":21,"datetime":"2018-08-10 20:00"},{"value":20,"datetime":"2018-08-10 21:00"},{"value":20,"datetime":"2018-08-10 22:00"},{"value":20,"datetime":"2018-08-10 23:00"},{"value":22,"datetime":"2018-08-11 00:00"},{"value":23,"datetime":"2018-08-11 01:00"},{"value":24,"datetime":"2018-08-11 02:00"},{"value":23,"datetime":"2018-08-11 03:00"},{"value":21,"datetime":"2018-08-11 04:00"},{"value":18,"datetime":"2018-08-11 05:00"},{"value":17,"datetime":"2018-08-11 06:00"},{"value":16,"datetime":"2018-08-11 07:00"},{"value":16,"datetime":"2018-08-11 08:00"},{"value":16,"datetime":"2018-08-11 09:00"},{"value":16,"datetime":"2018-08-11 10:00"},{"value":16,"datetime":"2018-08-11 11:00"},{"value":15,"datetime":"2018-08-11 12:00"},{"value":14,"datetime":"2018-08-11 13:00"},{"value":14,"datetime":"2018-08-11 14:00"},{"value":14,"datetime":"2018-08-11 15:00"},{"value":15,"datetime":"2018-08-11 16:00"},{"value":15,"datetime":"2018-08-11 17:00"},{"value":15,"datetime":"2018-08-11 18:00"},{"value":14,"datetime":"2018-08-11 19:00"},{"value":14,"datetime":"2018-08-11 20:00"},{"value":14,"datetime":"2018-08-11 21:00"},{"value":15,"datetime":"2018-08-11 22:00"},{"value":15,"datetime":"2018-08-11 23:00"},{"value":14,"datetime":"2018-08-12 00:00"},{"value":13,"datetime":"2018-08-12 01:00"},{"value":11,"datetime":"2018-08-12 02:00"},{"value":8,"datetime":"2018-08-12 03:00"},{"value":6,"datetime":"2018-08-12 04:00"},{"value":4,"datetime":"2018-08-12 05:00"},{"value":4,"datetime":"2018-08-12 06:00"},{"value":4,"datetime":"2018-08-12 07:00"},{"value":4,"datetime":"2018-08-12 08:00"},{"value":4,"datetime":"2018-08-12 09:00"},{"value":4,"datetime":"2018-08-12 10:00"},{"value":4,"datetime":"2018-08-12 11:00"},{"value":4,"datetime":"2018-08-12 12:00"},{"value":5,"datetime":"2018-08-12 13:00"},{"value":6,"datetime":"2018-08-12 14:00"},{"value":6,"datetime":"2018-08-12 15:00"},{"value":6,"datetime":"2018-08-12 16:00"}]
             * precipitation : [{"value":0,"datetime":"2018-08-10 17:00"},{"value":0,"datetime":"2018-08-10 18:00"},{"value":1.1824,"datetime":"2018-08-10 19:00"},{"value":1.0751,"datetime":"2018-08-10 20:00"},{"value":0.6826,"datetime":"2018-08-10 21:00"},{"value":0.2864,"datetime":"2018-08-10 22:00"},{"value":0,"datetime":"2018-08-10 23:00"},{"value":0,"datetime":"2018-08-11 00:00"},{"value":0,"datetime":"2018-08-11 01:00"},{"value":0,"datetime":"2018-08-11 02:00"},{"value":0,"datetime":"2018-08-11 03:00"},{"value":0,"datetime":"2018-08-11 04:00"},{"value":0,"datetime":"2018-08-11 05:00"},{"value":0,"datetime":"2018-08-11 06:00"},{"value":0,"datetime":"2018-08-11 07:00"},{"value":0,"datetime":"2018-08-11 08:00"},{"value":0,"datetime":"2018-08-11 09:00"},{"value":0,"datetime":"2018-08-11 10:00"},{"value":0,"datetime":"2018-08-11 11:00"},{"value":0.0438,"datetime":"2018-08-11 12:00"},{"value":0.0596,"datetime":"2018-08-11 13:00"},{"value":0.0652,"datetime":"2018-08-11 14:00"},{"value":0.068,"datetime":"2018-08-11 15:00"},{"value":0.139,"datetime":"2018-08-11 16:00"},{"value":0.3656,"datetime":"2018-08-11 17:00"},{"value":0.7871,"datetime":"2018-08-11 18:00"},{"value":1.2511,"datetime":"2018-08-11 19:00"},{"value":1.5575,"datetime":"2018-08-11 20:00"},{"value":1.564,"datetime":"2018-08-11 21:00"},{"value":1.359,"datetime":"2018-08-11 22:00"},{"value":1.0889,"datetime":"2018-08-11 23:00"},{"value":0.8701,"datetime":"2018-08-12 00:00"},{"value":0.7005,"datetime":"2018-08-12 01:00"},{"value":0.5481,"datetime":"2018-08-12 02:00"},{"value":0.3893,"datetime":"2018-08-12 03:00"},{"value":0.234,"datetime":"2018-08-12 04:00"},{"value":0.1004,"datetime":"2018-08-12 05:00"},{"value":0,"datetime":"2018-08-12 06:00"},{"value":0,"datetime":"2018-08-12 07:00"},{"value":0.2374,"datetime":"2018-08-12 08:00"},{"value":0.6365,"datetime":"2018-08-12 09:00"},{"value":1.0779,"datetime":"2018-08-12 10:00"},{"value":1.3592,"datetime":"2018-08-12 11:00"},{"value":1.3344,"datetime":"2018-08-12 12:00"},{"value":1.0837,"datetime":"2018-08-12 13:00"},{"value":0.7435,"datetime":"2018-08-12 14:00"},{"value":0.4301,"datetime":"2018-08-12 15:00"},{"value":0.1777,"datetime":"2018-08-12 16:00"}]
             * wind : [{"direction":35.34,"speed":19.85,"datetime":"2018-08-10 17:00"},{"direction":37.67,"speed":15.49,"datetime":"2018-08-10 18:00"},{"direction":35.96,"speed":10.52,"datetime":"2018-08-10 19:00"},{"direction":27.57,"speed":6.89,"datetime":"2018-08-10 20:00"},{"direction":16.21,"speed":6.08,"datetime":"2018-08-10 21:00"},{"direction":10.18,"speed":7.03,"datetime":"2018-08-10 22:00"},{"direction":6.71,"speed":8.24,"datetime":"2018-08-10 23:00"},{"direction":1.34,"speed":8.75,"datetime":"2018-08-11 00:00"},{"direction":354.29,"speed":8.83,"datetime":"2018-08-11 01:00"},{"direction":347.6,"speed":8.98,"datetime":"2018-08-11 02:00"},{"direction":343.39,"speed":9.43,"datetime":"2018-08-11 03:00"},{"direction":340.44,"speed":10.09,"datetime":"2018-08-11 04:00"},{"direction":336.76,"speed":10.86,"datetime":"2018-08-11 05:00"},{"direction":332,"speed":11.76,"datetime":"2018-08-11 06:00"},{"direction":330.08,"speed":12.75,"datetime":"2018-08-11 07:00"},{"direction":334.74,"speed":13.74,"datetime":"2018-08-11 08:00"},{"direction":346.35,"speed":15.17,"datetime":"2018-08-11 09:00"},{"direction":356.76,"speed":17.39,"datetime":"2018-08-11 10:00"},{"direction":359.92,"speed":19.46,"datetime":"2018-08-11 11:00"},{"direction":354.47,"speed":20.49,"datetime":"2018-08-11 12:00"},{"direction":345.37,"speed":21.01,"datetime":"2018-08-11 13:00"},{"direction":338.31,"speed":21.14,"datetime":"2018-08-11 14:00"},{"direction":337.19,"speed":20.15,"datetime":"2018-08-11 15:00"},{"direction":341,"speed":17.76,"datetime":"2018-08-11 16:00"},{"direction":348.83,"speed":14.08,"datetime":"2018-08-11 17:00"},{"direction":2.14,"speed":9.53,"datetime":"2018-08-11 18:00"},{"direction":26.44,"speed":5.75,"datetime":"2018-08-11 19:00"},{"direction":52.75,"speed":3.77,"datetime":"2018-08-11 20:00"},{"direction":30.05,"speed":2.3,"datetime":"2018-08-11 21:00"},{"direction":328.54,"speed":3.88,"datetime":"2018-08-11 22:00"},{"direction":312.25,"speed":7.04,"datetime":"2018-08-11 23:00"},{"direction":305.17,"speed":8.98,"datetime":"2018-08-12 00:00"},{"direction":301.37,"speed":9.77,"datetime":"2018-08-12 01:00"},{"direction":302.39,"speed":9.97,"datetime":"2018-08-12 02:00"},{"direction":310.16,"speed":10.21,"datetime":"2018-08-12 03:00"},{"direction":321.45,"speed":11.04,"datetime":"2018-08-12 04:00"},{"direction":331.27,"speed":12.65,"datetime":"2018-08-12 05:00"},{"direction":337.24,"speed":14.69,"datetime":"2018-08-12 06:00"},{"direction":340.86,"speed":16.39,"datetime":"2018-08-12 07:00"},{"direction":344.02,"speed":17.03,"datetime":"2018-08-12 08:00"},{"direction":348.08,"speed":16.3,"datetime":"2018-08-12 09:00"},{"direction":352.68,"speed":15.16,"datetime":"2018-08-12 10:00"},{"direction":355.72,"speed":14.73,"datetime":"2018-08-12 11:00"},{"direction":355.58,"speed":15.69,"datetime":"2018-08-12 12:00"},{"direction":356.48,"speed":17.24,"datetime":"2018-08-12 13:00"},{"direction":2.95,"speed":18.34,"datetime":"2018-08-12 14:00"},{"direction":17.2,"speed":18.99,"datetime":"2018-08-12 15:00"},{"direction":32.62,"speed":19.9,"datetime":"2018-08-12 16:00"}]
             * temperature : [{"value":34,"datetime":"2018-08-10 17:00"},{"value":31.83,"datetime":"2018-08-10 18:00"},{"value":30.67,"datetime":"2018-08-10 19:00"},{"value":29.2,"datetime":"2018-08-10 20:00"},{"value":28.73,"datetime":"2018-08-10 21:00"},{"value":28.27,"datetime":"2018-08-10 22:00"},{"value":27.2,"datetime":"2018-08-10 23:00"},{"value":27,"datetime":"2018-08-11 00:00"},{"value":26.8,"datetime":"2018-08-11 01:00"},{"value":26.3,"datetime":"2018-08-11 02:00"},{"value":26.2,"datetime":"2018-08-11 03:00"},{"value":26.1,"datetime":"2018-08-11 04:00"},{"value":26,"datetime":"2018-08-11 05:00"},{"value":26,"datetime":"2018-08-11 06:00"},{"value":26.5,"datetime":"2018-08-11 07:00"},{"value":27.1,"datetime":"2018-08-11 08:00"},{"value":27.7,"datetime":"2018-08-11 09:00"},{"value":28.3,"datetime":"2018-08-11 10:00"},{"value":29,"datetime":"2018-08-11 11:00"},{"value":29.5,"datetime":"2018-08-11 12:00"},{"value":30,"datetime":"2018-08-11 13:00"},{"value":30.5,"datetime":"2018-08-11 14:00"},{"value":31,"datetime":"2018-08-11 15:00"},{"value":31,"datetime":"2018-08-11 16:00"},{"value":31,"datetime":"2018-08-11 17:00"},{"value":31,"datetime":"2018-08-11 18:00"},{"value":30.61,"datetime":"2018-08-11 19:00"},{"value":30.01,"datetime":"2018-08-11 20:00"},{"value":29.47,"datetime":"2018-08-11 21:00"},{"value":29.07,"datetime":"2018-08-11 22:00"},{"value":28.76,"datetime":"2018-08-11 23:00"},{"value":28.39,"datetime":"2018-08-12 00:00"},{"value":27.7,"datetime":"2018-08-12 01:00"},{"value":26.97,"datetime":"2018-08-12 02:00"},{"value":26.54,"datetime":"2018-08-12 03:00"},{"value":26.21,"datetime":"2018-08-12 04:00"},{"value":26.05,"datetime":"2018-08-12 05:00"},{"value":26.53,"datetime":"2018-08-12 06:00"},{"value":27.01,"datetime":"2018-08-12 07:00"},{"value":27.5,"datetime":"2018-08-12 08:00"},{"value":28.18,"datetime":"2018-08-12 09:00"},{"value":26,"datetime":"2018-08-12 10:00"},{"value":29.26,"datetime":"2018-08-12 11:00"},{"value":28.92,"datetime":"2018-08-12 12:00"},{"value":28.29,"datetime":"2018-08-12 13:00"},{"value":27.81,"datetime":"2018-08-12 14:00"},{"value":30.9,"datetime":"2018-08-12 15:00"},{"value":28.91,"datetime":"2018-08-12 16:00"}]
             */

            private String status;
            private String description;
            private List<SkyconBean> skycon;
            private List<CloudrateBean> cloudrate;
            private List<AqiBean> aqi;
            private List<HumidityBean> humidity;
            private List<PresBean> pres;
            private List<Pm25Bean> pm25;
            private List<PrecipitationBean> precipitation;
            private List<WindBean> wind;
            private List<TemperatureBean> temperature;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<SkyconBean> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBean> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBean> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBean> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBean> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBean> aqi) {
                this.aqi = aqi;
            }

            public List<HumidityBean> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBean> humidity) {
                this.humidity = humidity;
            }

            public List<PresBean> getPres() {
                return pres;
            }

            public void setPres(List<PresBean> pres) {
                this.pres = pres;
            }

            public List<Pm25Bean> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25Bean> pm25) {
                this.pm25 = pm25;
            }

            public List<PrecipitationBean> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationBean> precipitation) {
                this.precipitation = precipitation;
            }

            public List<WindBean> getWind() {
                return wind;
            }

            public void setWind(List<WindBean> wind) {
                this.wind = wind;
            }

            public List<TemperatureBean> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBean> temperature) {
                this.temperature = temperature;
            }

            public static class SkyconBean {
                /**
                 * value : PARTLY_CLOUDY_DAY
                 * datetime : 2018-08-10 17:00
                 */

                private String value;
                private String datetime;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class CloudrateBean {
                /**
                 * value : 0.3
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class AqiBean {
                /**
                 * value : 62
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class HumidityBean {
                /**
                 * value : 0.61
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class PresBean {
                /**
                 * value : 99523.6446458357
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class Pm25Bean {
                /**
                 * value : 25
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class PrecipitationBean {
                /**
                 * value : 0
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class WindBean {
                /**
                 * direction : 35.34
                 * speed : 19.85
                 * datetime : 2018-08-10 17:00
                 */

                private double direction;
                private double speed;
                private String datetime;

                public double getDirection() {
                    return direction;
                }

                public void setDirection(double direction) {
                    this.direction = direction;
                }

                public double getSpeed() {
                    return speed;
                }

                public void setSpeed(double speed) {
                    this.speed = speed;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class TemperatureBean {
                /**
                 * value : 34
                 * datetime : 2018-08-10 17:00
                 */

                private double value;
                private String datetime;

                public double getValue() {
                    return value;
                }

                public void setValue(double value) {
                    this.value = value;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }
        }

        public static class DailyBean {
            /**
             * status : ok
             * coldRisk : [{"index":"4","desc":"极易发","datetime":"2018-08-10"},{"index":"3","desc":"易发","datetime":"2018-08-11"},{"index":"3","desc":"易发","datetime":"2018-08-12"},{"index":"3","desc":"易发","datetime":"2018-08-13"},{"index":"3","desc":"易发","datetime":"2018-08-14"}]
             * skycon_20h_32h : [{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}]
             * temperature : [{"date":"2018-08-10","max":34,"avg":29.99,"min":26},{"date":"2018-08-11","max":31,"avg":28.54,"min":26},{"date":"2018-08-12","max":30.9,"avg":28.29,"min":26},{"date":"2018-08-13","max":30,"avg":27,"min":26},{"date":"2018-08-14","max":32,"avg":29.18,"min":27}]
             * skycon : [{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}]
             * cloudrate : [{"date":"2018-08-10","max":1,"avg":0.8,"min":0.3},{"date":"2018-08-11","max":1,"avg":1,"min":1},{"date":"2018-08-12","max":1,"avg":0.96,"min":0.78},{"date":"2018-08-13","max":1,"avg":1,"min":0.99},{"date":"2018-08-14","max":1,"avg":0.98,"min":0.89}]
             * aqi : [{"date":"2018-08-10","max":70,"avg":36,"min":30},{"date":"2018-08-11","max":35,"avg":25.42,"min":22},{"date":"2018-08-12","max":30,"avg":13.12,"min":8},{"date":"2018-08-13","max":39,"avg":21.25,"min":7},{"date":"2018-08-14","max":14,"avg":7.5,"min":7}]
             * comfort : [{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"3","desc":"热","datetime":"2018-08-11"},{"index":"0","desc":"闷热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"0","desc":"闷热","datetime":"2018-08-14"}]
             * humidity : [{"date":"2018-08-10","max":0.81,"avg":0.75,"min":0.52},{"date":"2018-08-11","max":0.84,"avg":0.75,"min":0.57},{"date":"2018-08-12","max":0.88,"avg":0.76,"min":0.55},{"date":"2018-08-13","max":0.93,"avg":0.9,"min":0.81},{"date":"2018-08-14","max":0.95,"avg":0.88,"min":0.79}]
             * astro : [{"date":"2018-08-10","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-11","sunset":{"time":"19:17"},"sunrise":{"time":"06:19"}},{"date":"2018-08-12","sunset":{"time":"19:16"},"sunrise":{"time":"06:20"}},{"date":"2018-08-13","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}},{"date":"2018-08-14","sunset":{"time":"19:15"},"sunrise":{"time":"06:20"}}]
             * pres : [{"date":"2018-08-10","max":99899.66,"avg":99569.56,"min":99515.56},{"date":"2018-08-11","max":99648.26,"avg":99475.6,"min":99235.97},{"date":"2018-08-12","max":99537.96,"avg":99379.13,"min":99226.85},{"date":"2018-08-13","max":99417.3,"avg":99262.42,"min":99118.35},{"date":"2018-08-14","max":99661.08,"avg":99293.56,"min":99131.19}]
             * ultraviolet : [{"index":"1","desc":"最弱","datetime":"2018-08-10"},{"index":"1","desc":"最弱","datetime":"2018-08-11"},{"index":"1","desc":"最弱","datetime":"2018-08-12"},{"index":"1","desc":"最弱","datetime":"2018-08-13"},{"index":"1","desc":"最弱","datetime":"2018-08-14"}]
             * pm25 : [{"date":"2018-08-10","max":50,"avg":21.86,"min":20},{"date":"2018-08-11","max":24,"avg":16.75,"min":14},{"date":"2018-08-12","max":20,"avg":7.67,"min":4},{"date":"2018-08-13","max":27,"avg":13.62,"min":3},{"date":"2018-08-14","max":8,"avg":3.38,"min":3}]
             * dressing : [{"index":"3","desc":"热","datetime":"2018-08-10"},{"index":"2","desc":"很热","datetime":"2018-08-11"},{"index":"2","desc":"很热","datetime":"2018-08-12"},{"index":"3","desc":"热","datetime":"2018-08-13"},{"index":"3","desc":"热","datetime":"2018-08-14"}]
             * carWashing : [{"index":"3","desc":"较不适宜","datetime":"2018-08-10"},{"index":"3","desc":"较不适宜","datetime":"2018-08-11"},{"index":"3","desc":"较不适宜","datetime":"2018-08-12"},{"index":"3","desc":"较不适宜","datetime":"2018-08-13"},{"index":"3","desc":"较不适宜","datetime":"2018-08-14"}]
             * precipitation : [{"date":"2018-08-10","max":1.1824,"avg":0.4609,"min":0},{"date":"2018-08-11","max":1.564,"avg":0.3479,"min":0},{"date":"2018-08-12","max":1.3592,"avg":0.4445,"min":0},{"date":"2018-08-13","max":12.645,"avg":4.3305,"min":0},{"date":"2018-08-14","max":1.4058,"avg":0.707,"min":0.1332}]
             * wind : [{"date":"2018-08-10","max":{"direction":24.76,"speed":23.94},"avg":{"direction":18.31,"speed":15.26},"min":{"direction":16.21,"speed":6.08}},{"date":"2018-08-11","max":{"direction":338.31,"speed":21.14},"avg":{"direction":346.42,"speed":12.25},"min":{"direction":30.05,"speed":2.3}},{"date":"2018-08-12","max":{"direction":32.62,"speed":19.9},"avg":{"direction":349.15,"speed":13.75},"min":{"direction":356.35,"speed":5.96}},{"date":"2018-08-13","max":{"direction":286.23,"speed":27.1},"avg":{"direction":282.74,"speed":22.19},"min":{"direction":264.69,"speed":14.08}},{"date":"2018-08-14","max":{"direction":176.49,"speed":28.95},"avg":{"direction":176.1,"speed":17.31},"min":{"direction":234.32,"speed":1.85}}]
             * skycon_08h_20h : [{"date":"2018-08-10","value":"RAIN"},{"date":"2018-08-11","value":"RAIN"},{"date":"2018-08-12","value":"RAIN"},{"date":"2018-08-13","value":"RAIN"},{"date":"2018-08-14","value":"RAIN"}]
             */

            private String status;
            private List<ColdRiskBean> coldRisk;
            private List<Skycon20h32hBean> skycon_20h_32h;
            private List<TemperatureBeanX> temperature;
            private List<SkyconBeanX> skycon;
            private List<CloudrateBeanX> cloudrate;
            private List<AqiBeanX> aqi;
            private List<ComfortBean> comfort;
            private List<HumidityBeanX> humidity;
            private List<AstroBean> astro;
            private List<PresBeanX> pres;
            private List<UltravioletBean> ultraviolet;
            private List<Pm25BeanX> pm25;
            private List<DressingBean> dressing;
            private List<CarWashingBean> carWashing;
            private List<PrecipitationBeanX> precipitation;
            private List<WindBeanX> wind;
            private List<Skycon08h20hBean> skycon_08h_20h;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ColdRiskBean> getColdRisk() {
                return coldRisk;
            }

            public void setColdRisk(List<ColdRiskBean> coldRisk) {
                this.coldRisk = coldRisk;
            }

            public List<Skycon20h32hBean> getSkycon_20h_32h() {
                return skycon_20h_32h;
            }

            public void setSkycon_20h_32h(List<Skycon20h32hBean> skycon_20h_32h) {
                this.skycon_20h_32h = skycon_20h_32h;
            }

            public List<TemperatureBeanX> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureBeanX> temperature) {
                this.temperature = temperature;
            }

            public List<SkyconBeanX> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconBeanX> skycon) {
                this.skycon = skycon;
            }

            public List<CloudrateBeanX> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateBeanX> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<AqiBeanX> getAqi() {
                return aqi;
            }

            public void setAqi(List<AqiBeanX> aqi) {
                this.aqi = aqi;
            }

            public List<ComfortBean> getComfort() {
                return comfort;
            }

            public void setComfort(List<ComfortBean> comfort) {
                this.comfort = comfort;
            }

            public List<HumidityBeanX> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityBeanX> humidity) {
                this.humidity = humidity;
            }

            public List<AstroBean> getAstro() {
                return astro;
            }

            public void setAstro(List<AstroBean> astro) {
                this.astro = astro;
            }

            public List<PresBeanX> getPres() {
                return pres;
            }

            public void setPres(List<PresBeanX> pres) {
                this.pres = pres;
            }

            public List<UltravioletBean> getUltraviolet() {
                return ultraviolet;
            }

            public void setUltraviolet(List<UltravioletBean> ultraviolet) {
                this.ultraviolet = ultraviolet;
            }

            public List<Pm25BeanX> getPm25() {
                return pm25;
            }

            public void setPm25(List<Pm25BeanX> pm25) {
                this.pm25 = pm25;
            }

            public List<DressingBean> getDressing() {
                return dressing;
            }

            public void setDressing(List<DressingBean> dressing) {
                this.dressing = dressing;
            }

            public List<CarWashingBean> getCarWashing() {
                return carWashing;
            }

            public void setCarWashing(List<CarWashingBean> carWashing) {
                this.carWashing = carWashing;
            }

            public List<PrecipitationBeanX> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationBeanX> precipitation) {
                this.precipitation = precipitation;
            }

            public List<WindBeanX> getWind() {
                return wind;
            }

            public void setWind(List<WindBeanX> wind) {
                this.wind = wind;
            }

            public List<Skycon08h20hBean> getSkycon_08h_20h() {
                return skycon_08h_20h;
            }

            public void setSkycon_08h_20h(List<Skycon08h20hBean> skycon_08h_20h) {
                this.skycon_08h_20h = skycon_08h_20h;
            }

            public static class ColdRiskBean {
                /**
                 * index : 4
                 * desc : 极易发
                 * datetime : 2018-08-10
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class Skycon20h32hBean {
                /**
                 * date : 2018-08-10
                 * value : RAIN
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class TemperatureBeanX {
                /**
                 * date : 2018-08-10
                 * max : 34
                 * avg : 29.99
                 * min : 26
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class SkyconBeanX {
                /**
                 * date : 2018-08-10
                 * value : RAIN
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class CloudrateBeanX {
                /**
                 * date : 2018-08-10
                 * max : 1
                 * avg : 0.8
                 * min : 0.3
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class AqiBeanX {
                /**
                 * date : 2018-08-10
                 * max : 70
                 * avg : 36
                 * min : 30
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class ComfortBean {
                /**
                 * index : 3
                 * desc : 热
                 * datetime : 2018-08-10
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class HumidityBeanX {
                /**
                 * date : 2018-08-10
                 * max : 0.81
                 * avg : 0.75
                 * min : 0.52
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class AstroBean {
                /**
                 * date : 2018-08-10
                 * sunset : {"time":"19:17"}
                 * sunrise : {"time":"06:19"}
                 */

                private String date;
                private SunsetBean sunset;
                private SunriseBean sunrise;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public SunsetBean getSunset() {
                    return sunset;
                }

                public void setSunset(SunsetBean sunset) {
                    this.sunset = sunset;
                }

                public SunriseBean getSunrise() {
                    return sunrise;
                }

                public void setSunrise(SunriseBean sunrise) {
                    this.sunrise = sunrise;
                }

                public static class SunsetBean {
                    /**
                     * time : 19:17
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }

                public static class SunriseBean {
                    /**
                     * time : 06:19
                     */

                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }
            }

            public static class PresBeanX {
                /**
                 * date : 2018-08-10
                 * max : 99899.66
                 * avg : 99569.56
                 * min : 99515.56
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class UltravioletBean {
                /**
                 * index : 1
                 * desc : 最弱
                 * datetime : 2018-08-10
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class Pm25BeanX {
                /**
                 * date : 2018-08-10
                 * max : 50
                 * avg : 21.86
                 * min : 20
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class DressingBean {
                /**
                 * index : 3
                 * desc : 热
                 * datetime : 2018-08-10
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class CarWashingBean {
                /**
                 * index : 3
                 * desc : 较不适宜
                 * datetime : 2018-08-10
                 */

                private String index;
                private String desc;
                private String datetime;

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }
            }

            public static class PrecipitationBeanX {
                /**
                 * date : 2018-08-10
                 * max : 1.1824
                 * avg : 0.4609
                 * min : 0
                 */

                private String date;
                private double max;
                private double avg;
                private double min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public double getMax() {
                    return max;
                }

                public void setMax(double max) {
                    this.max = max;
                }

                public double getAvg() {
                    return avg;
                }

                public void setAvg(double avg) {
                    this.avg = avg;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * date : 2018-08-10
                 * max : {"direction":24.76,"speed":23.94}
                 * avg : {"direction":18.31,"speed":15.26}
                 * min : {"direction":16.21,"speed":6.08}
                 */

                private String date;
                private MaxBean max;
                private AvgBean avg;
                private MinBean min;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxBean getMax() {
                    return max;
                }

                public void setMax(MaxBean max) {
                    this.max = max;
                }

                public AvgBean getAvg() {
                    return avg;
                }

                public void setAvg(AvgBean avg) {
                    this.avg = avg;
                }

                public MinBean getMin() {
                    return min;
                }

                public void setMin(MinBean min) {
                    this.min = min;
                }

                public static class MaxBean {
                    /**
                     * direction : 24.76
                     * speed : 23.94
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class AvgBean {
                    /**
                     * direction : 18.31
                     * speed : 15.26
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }

                public static class MinBean {
                    /**
                     * direction : 16.21
                     * speed : 6.08
                     */

                    private double direction;
                    private double speed;

                    public double getDirection() {
                        return direction;
                    }

                    public void setDirection(double direction) {
                        this.direction = direction;
                    }

                    public double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(double speed) {
                        this.speed = speed;
                    }
                }
            }

            public static class Skycon08h20hBean {
                /**
                 * date : 2018-08-10
                 * value : RAIN
                 */

                private String date;
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class MinutelyBean {
            /**
             * status : ok
             * description : 附近正在下雨，出门还是带把伞吧~
             * probability : [0,0,0,0]
             * datasource : radar
             * precipitation_2h : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             * precipitation : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
             */

            private String status;
            private String description;
            private String datasource;
            private List<Double> probability;
            private List<Double> precipitation_2h;
            private List<Double> precipitation;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDatasource() {
                return datasource;
            }

            public void setDatasource(String datasource) {
                this.datasource = datasource;
            }

            public List<Double> getProbability() {
                return probability;
            }

            public void setProbability(List<Double> probability) {
                this.probability = probability;
            }

            public List<Double> getPrecipitation_2h() {
                return precipitation_2h;
            }

            public void setPrecipitation_2h(List<Double> precipitation_2h) {
                this.precipitation_2h = precipitation_2h;
            }

            public List<Double> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<Double> precipitation) {
                this.precipitation = precipitation;
            }
        }
    }
}
