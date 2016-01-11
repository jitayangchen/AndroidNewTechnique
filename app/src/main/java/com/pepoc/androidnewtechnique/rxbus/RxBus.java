package com.pepoc.androidnewtechnique.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yangchen on 16-1-11.
 */
public class RxBus {

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object obj) {
        _bus.onNext(obj);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }
}
