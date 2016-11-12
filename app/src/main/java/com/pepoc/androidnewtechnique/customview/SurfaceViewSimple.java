package com.pepoc.androidnewtechnique.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pepoc.androidnewtechnique.R;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/8 10:25
 * @copyright TCL-MIG
 */
public class SurfaceViewSimple extends SurfaceView implements SurfaceHolder.Callback{

    private Path path, pathContent;
    private Paint paint, paintContent;
    private Bitmap bgBitmap;
    private Bitmap bitmap;
    private Bitmap lineBitmap;
    private float height;
    private ValueAnimator valueAnimator;
    private PorterDuffXfermode porterDuffXfermode;
    private int left = -1;
    private int top = 200;


    public SurfaceViewSimple(Context context) {
        super(context);
        init();
    }

    public SurfaceViewSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewSimple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);


        path = new Path();
        pathContent = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(8);


        paintContent = new Paint();
        paintContent.setAntiAlias(true);
        paintContent.setStyle(Paint.Style.FILL);
//        LinearGradient mLinearGradient = new LinearGradient(getWidth() / 5 * 2, 200, getWidth() - (getWidth() / 5 * 2), 200, new int[] {
//                Color.TRANSPARENT, Color.WHITE, Color.TRANSPARENT}, null, Shader.TileMode.CLAMP);
//        paintContent.setShader(mLinearGradient);

        bgBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.privacy_scanning_shield_on);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.privacy_scanning_shield);
        lineBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.line);
        final int bgHeight = bitmap.getHeight();

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);


        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                height = bgHeight - bgHeight * value + top;
                invalidate();
//                Log.i("YYY", "--------------onAnimationUpdate---------------");
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i("YYY", "--------------onAnimationEnd---------------");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
//                Log.i("YYY", "--------------onAnimationRepeat---------------");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                Log.i("YYY", "--------------onAnimationCancel---------------");
            }
        });

        valueAnimator.start();
    }

    public void cancelAnimation() {
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#28a4f6"));

        /*path.moveTo(getWidth() / 2, 200);

        path.quadTo(getWidth() / 5 * 2, 295, getWidth() / 5, 300);

        path.quadTo(getWidth() / 5, 600, getWidth() / 2, 700);

        path.quadTo(getWidth() - (getWidth() / 5), 600, getWidth() - (getWidth() / 5), 300);

        path.quadTo(getWidth() - (getWidth() / 5 * 2), 295, getWidth() / 2, 200);

        path.close();



        LinearGradient mLinearGradient = new LinearGradient(getWidth() / 2, 200, 0, 200, new int[] {
                Color.TRANSPARENT, Color.RED}, null, Shader.TileMode.REPEAT);
        paintContent.setShader(mLinearGradient);

        pathContent.moveTo(getWidth() / 2, 200);

        pathContent.quadTo(getWidth() / 5 * 2, 295, getWidth() / 5, 300);

        pathContent.quadTo(getWidth() / 5, 600, getWidth() / 2, 700);

        pathContent.quadTo(getWidth() - (getWidth() / 5), 600, getWidth() - (getWidth() / 5), 300);

        pathContent.quadTo(getWidth() - (getWidth() / 5 * 2), 295, getWidth() / 2, 200);

        pathContent.close();

        canvas.drawPath(path, paint);
        canvas.drawPath(pathContent, paintContent);*/





//        //设置背景色
//        canvas.drawARGB(255, 139, 197, 186);
//
//        int canvasWidth = canvas.getWidth();
//        int r = canvasWidth / 3;
//        //正常绘制黄色的圆形
//        paint.setColor(0xFFFFCC44);
//        canvas.drawCircle(r, r, r, paint);
//        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        paint.setColor(0xFF66AAFF);
//        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
//        //最后将画笔去除Xfermode
//        paint.setXfermode(null);



        if (left == -1) {
            left = (getWidth() - bgBitmap.getWidth()) / 2;
        }

        canvas.drawBitmap(bgBitmap, left, top, null);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bitmap, left, top, paint);
        paint.setColor(Color.WHITE);
//        canvas.drawLine(100, height - 5, getWidth() - 100, height - 5, paint);
        canvas.drawBitmap(lineBitmap, (getWidth() - lineBitmap.getWidth()) / 2, height - lineBitmap.getHeight(), null);

        paint.setXfermode(porterDuffXfermode);
        canvas.drawRect(left, height, bitmap.getWidth() + left, bitmap.getHeight() + top, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
