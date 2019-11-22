package com.pepoc.androidnewtechnique.date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.date.utils.DateTimeNormalizer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTestActivity extends AppCompatActivity {

    private EditText etDateStr;
    private TextView tvDateResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_test);

        etDateStr = findViewById(R.id.et_date_str);
        tvDateResult = findViewById(R.id.tv_date_result);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateStr = etDateStr.getText().toString();
                long dateLong = DateTimeNormalizer.convertDateLong(dateStr);
                dateLong += (12*3600*1000);

                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

//                Date date = new Date();
//                date.setTime(dateLong);
//                tvDateResult.setText(ft.format(date));

                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY);
//                calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                calendar.setTimeInMillis(dateLong);
                tvDateResult.setText(ft.format(calendar.getTime()));

                try {
                    initData(1);
                } catch (Exception e) {
                    Log.e("makeE", "makeException", e);

                    Log.i("makeE", "e.getMessage() = " + e.getMessage());
                    Log.i("makeE", "e.getCause() = " + e.getCause());
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    Log.i("makeE", "e.getStackTrace() = " + String.format("%s - %s - %s", stackTrace[0], stackTrace[1], stackTrace[2]));
//                    e.printStackTrace();
                }
            }
        });
    }

    private void initData(int j) {
        j -= 1;
        makeException(j);
    }

    private void makeException(int i) {
        Log.i("me", String.valueOf(1/i));
    }
}
