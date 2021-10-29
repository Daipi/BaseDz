package com.daipi.base.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

/**
 * author:daijs
 * time:2021/10/18 16:11
 * details:RX线程切换
 */
object RxUtil {
    fun <T> applyThread(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable: Observable<T> ->
            observable.subscribeOn(
                Schedulers.io()
            )
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}