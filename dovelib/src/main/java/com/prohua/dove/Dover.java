package com.prohua.dove;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 收信者 Receiver further encapsulation
 * Created by Deep on 2018/3/14 0014.
 */

public abstract class Dover<T> implements Observer<T> {

    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        this.disposable = disposable;
        call(disposable);
    }

    @Override
    public void onNext(@NonNull T t) {
        don(disposable, t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        die(disposable, e);
    }

    @Override
    public void onComplete() {
        end(disposable);
    }

    /**
     * 提醒
     *
     * @param d Disposable 用于解除订阅
     */
    public abstract void call(@NonNull Disposable d);

    /**
     * 收到
     *
     * @param t 数据
     */
    public abstract void don(Disposable d, @NonNull T t);

    /**
     * 中途断了
     *
     * @param e 异常信息
     */
    public abstract void die(Disposable d, @NonNull Throwable throwable);

    /**
     * 确认
     */
    public abstract void end(Disposable d);

}
