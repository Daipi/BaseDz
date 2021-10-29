package com.daipi.http.netApi;


import com.daipi.base.utils.RxUtil;
import com.daipi.http.AppRetrofitManager;
import com.daipi.http.EntityObserver;
import com.daipi.http.ResultObserver;
import com.daipi.http.netApi.test.ProjectType;


import java.util.ArrayList;
import java.util.List;

/**
 * author:daijs
 * time:2021/10/19 11:43
 * details:业务网络请求实现
 */
public class NetWorkApi {
    private NetWorkApi(){ }

    public static NetWorkApi getInstance(){
        return NetWorkApi.NetWorkApiInstance.instance;
    }

    private static class NetWorkApiInstance{
        private static final NetWorkApi instance= new NetWorkApi();
    }

    ///////////////test
    public void getProject(EntityObserver<List<ProjectType>> observer) {
        AppRetrofitManager.getApiService()
                .getProject()
                .compose(RxUtil.INSTANCE.applyObservableAsync())
                .safeSubscribe(observer);
    }

    public List<ProjectType> getProject2() {
        List<ProjectType> list = new ArrayList<ProjectType>();
        AppRetrofitManager.getApiService()
                .getProject2()
                .safeSubscribe(new ResultObserver<List<ProjectType>>(){
                    @Override
                    public void onSuccess(List<ProjectType> entity) {
                        list.addAll(entity);
                    }
                });
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
    ////////////////


}
