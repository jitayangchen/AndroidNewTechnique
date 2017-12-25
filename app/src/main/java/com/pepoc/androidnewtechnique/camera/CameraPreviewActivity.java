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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;

public class CameraPreviewActivity extends AppCompatActivity {

    public final static String PICTURE_PATH = "picture_path";
    public final static String PICTURE_ROTATION = "picture_rotation";
    public final static String PICTURE_SIZE = "picture_size";
    private ImageView ivPreview;
    private String picturePath;
    private ProcessingBitmap processingBitmap;
    private int[] pictureSize;
    private BitmapFactory.Options options;
    private int pictureRotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);

        ivPreview = (ImageView) findViewById(R.id.iv_preview);

        processingBitmap = new ProcessingBitmap(this);

        Intent intent = getIntent();
        picturePath = intent.getStringExtra(PICTURE_PATH);
        pictureRotation = intent.getIntExtra(PICTURE_ROTATION, 0);
        pictureSize = intent.getIntArrayExtra(PICTURE_SIZE);


        ivPreview = (ImageView) findViewById(R.id.iv_preview);


//        Drawable drawable = Drawable.createFromPath(picturePath);
//        ivPreview.setImageDrawable(drawable);


//        int degree = readPictureDegree(picturePath);
//        LogManager.i("degree ======= " + degree);

        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, options);
        LogManager.i("options.outWidth === " + options.outWidth);
        LogManager.i("options.outHeight === " + options.outHeight);

        processingBitmap.start();
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

    private void saveBitmap(Bitmap bitmap) {
        File file = new File(picturePath);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ProcessingBitmap extends Thread {

        private final SoftReference<CameraPreviewActivity> activitySoftReference;

        public ProcessingBitmap(CameraPreviewActivity cameraPreviewActivity) {
            activitySoftReference = new SoftReference<>(cameraPreviewActivity);
        }

        @Override
        public void run() {
            super.run();
            final Bitmap newbitmap;
            final CameraPreviewActivity cameraPreviewActivity = activitySoftReference.get();
            if (cameraPreviewActivity == null) {
                return ;
            }
            if (cameraPreviewActivity.pictureSize[0] == cameraPreviewActivity.options.outWidth
                    && cameraPreviewActivity.pictureSize[1] == cameraPreviewActivity.options.outHeight
                    || cameraPreviewActivity.pictureSize[0] > cameraPreviewActivity.pictureSize[1]
                    && cameraPreviewActivity.options.outWidth > cameraPreviewActivity.options.outHeight) {
                newbitmap = cameraPreviewActivity.getBitmap(cameraPreviewActivity.picturePath, cameraPreviewActivity.pictureRotation, true);
                cameraPreviewActivity.saveBitmap(newbitmap);
            } else {
                newbitmap = cameraPreviewActivity.getBitmap(cameraPreviewActivity.picturePath, cameraPreviewActivity.pictureRotation, false);
            }

            cameraPreviewActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cameraPreviewActivity.ivPreview.setImageBitmap(newbitmap);
                }
            });
        }
    }
}
