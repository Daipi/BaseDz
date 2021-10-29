package com.daipi.http;

import com.daipi.base.utils.LogUtil;
import com.daipi.http.common.data.ResultData;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ResultObserver<T> implements Observer<ResultData<T>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResultData<T> o) {
        //WebSocketUtil.getInstance().setLock(false);
        //if (o == null || (200 != o.getCode())) {
        if (o == null || (0 != o.getCode())) {
            onFail(o.getMessage());
            onFail(o.getCode(), o.getMessage());
            if (o!=null&&o.getCode() == 401){

            }
        }else {
            Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (o.getData() == null && t.toString().equals(String.class.toString())) {
                onSuccess((T) o.getMessage());
            } else {
                onSuccess(o.getData());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
        onFail(e.hashCode(), e.getMessage());
    }

    @Override
    public void onComplete() {
        LogUtil.d("Observer", "onComplete");
    }

    public abstract void onSuccess(T entity);

    public void onFail(String msg) {

    }

    public void onFail(int code, String msg) {

    }
}
