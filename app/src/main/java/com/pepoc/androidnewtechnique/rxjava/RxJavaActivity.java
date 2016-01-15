package com.pepoc.androidnewtechnique.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxJavaActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);

        Class<? extends RxJavaActivity> aClass = RxJavaActivity.this.getClass();
        Observable.from(getButtons()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Button button = (Button) View.inflate(RxJavaActivity.this, R.layout.button, null);
                llContent.addView(button);
                button.setText(s);
                RxView.clicks(button).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        try {
                            Method declaredMethod = aClass.getDeclaredMethod(s);
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(RxJavaActivity.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private List<String> getButtons() {
        List<String> textContents = new ArrayList<>();
        textContents.add("create");
        textContents.add("flatMap");
        textContents.add("concatMap");
        textContents.add("switchMap");
        textContents.add("delay");
        textContents.add("timer");
        textContents.add("interval");
        textContents.add("range");
        textContents.add("repeat");
        textContents.add("repeatWhen");
        textContents.add("buffer");
        return textContents;
    }

    private void create() {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("LALALA");
                subscriber.onNext("HAHAHA");
                subscriber.onNext("HEHEHE");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogManager.i("---------onCompleted--------- ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogManager.i("---------onNext--------- " + s);
            }
        });
    }

    /**
     * flatMap操作符是把Observable产生的结果转换成多个Observable，然后把这多个Observable“扁平化”成一个Observable，
     * 并依次提交产生的结果给订阅者。
     * flatMap操作符通过传入一个函数作为参数转换源Observable，
     * 在这个函数中，你可以自定义转换规则，最后在这个函数中返回一个新的Observable，
     * 然后flatMap操作符通过合并这些Observable结果成一个Observable，并依次提交结果给订阅者。
     * 值得注意的是，flatMap操作符在合并Observable结果时，有可能存在交叉的情况
     */
    private void flatMap() {
        Observable.just(10, 20, 30).flatMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.from(new Integer[]{integer, integer / 2}).delay(1, TimeUnit.SECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                LogManager.i("------------onStart------------");
            }

            @Override
            public void onCompleted() {
                LogManager.i("------------onCompleted------------");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogManager.i("flatMap Next : " + integer);
            }
        });
    }

    /**
     * cancatMap操作符与flatMap操作符类似，都是把Observable产生的结果转换成多个Observable，
     * 然后把这多个Observable“扁平化”成一个Observable，并依次提交产生的结果给订阅者。
     * 与flatMap操作符不同的是，concatMap操作符在处理产生的Observable时，
     * 采用的是“连接(concat)”的方式，而不是“合并(merge)”的方式，这就能保证产生结果的顺序性，
     * 也就是说提交给订阅者的结果是按照顺序提交的，不会存在交叉的情况。
     */
    private void concatMap() {
        Observable.just(10, 20, 30).concatMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.from(new Integer[]{integer, integer / 2}).delay(1, TimeUnit.SECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                LogManager.i("------------onStart------------");
            }

            @Override
            public void onCompleted() {
                LogManager.i("------------onCompleted------------");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogManager.i("concatMap Next : " + integer);
            }
        });
    }

    /**
     * switchMap操作符与flatMap操作符类似，都是把Observable产生的结果转换成多个Observable，
     * 然后把这多个Observable“扁平化”成一个Observable，并依次提交产生的结果给订阅者。
     * 与flatMap操作符不同的是，switchMap操作符会保存最新的Observable产生的结果而舍弃旧的结果，
     * 举个例子来说，比如源Observable产生A、B、C三个结果，通过switchMap的自定义映射规则，
     * 映射后应该会产生A1、A2、B1、B2、C1、C2，但是在产生B2的同时，C1已经产生了，这样最后的结果就变成A1、A2、B1、C1、C2，B2被舍弃掉了！
     */
    private void switchMap() {
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                return Observable.from(new Integer[]{integer, integer / 2}).delay(1, TimeUnit.SECONDS);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                LogManager.i("------------onStart------------");
            }

            @Override
            public void onCompleted() {
                LogManager.i("------------onCompleted------------");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogManager.i("switchMap Next : " + integer);
            }
        });
    }

    /**
     * 延时执行
     */
    private void delay() {
        Observable.just("LALALA", "HAHAHA", "GAGAGA")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        LogManager.i("------------onStart------------");
                    }

                    @Override
                    public void onCompleted() {
                        LogManager.i("------------onCompleted------------");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogManager.i(s);
                    }
                });
    }

    /**
     * 延时执行任务
     */
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onStart() {
                super.onStart();
                LogManager.i("--------------------onStart-----------------------");
            }

            @Override
            public void onCompleted() {
                LogManager.i("--------------------onCompleted-----------------------");
            }

            @Override
            public void onError(Throwable e) {
                LogManager.i("--------------------onError-----------------------");
            }

            @Override
            public void onNext(Long aLong) {
                LogManager.i("--------------------onNext-----------------------");
            }
        });
    }

    /**
     * 每隔一段时间循环执行任务
     */
    private void interval() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                LogManager.i("----------interval-------- " + aLong);
            }
        });
    }

    /**
     * 创建一组在从n开始，个数为m的连续数字，比如range(3,10)，就是创建3、4、5…12的一组数字
     */
    private void range() {
        Observable.range(5, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                LogManager.i("----------------range------------------ " + integer.toString());
            }
        });
    }

    /**
     * repeat操作符是对某一个Observable，重复产生多次结果
     * repeat和repeatWhen操作符默认情况下是运行在一个新线程上的，当然你可以通过传入参数来修改其运行的线程。
     */
    private void repeat() {
        Observable.range(3, 3).repeat(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                LogManager.i("---------------repeat---------------- " + integer.toString());
            }
        });
    }

    /**
     * repeatWhen操作符是对某一个Observable，有条件地重新订阅从而产生多次结果
     *
     * @see {@link #repeat()}
     */
    private void repeatWhen() {
        Observable.just(1, 2, 3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                LogManager.i("---------call----------");
                return observable.timer(2, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                LogManager.i("--------------call-------------- " + integer.toString());
            }
        });
    }

    /**
     * buffer操作符周期性地收集源Observable产生的结果到列表中，并把这个列表提交给订阅者
     */
    private void buffer() {
        Observable.timer(1, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "YYYYYYY";
                    }
                })
                .repeat(10)
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        LogManager.i("*********************************");
                        LogManager.i(strings.toString());
                        LogManager.i("*********************************");
                    }
                });
    }

}
