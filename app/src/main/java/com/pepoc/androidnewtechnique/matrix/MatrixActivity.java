package com.pepoc.androidnewtechnique.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class MatrixActivity extends AppCompatActivity {

    private BitmapFactory.Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmapXXX = BitmapFactory.decodeResource(getResources(), R.drawable.privacy_scanning_shield_xxxhdpi);
        Bitmap bitmapXX = BitmapFactory.decodeResource(getResources(), R.drawable.privacy_scanning_shield_xxhdpi);
        Bitmap bitmapX = BitmapFactory.decodeResource(getResources(), R.drawable.privacy_scanning_shield_xhdpi);

        LogManager.i("bitmapXXX === " + bitmapXXX.getWidth() + " - " + bitmapXXX.getHeight());
        LogManager.i("bitmapXX === " + bitmapXX.getWidth() + " - " + bitmapXX.getHeight());
        LogManager.i("bitmapX === " + bitmapX.getWidth() + " - " + bitmapX.getHeight());
    }
}
