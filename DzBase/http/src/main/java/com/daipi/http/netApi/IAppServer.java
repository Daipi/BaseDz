package com.daipi.http.netApi;

import com.daipi.base.base.BaseEntity;
import com.daipi.http.common.data.ResultData;
import com.daipi.http.netApi.test.ProjectType;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IAppServer {
    @GET(AppUrlConstant.GET_PROJECT)
    Observable<BaseEntity<List<ProjectType>>> getProject();

    @GET(AppUrlConstant.GET_PROJECT)
    Observable<ResultData<List<ProjectType>>> getProject2();
}
