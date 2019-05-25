package com.xtkj.paopaoxiche.callback;

import com.xtkj.paopaoxiche.bean.WashTimeBean;

public interface WashTimeCallback {
    void confirmWashTime(WashTimeBean startTime, WashTimeBean endTime);
}
