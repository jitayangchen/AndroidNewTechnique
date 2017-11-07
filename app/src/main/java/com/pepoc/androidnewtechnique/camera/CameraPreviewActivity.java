package com.pepoc.androidnewtechnique.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.IOException;

public class CameraPreviewActivity extends AppCompatActivity {

    public final static String PICTURE_PATH = "picture_path";
    public final static String PICTURE_ROTATION = "picture_rotation";
    public final static String PICTURE_SIZE = "picture_size";
    private ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        ivPreview = (ImageView) findViewById(R.id.iv_preview);

        Intent intent = getIntent();
        String picturePath = intent.getStringExtra(PICTURE_PATH);
        int pictureRotation = intent.getIntExtra(PICTURE_ROTATION, 0);
        int[] pictureSize = intent.getIntArrayExtra(PICTURE_SIZE);


        ivPreview = (ImageView) findViewById(R.id.iv_preview);


//        Drawable drawable = Drawable.createFromPath(picturePath);
//        ivPreview.setImageDrawable(drawable);


//        int degree = readPictureDegree(picturePath);
//        LogManager.i("degree ======= " + degree);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, options);
        LogManager.i("options.outWidth === " + options.outWidth);
        LogManager.i("options.outHeight === " + options.outHeight);

        Bitmap newbitmap;
        if (pictureSize[0] == options.outWidth && pictureSize[1] == options.outHeight
                || pictureSize[0] > pictureSize[1] && options.outWidth > options.outHeight) {
            newbitmap = getBitmap(picturePath, pictureRotation, true);
        } else {
            newbitmap = getBitmap(picturePath, pictureRotation, false);
        }

        ivPreview.setImageBitmap(newbitmap);
    }

    private Bitmap getBitmap(String picturePath, int pictureRotation, boolean isRotate) {
        BitmapFactory.Options opts=new BitmapFactory.Options();//获取缩略图显示到屏幕上
        opts.inSampleSize=2;
        Bitmap cbitmap = BitmapFactory.decodeFile(picturePath, opts);

        if (!isRotate) {
            return cbitmap;
        }
        /**
         * 把图片旋转为正的方向
         */
        return rotaingImageView(pictureRotation, cbitmap);
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    /*
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
}
