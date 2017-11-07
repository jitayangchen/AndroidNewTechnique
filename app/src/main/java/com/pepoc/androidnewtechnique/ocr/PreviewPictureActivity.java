package com.pepoc.androidnewtechnique.ocr;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.pepoc.androidnewtechnique.R;

public class PreviewPictureActivity extends AppCompatActivity {

    public final static String PICTURE_PATH = "picture_path";
    private ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_picture);

        Intent intent = getIntent();
        String picturePath = intent.getStringExtra(PICTURE_PATH);

        ivPreview = (ImageView) findViewById(R.id.iv_preview);
        Drawable drawable = Drawable.createFromPath(picturePath);
        ivPreview.setImageDrawable(drawable);
//        ivPreview.setBackground(drawable);
    }
}
