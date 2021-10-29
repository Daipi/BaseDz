package com.daipi.http;

import com.daipi.base.base.BaseEntity;
import com.daipi.base.utils.LogUtil;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class EntityObserver<T> implements Observer<BaseEntity<T>> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseEntity<T> o) {
        //WebSocketUtil.getInstance().setLock(false);
        if (o == null || (200 != o.code && !o.success)) {
            onFail(o.msg);
            onFail(o.code, o.msg);
            if (o!=null&&o.code == 401){

            }
        }else {
            Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (o.data == null && t.toString().equals(String.class.toString())) {
                onSuccess((T) o.msg);
            } else {
                onSuccess(o.data);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        //WebSocketUtil.getInstance().setLock(false);
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
