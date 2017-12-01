package com.pepoc.androidnewtechnique.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pepoc.androidnewtechnique.log.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 2017/10/19.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private final static String TAG = "CameraPreview";
    public int CAMERA_ID = 0;
    private int bitmapRotation;
    private int[] pictureSizeArr = new int[2];
    private int numberOfCameras;

    public CameraPreview(Context context) {
        super(context);
//        setClickable(true);
//        mCamera = getCameraInstance();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        LogManager.i("lifecycle==============CameraPreview=================holder = " + mHolder);
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        CAMERA_ID = getCameraId();
    }

    public Camera getmCamera() {
        return mCamera;
    }

    public Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(CAMERA_ID); // attempt to get a Camera instance

            setCameraDisplayOrientation((Activity) getContext(), CAMERA_ID, c);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public SurfaceHolder getSurfaceHolder() {
        return mHolder;
    }

    public int getBitmapRotation() {
        return bitmapRotation;
    }

    public int[] getPictureSizeArr() {
        return pictureSizeArr;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        LogManager.i("lifecycle==============surfaceCreated=================holder = " + holder);
        // The Surface has been created, now tell the camera where to draw the preview.
        mCamera = getCameraInstance();
        startPreview(mCamera, holder);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        LogManager.i("lifecycle==============surfaceDestroyed=================holder = " + holder);
//        // empty. Take care of releasing the Camera preview in your activity.
//
//        if (holder != null) {
//            holder.removeCallback(this);
//            if (holder.getSurface() != null) {
//                holder.getSurface().release();
//            }
//        }
//        mCamera.setPreviewCallback(null);
////        mCamera.stopPreview();
////        mCamera.lock();
////        mCamera.release();
        stopPreview();
////        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        LogManager.i("lifecycle==============surfaceChanged=================holder = " + holder);
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
//            mCamera.setPreviewDisplay(mHolder);
            startPreview(mCamera, mHolder);

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    public void destroyed() {
        if (getHolder() != null) {
            getHolder().removeCallback(this);
            if (getHolder().getSurface() != null) {
                getHolder().getSurface().release();
            }
        }

        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
        }
    }

    public void startPreview(Camera mCamera, SurfaceHolder holder) {
        this.mCamera = mCamera;
//        Thread.dumpStack();
        LogManager.i("==========startPreview========= mCamera = " + mCamera);
        try {
            mCamera.setPreviewDisplay(holder);
            setCameraParams();
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPreview() {
//        Thread.dumpStack();
        LogManager.i("==========stopPreview=========");
//        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    private void setCameraParams() {

        Camera.Parameters parameters = mCamera.getParameters();

        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();

//        Camera.Size pictureSize = parameters.getPictureSize();
//        LogManager.i(TAG, "pictureSize pictureSize.width=" + pictureSize.width + "  pictureSize.height=" + pictureSize.height);
//
//        Camera.Size previewSize = parameters.getPreviewSize();
//        LogManager.i(TAG, "previewSize previewSize.width=" + previewSize.width + "  previewSize.height=" + previewSize.height);

        Camera.Size pictureSize = getProperSize(supportedPictureSizes);
        if (pictureSize != null) {
            parameters.setPictureSize(pictureSize.width, pictureSize.height);
            pictureSizeArr[0] = pictureSize.width;
            pictureSizeArr[1] = pictureSize.height;
            LogManager.i(TAG, "pictureSize pictureSize.width=" + pictureSize.width + "  pictureSize.height=" + pictureSize.height);
        }
        Camera.Size previewSize = getProperSize(supportedPreviewSizes);
        if (previewSize != null) {
            LogManager.i(TAG, "previewSize previewSize.width=" + previewSize.width + "  previewSize.height=" + previewSize.height);
            parameters.setPreviewSize(previewSize.width, previewSize.height);
        }

//        ViewGroup.LayoutParams layoutParams = getLayoutParams();
//        layoutParams.width = 1080;
//        layoutParams.height = 1440;
//        setLayoutParams(layoutParams);


        for (Camera.Size size : supportedPictureSizes) {
            LogManager.i(TAG, "pictureSizeList size.width=" + size.width + "  size.height=" + size.height);
        }

        for (Camera.Size size : supportedPreviewSizes) {
            Log.i(TAG, "previewSizeList size.width=" + size.width + "  size.height=" + size.height);
        }

        bitmapRotation = getOrientationChangedRotation();
        parameters.setRotation(bitmapRotation);

        //设置对焦模式
        mCamera.cancelAutoFocus();
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            LogManager.i("FOCUS_MODE_CONTINUOUS_PICTURE, FOCUS_MODE_CONTINUOUS_PICTURE, FOCUS_MODE_CONTINUOUS_PICTURE");
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            LogManager.i("Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE");
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            mCamera.cancelAutoFocus();
        }

//        try {
//            mCamera.setParameters(parameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mCamera.setParameters(parameters);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                LogManager.i("onAutoFocus  ACTION_UP");

                Rect rect = calculateTapArea(event.getX(), event.getY(), 1, getMeasuredWidth(), getMeasuredHeight());
                List<Camera.Area> areas = new ArrayList<>();
                areas.add(new Camera.Area(rect, 1000));
//                mCamera.cancelAutoFocus();
                Camera.Parameters parameters = mCamera.getParameters();
                int maxNumFocusAreas = parameters.getMaxNumFocusAreas();
                LogManager.i("maxNumFocusAreas === " + maxNumFocusAreas);
                if (parameters.getMaxNumFocusAreas() > 0) {

                    parameters.setFocusAreas(areas);
                    parameters.setMeteringAreas(areas);
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    mCamera.setParameters(parameters);
                    mCamera.autoFocus(new Camera.AutoFocusCallback() {
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                            LogManager.i("onAutoFocus === " + success);
                            mCamera.cancelAutoFocus();
                        }
                    });
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private static Rect calculateTapArea(float x, float y, float coefficient, int width, int height) {
        float focusAreaSize = 300;
        int areaSize = Float.valueOf(focusAreaSize * coefficient).intValue();
        int centerX = (int) (x / width * 2000 - 1000);
        int centerY = (int) (y / height * 2000 - 1000);

        int halfAreaSize = areaSize / 2;
        RectF rectF = new RectF(clamp(centerX - halfAreaSize, -1000, 1000)
                , clamp(centerY - halfAreaSize, -1000, 1000)
                , clamp(centerX + halfAreaSize, -1000, 1000)
                , clamp(centerY + halfAreaSize, -1000, 1000));
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private static int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }

    public void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
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

    public int getOrientationChangedRotation() {
//        if (orientation == ORIENTATION_UNKNOWN) return;
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, info);
        int orientation = 0;
        switch (((Activity)getContext()).getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0: orientation = 0; break;
            case Surface.ROTATION_90: orientation = 90; break;
            case Surface.ROTATION_180: orientation = 180; break;
            case Surface.ROTATION_270: orientation = 270; break;
        }
        orientation = (orientation + 45) / 90 * 90;
        int rotation;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            rotation = (info.orientation - orientation + 360) % 360;
        } else {  // back-facing camera
            rotation = (info.orientation + orientation) % 360;
        }
        return rotation;
    }

    private Camera.Size getProperSize(List<Camera.Size> supportedSizes) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Log.i(TAG, "measuredWidth = " + measuredWidth);
        Log.i(TAG, "measuredHeight = " + measuredHeight);
        float dvalue = -1;
        Camera.Size result = null;
        for (Camera.Size size : supportedSizes) {
            if (size.width < measuredHeight && size.height < measuredWidth) {
                continue;
            }
            float ratioCamera = (size.width * 1.0f) / (size.height * 1.0f);
            float ratioView = (measuredHeight * 1.0f) / (measuredWidth * 1.0f);

            float dvalueTemp = Math.abs(ratioCamera - ratioView);
            if (dvalue == -1) {
                dvalue = dvalueTemp;
                result = size;
            } else if (dvalueTemp < dvalue) {
                dvalue = dvalueTemp;
                result = size;
            }
        }

        if (result == null && supportedSizes.size() > 0) {
            Camera.Size firstItem = supportedSizes.get(0);
            Camera.Size lastItem = supportedSizes.get(supportedSizes.size() - 1);
            if (firstItem.width >= lastItem.width || firstItem.height >= lastItem.height) {
                result = firstItem;
            } else {
                result = lastItem;
            }
        }

        return result;
    }

    public void changeCamera() {
        if (numberOfCameras < 2) {
            return ;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, cameraInfo);

        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            if (CAMERA_ID == 0) {
                CAMERA_ID = 1;
            }
        } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            if (CAMERA_ID == 1) {
                CAMERA_ID = 0;
            }
        }

        stopPreview();
        startPreview(getCameraInstance(), getHolder());
    }

    public int getCameraId() {

//        int cameraId = -1;
        numberOfCameras = Camera.getNumberOfCameras();
//        if (numberOfCameras >= 2) {
//
//            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//            Camera.getCameraInfo(1, cameraInfo);
//
//            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//                cameraId = 1;
//            } else {
//                for (int i = 0; i < numberOfCameras; i++) {
//                    if (i == 1)
//                        continue;
//                    Camera.getCameraInfo(i, cameraInfo);
//                    if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//                        cameraId = i;
//                    }
//                }
//            }
//
//        } else if (numberOfCameras >= 1) {
//            return 0;
//        } else {
//            return -1;
//        }

        if (numberOfCameras >= 2) {
            return 1;
        } else if (numberOfCameras >= 1) {
            return 0;
        }

        return 0;
    }
}
