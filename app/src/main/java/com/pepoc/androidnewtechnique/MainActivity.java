package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.pepoc.androidnewtechnique.customview.moment.AnnulusViewActivity;
import com.pepoc.androidnewtechnique.customview.pulltorefresh.PullToRefreshActivity;
import com.pepoc.androidnewtechnique.customview.safemobile.SafeTestActivity;
import com.pepoc.androidnewtechnique.date.DateTestActivity;
import com.pepoc.androidnewtechnique.eventbus.EventBusActivity;
import com.pepoc.androidnewtechnique.file.FileActivity;
import com.pepoc.androidnewtechnique.fragment.FragmentDemoActivity;
import com.pepoc.androidnewtechnique.fragment.SingleTaskDemoActivity;
import com.pepoc.androidnewtechnique.handler.HandlerDemoActivity;
import com.pepoc.androidnewtechnique.java.JavaBaseActivity;
import com.pepoc.androidnewtechnique.jni.JniActivity;
import com.pepoc.androidnewtechnique.keyboardinput.EditTextDemoActivity;
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
import com.pepoc.androidnewtechnique.services.foreground.ForegroundService;
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
import com.pepoc.androidnewtechnique.util.RSAManager;
import com.pepoc.androidnewtechnique.viewstub.ViewStubDemoActivity;
import com.pepoc.androidnewtechnique.widget.WidgetActivity;
import com.pepoc.androidnewtechnique.wifi.WifiTestActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends Activity {

    Toolbar toolbar;
    LinearLayout llMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        init();

//        LogManager.i(Environment.getExternalStorageDirectory().getPath());
//
//        ClassLoader loader = MainActivity.class.getClassLoader();
//        while (loader != null) {
//            Log.d("classloader",loader.toString());//1
//            loader = loader.getParent();
//        }


//        for (Class<?> clazz = this.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
//            try {
//                Field mTokenField = clazz.getDeclaredField("mToken");
//
//                mTokenField.setAccessible(true);
//                Object o = mTokenField.get(this);
//                LogManager.i("fix", o == null ? "null" : o.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        try {
//            final Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
//            Method mGetInstance = resourcesManagerClass.getDeclaredMethod("getInstance");
//
//            if (!mGetInstance.isAccessible()) {
//                mGetInstance.setAccessible(true);
//            }
//
//            Method activityResources = resourcesManagerClass.getDeclaredMethod("getOrCreateActivityResourcesStructLocked");
//            if (!activityResources.isAccessible()) {
//                activityResources.setAccessible(true);
//            }
//
//            Object resourcesManager = mGetInstance.invoke(null);
//
//            Object invoke = activityResources.invoke(resourcesManager);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        MavenTest.mavenTest();





        BatteryManager manager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
        manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
        int capacity = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);///当前电量百分比
        LogManager.i("CAPACITY = " + capacity);



        Intent batteryBroadcast = registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        // 0 means we are discharging, anything else means charging
        boolean isCharging = batteryBroadcast.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) != 0;
        Toast.makeText(this, "isCharging = " + isCharging, Toast.LENGTH_SHORT).show();
        LogManager.i("isCharging = " + isCharging);


        Locale locale=getResources().getConfiguration().locale;
        Log.i("Language", locale.getLanguage());

        TextView tv = new TextView(this);

        Toast.makeText(this,"默认字体大小为："+tv.getTextSize(),Toast.LENGTH_LONG).show();

//        Configuration mCurConfig = new Configuration();


        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        Toast.makeText(this, "StatusHeight = " + resources.getDimensionPixelSize(resourceId), Toast.LENGTH_SHORT).show();

    }

    private void init() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
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

//        try {
//            Class<?> classH = Class.forName("android.app.ActivityThread$H");
//            Field field_SCHEDULE_CRASH = classH.getDeclaredField("SCHEDULE_CRASH");
//            LogManager.i("SCHEDULE_CRASH", field_SCHEDULE_CRASH.getInt(null) + "");
//            Toast.makeText(this, "SCHEDULE_CRASH = " + field_SCHEDULE_CRASH.getInt(null), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//        try {
//
////            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
//            Class<?> ActivityThread = Class.forName("android.app.ActivityThread");
//            Method method = ActivityThread.getMethod("currentActivityThread");
//            Object currentActivityThread = method.invoke(ActivityThread);
//            Method getHandler = currentActivityThread.getClass().getDeclaredMethod("getHandler");
//            getHandler.setAccessible(true);
//            Object mH = getHandler.invoke(currentActivityThread);//获取mH handler 对象
//            Field mCallback = Class.forName("android.os.Handler").getDeclaredField("mCallback");
//            mCallback.setAccessible(true);
//            HandlerCallback handlerCallback = new HandlerCallback();
//            mCallback.set(mH, handlerCallback);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        startService(new Intent(this, ForegroundService.class));

        String sign = "L5eVgc2Itpf2/Bm584Td+7nuGOs9vNqUWk6sWSgp6/zfiYLx0o0XCtOQejv80sZg/r2+CdMzwGeM0Zauqzq8jN2s7fVHW12W6ScBgnZfXIL+lsOuhpIc3YMVejpOQlTRTctewAL8JJO9v69KKPV6dToKqnLbSlGGpuRdEIcSpbQ=";
        String fileMd5 = RSAManager.decryptTest(this, sign);
        Log.i("File_MD5", "fileMd5 = " + fileMd5);
    }

    class HandlerCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            try {
                //C84409BEFE0C4FAC2539C8CC121DC5EA 崩溃
                //在这里拦截错误 SCHEDULE_CRASH  首先判断是否是这个错误,然后判断是不是常驻的,常驻通知栏的话拦截错误并刷新常驻通知栏
                if (msg.what == 100) {
                    Toast.makeText(MainActivity.this, "LAUNCH_ACTIVITY = 100", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private List<Class<? extends Activity>> getData() {
        List<Class<? extends Activity>> classList = new ArrayList<>();
        classList.add(EditTextDemoActivity.class);
        classList.add(AnnulusViewActivity.class);
        classList.add(DateTestActivity.class);
        classList.add(BezierCurveActivity.class);
        classList.add(CanvasDemoActivity.class);
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
        classList.add(WidgetActivity.class);
        classList.add(ScrollerActivity.class);
        classList.add(MatrixActivity.class);
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
