package com.daipi.http;

import com.daipi.http.netApi.IAppServer;

public class AppRetrofitManager {

    private AppRetrofitManager(){}

    private static IAppServer server = RetrofitManager.INSTANCE.get().create(IAppServer.class);

    public static IAppServer getApiService() {
        return server;
    }
}
