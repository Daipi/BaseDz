package com.daipi.trafficcheck;

import android.app.Activity;

public interface TrafficListener {
    //获取流量
    void getTrafficStats(Activity activity, long value);
}
