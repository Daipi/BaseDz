package com.daipi.http.netApi.test

import com.daipi.http.common.data.ResultData
import java.io.Serializable

/**
 * author:daijs
 * time:2021/10/19 16:07
 * details:
 */
class ProjectType : ResultData<Any?>(), Serializable {
    /**
     * children : []
     * courseId : 13
     * id : 294
     * name : 完整项目
     * order : 145000
     * parentChapterId : 293
     * userControlSetTop : false
     * visible : 0
     */
    var courseId = 0
    var id = 0
    var name: String? = null
    var order = 0
    var parentChapterId = 0
    var userControlSetTop = false
    var visible = 0
    var children: List<*>? = null
}