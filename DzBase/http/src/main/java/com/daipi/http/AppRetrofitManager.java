package com.daipi.http;

import com.daipi.http.netApi.IAppServer;
import com.daipi.http.netApi.IAppServerKt;

public class AppRetrofitManager {

    private AppRetrofitManager(){}

    private static IAppServer server = RetrofitManager.INSTANCE.get().create(IAppServer.class);

    public static IAppServer getApiService() {
        return server;
    }

    private static IAppServerKt serverKt = RetrofitManager.INSTANCE.get().create(IAppServerKt.class);

    public static IAppServerKt getApiServiceKt() {
        return serverKt;
    }
}
