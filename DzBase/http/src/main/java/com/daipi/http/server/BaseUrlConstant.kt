package com.daipi.http.server


object BaseUrlConstant {
    @JvmField
    val URL = UrlConstant.SERVER + ""

    const val SMS_CODE_VERIFY = "SMS_CODE_VERIFY"
    const val SMS_CODE_GET = "SMS_CODE_GET"

    const val BASE_GET_GAME_HISTORY = "play/getHistoryPlays"
    const val BASE_GET_GAME_DETAIL = "play/getProject"

    const val BASE_STATISTICS_PERSONAL_ACCESS = "play/access/saveUserConeterAccess"
    const val BASE_STATISTICS_PRODUCT_ACCESS = "play/access/saveProductAccess"
    const val BASE_STATISTICS_PROJECT_ACCESS = "play/access/saveProjectAccess"
    const val BASE_STATISTICS_PLAY_DESC = "play/access/savePlayDesc"
    const val BASE_STATISTICS_PLAY_HISTORY = "play/access/savePlayHisAccess"
}
