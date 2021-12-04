package com.daipi.http.netApi

import com.daipi.http.common.data.ResultData
import com.daipi.http.netApi.test.ProjectType
import retrofit2.http.GET

interface IAppServerKt {
    @GET("project/tree/json")
    suspend fun getProject3(): ResultData<List<ProjectType>>
}