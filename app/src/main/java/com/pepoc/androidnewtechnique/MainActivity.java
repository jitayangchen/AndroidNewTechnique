package com.pepoc.androidnewtechnique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.pepoc.androidnewtechnique.SpannableString.SpannableStringActivity;
import com.pepoc.androidnewtechnique.andfix.AndfixActivity;
import com.pepoc.androidnewtechnique.asynctask.AsyncTaskDemoActivity;
import com.pepoc.androidnewtechnique.beziercurve.BezierCurveActivity;
import com.pepoc.androidnewtechnique.customview.CanvasDemoActivity;
import com.pepoc.androidnewtechnique.eventbus.EventBusActivity;
import com.pepoc.androidnewtechnique.fragment.FragmentDemoActivity;
import com.pepoc.androidnewtechnique.handler.HandlerDemoActivity;
import com.pepoc.androidnewtechnique.jni.JniActivity;
import com.pepoc.androidnewtechnique.listview.RecyclerViewDemoActivity;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.matrix.MatrixActivity;
import com.pepoc.androidnewtechnique.okhttp.OkHttpActivity;
import com.pepoc.androidnewtechnique.process.ProcessDemoActivity;
import com.pepoc.androidnewtechnique.realm.RealmActivity;
import com.pepoc.androidnewtechnique.rxbinding.RxBindingActivity;
import com.pepoc.androidnewtechnique.rxbus.RxBusActivity;
import com.pepoc.androidnewtechnique.rxjava.RxJavaActivity;
import com.pepoc.androidnewtechnique.scroller.ScrollerActivity;
import com.pepoc.androidnewtechnique.services.ServiceDemoActivity;
import com.pepoc.androidnewtechnique.sqlite.SqliteDemoActivity;
import com.pepoc.androidnewtechnique.svg.SVGActivity;
import com.pepoc.androidnewtechnique.threadpool.ThreadPollDemoActivity;
import com.pepoc.androidnewtechnique.touchevent.TouchEventDemoActivity;
import com.pepoc.androidnewtechnique.widget.WidgetActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_main_content)
    LinearLayout llMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

        LogManager.i(Environment.getExternalStorageDirectory().getPath());
    }

    private void init() {
        setSupportActionBar(toolbar);

        Observable.from(getData()).subscribe(new Action1<Class<? extends Activity>>() {
            @Override
            public void call(final Class<? extends Activity> aClass) {
                Button button = (Button) View.inflate(MainActivity.this, R.layout.button, null);
                button.setText(aClass.getSimpleName());
                llMainContent.addView(button);
                RxView.clicks(button).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(MainActivity.this, aClass));
                    }
                });
            }
        });
    }

    private List<Class<? extends Activity>> getData() {
        List<Class<? extends Activity>> classList = new ArrayList<>();
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
        classList.add(SVGActivity.class);
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
}
