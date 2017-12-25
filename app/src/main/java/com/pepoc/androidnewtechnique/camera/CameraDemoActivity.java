package com.pepoc.androidnewtechnique.camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "CameraDemoActivity";
    private Camera mCamera;
    private CameraPreview mPreview;
    public final static int CAMERA_ID = 1;
    private boolean isPreview = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera_demo);

        // Create an instance of Camera
//        mCamera = getCameraInstance();

//        setCameraParams();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);



        findViewById(R.id.button_capture).setOnClickListener(this);
    }

    private void setCameraParams() {

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        parameters.setPictureFormat(PixelFormat.JPEG);
        parameters.setPreviewFormat(PixelFormat.JPEG);
        parameters.setJpegQuality(100);

        Camera.Size pictureSize = parameters.getPictureSize();
        LogManager.i(TAG, "pictureSize pictureSize.width=" + pictureSize.width + "  pictureSize.height=" + pictureSize.height);

        Camera.Size previewSize = parameters.getPreviewSize();
        LogManager.i(TAG, "previewSize previewSize.width=" + previewSize.width + "  previewSize.height=" + previewSize.height);

        parameters.setPictureSize(2592, 1944);
        parameters.setPreviewSize(1920, 1080);



        for (Camera.Size size : supportedPictureSizes) {
            LogManager.i(TAG, "pictureSizeList size.width=" + size.width + "  size.height=" + size.height);
        }

        for (Camera.Size size : supportedPreviewSizes) {
            Log.i(TAG, "previewSizeList size.width=" + size.width + "  size.height=" + size.height);
        }

        mCamera.setParameters(parameters);
    }

    public void setCameraDisplayOrientation(int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        // 90
        camera.setDisplayOrientation(result);
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware() {
        // this device has a camera
        // no camera on this device
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    /** A safe way to get an instance of the Camera object. */
    public Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(CAMERA_ID); // attempt to get a Camera instance

            setCameraDisplayOrientation(CAMERA_ID, c);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogManager.i("---------- on-onRestart -----------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogManager.i("---------- on-onStart -----------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogManager.i("---------- on-onResume -----------");
        mPreview.setVisibility(View.VISIBLE);
//        if (!isPreview) {
//            mCamera = getCameraInstance();
//
//            mPreview.startPreview(mCamera, mPreview.getSurfaceHolder());
//
//            isPreview = true;
//        }

//        mPreview.vis
    }
//
    @Override
    protected void onPause() {
        super.onPause();
        LogManager.i("---------- on-onPause -----------");
        mPreview.setVisibility(View.INVISIBLE);
////        releaseCamera();              // release the camera immediately on pause event
//
//        if (isPreview) {
//            mPreview.stopPreview();
//
//            isPreview = false;
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogManager.i("---------- on-onStop -----------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPreview.destroyed();
    }

    //    private void releaseCamera(){
//        if (mCamera != null){
//            mCamera.release();        // release the camera for other applications
//            mCamera = null;
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_capture:

                if (mPreview == null || mPreview.getmCamera() == null) {
                    LogManager.i("mPreview === " + mPreview);
                    LogManager.i("mPreview.getmCamera() === " + mPreview.getmCamera());
                    return ;
                }
                mPreview.getmCamera().takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        BufferedOutputStream bos = null;
                        Bitmap bm = null;
                        try {
                            // 获得图片
                            bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                Log.i(TAG, "Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
                                String filePath = getExternalCacheDir() + "/AndroidNewTechnique/hahaha/lalala/xixixi/hehehe/camera_"+System.currentTimeMillis()+".jpg";//照片保存路径
                                File file = new File(filePath);
                                if (!file.getParentFile().exists()) {
                                    boolean result = file.getParentFile().mkdirs();
                                }
                                if (!file.exists()){
                                    boolean newFile = file.createNewFile();
                                }
                                bos = new BufferedOutputStream(new FileOutputStream(file));
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中

                                Intent intent = new Intent(CameraDemoActivity.this, CameraPreviewActivity.class);
                                intent.putExtra(CameraPreviewActivity.PICTURE_PATH, filePath);
                                intent.putExtra(CameraPreviewActivity.PICTURE_ROTATION, mPreview.getBitmapRotation());
                                intent.putExtra(CameraPreviewActivity.PICTURE_SIZE, mPreview.getPictureSizeArr());
                                startActivity(intent);
                            }else{
                                Toast.makeText(CameraDemoActivity.this,"没有检测到内存卡", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (bos != null) {
                                    bos.flush();//输出
                                }
                                if (bos != null) {
                                    bos.close();//关闭
                                }
                                if (bm != null) {
                                    bm.recycle();// 回收bitmap空间
                                }
//                                mCamera.stopPreview();// 关闭预览
//                                mCamera.startPreview();// 开启预览
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
        }
    }
}
