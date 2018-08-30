package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.AppIsBackground.AppGroundActivity;
import com.pepoc.androidnewtechnique.SpannableString.SpannableStringActivity;
import com.pepoc.androidnewtechnique.activitytask.ATestActivity;
import com.pepoc.androidnewtechnique.andfix.AndfixActivity;
import com.pepoc.androidnewtechnique.androidbase.RotateActivity;
import com.pepoc.androidnewtechnique.asynctask.AsyncTaskDemoActivity;
import com.pepoc.androidnewtechnique.beziercurve.BezierCurveActivity;
import com.pepoc.androidnewtechnique.bitmap.BitmapDemoActivity;
import com.pepoc.androidnewtechnique.camera.CameraDemoActivity;
import com.pepoc.androidnewtechnique.clipboard.ClipboardDemoActivity;
import com.pepoc.androidnewtechnique.collection.BlockingQueueActivity;
import com.pepoc.androidnewtechnique.constraintlayout.ConstraintLayoutDemoActivity;
import com.pepoc.androidnewtechnique.customview.CanvasDemoActivity;
import com.pepoc.androidnewtechnique.customview.SlideViewActivity;
import com.pepoc.androidnewtechnique.customview.animation.AnimationDemoActivity;
import com.pepoc.androidnewtechnique.customview.draw.DrawMothedDemoActivity;
import com.pepoc.androidnewtechnique.customview.pulltorefresh.PullToRefreshActivity;
import com.pepoc.androidnewtechnique.customview.safemobile.SafeTestActivity;
import com.pepoc.androidnewtechnique.eventbus.EventBusActivity;
import com.pepoc.androidnewtechnique.file.FileActivity;
import com.pepoc.androidnewtechnique.fragment.FragmentDemoActivity;
import com.pepoc.androidnewtechnique.fragment.SingleTaskDemoActivity;
import com.pepoc.androidnewtechnique.handler.HandlerDemoActivity;
import com.pepoc.androidnewtechnique.java.JavaBaseActivity;
import com.pepoc.androidnewtechnique.jni.JniActivity;
import com.pepoc.androidnewtechnique.keyboardinput.KeyBoardInputActivity;
import com.pepoc.androidnewtechnique.leakcanary.LeakCanaryTestActivity;
import com.pepoc.androidnewtechnique.listview.RecyclerViewDemoActivity;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.matrix.MatrixActivity;
import com.pepoc.androidnewtechnique.memoryleak.MemoryLeakActivity;
import com.pepoc.androidnewtechnique.ocr.OcrDemoActivity;
import com.pepoc.androidnewtechnique.okhttp.OkHttpActivity;
import com.pepoc.androidnewtechnique.okhttp.retrofit.RetrofitActivity;
import com.pepoc.androidnewtechnique.process.ProcessDemoActivity;
import com.pepoc.androidnewtechnique.realm.RealmActivity;
import com.pepoc.androidnewtechnique.recyclerview.RecyclerViewActivity;
import com.pepoc.androidnewtechnique.recyclerview.snaphelper.RemoveSnapActivity;
import com.pepoc.androidnewtechnique.recyclerview.viewlistener.ViewListenerActivity;
import com.pepoc.androidnewtechnique.rxbinding.RxBindingActivity;
import com.pepoc.androidnewtechnique.rxbus.RxBusActivity;
import com.pepoc.androidnewtechnique.rxjava.RxJavaActivity;
import com.pepoc.androidnewtechnique.screenlocker.ScreenLockerActivity;
import com.pepoc.androidnewtechnique.scroller.ScrollerActivity;
import com.pepoc.androidnewtechnique.services.ServiceDemoActivity;
import com.pepoc.androidnewtechnique.services.foreground.ForegroundServiceDemoActivity;
import com.pepoc.androidnewtechnique.services.notification.NotificationListenerActivity;
import com.pepoc.androidnewtechnique.sqlite.SqliteDemoActivity;
import com.pepoc.androidnewtechnique.svg.SVGActivity;
import com.pepoc.androidnewtechnique.systemlanuage.SystemLanuageActivity;
import com.pepoc.androidnewtechnique.textview.TextViewDemoActivity;
import com.pepoc.androidnewtechnique.threadpool.ThreadPollDemoActivity;
import com.pepoc.androidnewtechnique.timer.TimerDemoActivity;
import com.pepoc.androidnewtechnique.touchevent.TouchEventDemoActivity;
import com.pepoc.androidnewtechnique.util.HomeListenerHelper;
import com.pepoc.androidnewtechnique.viewstub.ViewStubDemoActivity;
import com.pepoc.androidnewtechnique.widget.WidgetActivity;
import com.pepoc.androidnewtechnique.wifi.WifiTestActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout llMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        LogManager.i(Environment.getExternalStorageDirectory().getPath());

        ClassLoader loader = MainActivity.class.getClassLoader();
        while (loader != null) {
            Log.d("classloader",loader.toString());//1
            loader = loader.getParent();
        }


        for (Class<?> clazz = this.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field mTokenField = clazz.getDeclaredField("mToken");

                mTokenField.setAccessible(true);
                Object o = mTokenField.get(this);
                LogManager.i("fix", o == null ? "null" : o.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            final Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
            Method mGetInstance = resourcesManagerClass.getDeclaredMethod("getInstance");

            if (!mGetInstance.isAccessible()) {
                mGetInstance.setAccessible(true);
            }

            Method activityResources = resourcesManagerClass.getDeclaredMethod("getOrCreateActivityResourcesStructLocked");
            if (!activityResources.isAccessible()) {
                activityResources.setAccessible(true);
            }

            Object resourcesManager = mGetInstance.invoke(null);

            Object invoke = activityResources.invoke(resourcesManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llMainContent = (LinearLayout) findViewById(R.id.ll_main_content);

        Observable.from(getData()).subscribe(new Action1<Class<? extends Activity>>() {
            @Override
            public void call(final Class<? extends Activity> aClass) {
                Button button = (Button) View.inflate(MainActivity.this, R.layout.button, null);
                button.setText(aClass.getSimpleName());
                llMainContent.addView(button);
                RxView.clicks(button).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this, aClass);
                        if (ProcessDemoActivity.class.equals(aClass)) {
                            intent.putExtra("arg", "Yangchen");
                        }
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private List<Class<? extends Activity>> getData() {
        List<Class<? extends Activity>> classList = new ArrayList<>();
        classList.add(SafeTestActivity.class);
        classList.add(RetrofitActivity.class);
        classList.add(RemoveSnapActivity.class);
        classList.add(ATestActivity.class);
        classList.add(ViewListenerActivity.class);
        classList.add(ConstraintLayoutDemoActivity.class);
        classList.add(MemoryLeakActivity.class);
        classList.add(SingleTaskDemoActivity.class);
        classList.add(AnimationDemoActivity.class);
        classList.add(DrawMothedDemoActivity.class);
        classList.add(CameraDemoActivity.class);
        classList.add(SystemLanuageActivity.class);
        classList.add(OcrDemoActivity.class);
        classList.add(ForegroundServiceDemoActivity.class);
        classList.add(ClipboardDemoActivity.class);
        classList.add(PullToRefreshActivity.class);
        classList.add(LeakCanaryTestActivity.class);
        classList.add(WifiTestActivity.class);
        classList.add(RecyclerViewActivity.class);
        classList.add(SlideViewActivity.class);
        classList.add(NotificationListenerActivity.class);
        classList.add(ScreenLockerActivity.class);
        classList.add(RotateActivity.class);
        classList.add(ViewStubDemoActivity.class);
        classList.add(JavaBaseActivity.class);
        classList.add(BlockingQueueActivity.class);
        classList.add(FileActivity.class);
        classList.add(AppGroundActivity.class);
        classList.add(BitmapDemoActivity.class);
        classList.add(BezierCurveActivity.class);
        classList.add(WidgetActivity.class);
        classList.add(ScrollerActivity.class);
        classList.add(MatrixActivity.class);
        classList.add(CanvasDemoActivity.class);
        classList.add(EventBusActivity.class);
        classList.add(RealmActivity.class);
        classList.add(RxBindingActivity.class);
        classList.add(RxBusActivity.class);
        classList.add(RxJavaActivity.class);
        classList.add(JniActivity.class);
        classList.add(SpannableStringActivity.class);
        classList.add(AndfixActivity.class);
        classList.add(ServiceDemoActivity.class);
        classList.add(OkHttpActivity.class);
        classList.add(ThreadPollDemoActivity.class);
        classList.add(TouchEventDemoActivity.class);
        classList.add(HandlerDemoActivity.class);
        classList.add(SqliteDemoActivity.class);
        classList.add(AsyncTaskDemoActivity.class);
        classList.add(FragmentDemoActivity.class);
        classList.add(ProcessDemoActivity.class);
        classList.add(RecyclerViewDemoActivity.class);
        classList.add(TextViewDemoActivity.class);
        classList.add(SVGActivity.class);
        classList.add(TimerDemoActivity.class);
        classList.add(KeyBoardInputActivity.class);
        return classList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        HomeListenerHelper.getInstance().registCallback(homeCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LogManager.i("=========MainActivity onPause=========");
    }

    @Override
    protected void onStop() {
        super.onStop();
        HomeListenerHelper.getInstance().unregistCallback(homeCallback);
    }

    HomeListenerHelper.HomeCallback homeCallback = new HomeListenerHelper.HomeCallback() {
        @Override
        public void onHomeClick() {
            LogManager.i("=========MainActivity onHomeClick=========");
//            Toast.makeText(MainActivity.this, "MainActivity onHomeClick", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogManager.i("=========onUserLeaveHint***********");
    }
}
