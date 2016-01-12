package com.pepoc.androidnewtechnique.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;
import com.pepoc.androidnewtechnique.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
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
        textContents.add("timer");
        textContents.add("interval");
        textContents.add("range");
        textContents.add("repeat");
        textContents.add("repeatWhen");
        textContents.add("buffer");
        return textContents;
    }

    /**
     * 延时执行任务
     */
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onStart() {
                super.onStart();
                Logger.i("--------------------onStart-----------------------");
            }

            @Override
            public void onCompleted() {
                Logger.i("--------------------onCompleted-----------------------");
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("--------------------onError-----------------------");
            }

            @Override
            public void onNext(Long aLong) {
                Logger.i("--------------------onNext-----------------------");
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
                Logger.i("----------interval-------- " + aLong);
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
                Logger.i("----------------range------------------ " + integer.toString());
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
                Logger.i("---------------repeat---------------- " + integer.toString());
            }
        });
    }

    /**
     * repeatWhen操作符是对某一个Observable，有条件地重新订阅从而产生多次结果
     * @see {@link #repeat()}
     */
    private void repeatWhen() {
        Observable.just(1, 2, 3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                Logger.i("---------call----------");
                return observable.timer(2, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.i("--------------call-------------- " + integer.toString());
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
                .buffer(3, TimeUnit.SECONDS)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        Log.i("YY", "*********************************");
                        Logger.i(strings.toString());
                        Log.i("YY", "*********************************");
                    }
                });
    }

}
