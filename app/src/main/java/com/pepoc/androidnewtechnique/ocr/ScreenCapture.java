package com.pepoc.androidnewtechnique.ocr;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import com.pepoc.androidnewtechnique.log.LogManager;
import com.pepoc.androidnewtechnique.util.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.pepoc.androidnewtechnique.util.Util.getScreenShotsName;

/**
 * Created by yangchen on 2017/10/19.
 */

public class ScreenCapture {

    private MediaProjection mMediaProjection;
    private VirtualDisplay mVirtualDisplay;
    private ImageReader mImageReader;
    private Context mContext;
    private Handler mHandler = new Handler();
    private RectF rectF;

    public ScreenCapture(Context mContext) {
        this.mContext = mContext;
    }

    public void createImageReader() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            mImageReader = ImageReader.newInstance(Util.getScreenWidth(mContext), Util.getScreenHeight(mContext), PixelFormat.RGBA_8888, 2);
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void virtualDisplay() {
        try {
            mVirtualDisplay = mMediaProjection.createVirtualDisplay("ScreenCapture",
                    Util.getScreenWidth(mContext), Util.getScreenHeight(mContext), Util.getScreenDensity(mContext), DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    mImageReader.getSurface(), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void startCapture() {

        Image image = mImageReader.acquireLatestImage();

        LogManager.i("----------startCapture---------" + image);
        if (image == null) {
            startScreenShot();
        } else {
            SaveTask mSaveTask = new SaveTask();
            AsyncTaskCompat.executeParallel(mSaveTask, image);
        }

        LogManager.i("----------startCapture---------");
    }

    public class SaveTask extends AsyncTask<Image, Void, String> {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(Image... params) {
            LogManager.i("----------doInBackground---------");
            if (params == null || params.length < 1 || params[0] == null) {

                return null;
            }

            Image image = params[0];

            int width = image.getWidth();
            int height = image.getHeight();
            final Image.Plane[] planes = image.getPlanes();
            final ByteBuffer buffer = planes[0].getBuffer();
            //每个像素的间距
            int pixelStride = planes[0].getPixelStride();
            //总的间距
            int rowStride = planes[0].getRowStride();
            int rowPadding = rowStride - pixelStride * width;
            Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            image.close();


            if (width != Util.getScreenWidth(mContext) || rowPadding != 0) {
                int[] pixel = new int[width + rowPadding / pixelStride];
                bitmap.getPixels(pixel, 0, width + rowPadding / pixelStride, 0, 0, width + rowPadding / pixelStride, 1);
                int leftPadding = 0;
                int rightPadding = width + rowPadding / pixelStride;
                for (int i = 0; i < pixel.length; i++) {
                    if (pixel[i] != 0) {
                        leftPadding = i;
                        break;
                    }
                }
                for (int i = pixel.length - 1; i >= 0; i--) {
                    if (pixel[i] != 0) {
                        rightPadding = i;
                        break;
                    }
                }
                width = Math.min(width, Util.getScreenWidth(mContext));
                if (rightPadding - leftPadding > width) {
                    rightPadding = width;
                }
                bitmap = Bitmap.createBitmap(bitmap, leftPadding, 0, rightPadding - leftPadding, height);
            }










            LogManager.i("Bitmap Size bitmap.getWidth() = " + bitmap.getWidth());
            LogManager.i("Bitmap Size bitmap.getHeight() = " + bitmap.getHeight());
            bitmap = Bitmap.createBitmap(bitmap, (int)rectF.left, (int)rectF.top, (int)(rectF.right - rectF.left), (int)(rectF.bottom - rectF.top));
//            bitmap = Bitmap.createBitmap(bitmap, 0, 0, Util.getScreenWidth(mContext), Util.getScreenHeight(mContext));
            LogManager.i("Bitmap Size bitmap.getWidth() = " + bitmap.getWidth());
            LogManager.i("Bitmap Size bitmap.getHeight() = " + bitmap.getHeight());
//            LogManager.i("Bitmap Size getScreenWidth() = " + Util.getScreenWidth(mContext));
//            LogManager.i("Bitmap Size getScreenHeight() = " + Util.getScreenHeight(mContext));
            LogManager.i("Bitmap Size rectF = " + rectF.toString());
            File fileImage = null;
            if (bitmap != null) {
                try {
                    fileImage = new File(getScreenShotsName(mContext.getApplicationContext()));
                    LogManager.i("----------new File(getScreenShotsName---------");
                    if (!fileImage.exists()) {
                        try {
                            fileImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                            LogManager.i("----------fileImage.createNewFile()---------");
                        }
                    }
//                    if (!fileImage.getParentFile().exists()) {
//                        fileImage.getParentFile().mkdirs();
//                    }
                    FileOutputStream out = new FileOutputStream(fileImage);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                    Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(fileImage);
                    media.setData(contentUri);
                    mContext.sendBroadcast(media);
                    LogManager.i("----------sendBroadcast(media);---------");
                } catch (IOException e) {
                    e.printStackTrace();
                    fileImage = null;
                }
            }

            if (fileImage != null) {
                return fileImage.getAbsolutePath();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);
            //预览图片
            if (bitmap != null) {

                Log.i("ryze", "获取图片成功 bitmap Path = " + bitmap);
                Intent intent = new Intent(mContext, PreviewPictureActivity.class);
                intent.putExtra(PreviewPictureActivity.PICTURE_PATH, bitmap);
                mContext.startActivity(intent);
            }

        }
    }

    public void startScreenShot() {



        mHandler.postDelayed(new Runnable() {
            public void run() {
                //start virtual
                virtualDisplay();
            }
        }, 100);

        mHandler.postDelayed(new Runnable() {
            public void run() {
                //capture the screen
                startCapture();

            }
        }, 300);
    }

    public void toCapture(final Intent data, final MediaProjectionManager mMediaProjectionManager, RectF rectF) {
        this.rectF = rectF;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mMediaProjection = mMediaProjectionManager.getMediaProjection(Activity.RESULT_OK, data);
                    virtualDisplay();
                    startCapture();
                }
            }
        }, 500);

    }
}
