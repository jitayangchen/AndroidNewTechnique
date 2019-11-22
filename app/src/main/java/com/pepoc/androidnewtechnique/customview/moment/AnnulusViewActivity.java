package com.pepoc.androidnewtechnique.customview.moment;

import android.app.Activity;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.R;

import java.util.Objects;

public class AnnulusViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annulus_view);

//        funtest(1);

        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });

        SeekBar seekBar = findViewById(R.id.seek_bar);
        seekBar.setProgress((int) (getSystemBrightnessValue() * 100));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                WindowManager.LayoutParams lp = getWindow().getAttributes();

                lp.screenBrightness = progress / 100f;

                getWindow().setAttributes(lp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.btn_get_brightness).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float screenBrightness = 0;

                WindowManager.LayoutParams lp = getWindow().getAttributes();
                if (lp.screenBrightness != WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE) {
                    screenBrightness = lp.screenBrightness;
                } else {
                    screenBrightness = getSystemBrightnessValue();
                }

                Toast.makeText(AnnulusViewActivity.this, "亮度：" + screenBrightness, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.vibrate_short).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeVibrator(15);
            }
        });

        findViewById(R.id.vibrate_long).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeVibrator(400);
            }
        });

        findViewById(R.id.btn_set_clipboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard("Yangchen clipboard");
            }
        });

        findViewById(R.id.btn_get_clipboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AnnulusViewActivity.this, getClipboard(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void executeVibrator(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(duration);
    }

    private float getSystemBrightnessValue() {
        int screenBrightness = 0;
        ContentResolver resolver = getContentResolver();
        try {
            screenBrightness = android.provider.Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenBrightness / 255f;
    }

    public String getClipboard() {
        String content = "";
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip != null && clipboardManager.hasPrimaryClip() && primaryClip.getItemCount() > 0) {
            content = primaryClip.getItemAt(0).getText().toString();
        }

        return content;
    }

    public void setClipboard(String data) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", data);
        clipboardManager.setPrimaryClip(clipData);
    }

    private static void funtest(int i) {
//        try {
            float r = 1 / i;
            exceptionTest(i - 1);
            Log.i("HHH", "hahaha");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Log.i("HHH", "finally --- ");
//        }

        Log.i("HHH", "bottom --- ");

    }

    private static void exceptionTest(int i) {
        float r = 1 / i;

        Log.i("HHH", "hahaha");
    }
}
