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
import android.view.View;
import android.view.animation.Interpolator;

import com.pepoc.androidnewtechnique.R;

import java.util.Random;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/8 10:25
 * @copyright TCL-MIG
 */
public class AntivirusScanView extends View {

    private Path path, pathContent;
    private Paint paint, paintContent;
    private Bitmap bmScanningVirus1, bmScanningVirus2, bmScanningVirus3, bmScanningVirus4;
    private Bitmap bitmap;
    private Bitmap lineBitmap;
    private float lineTop;
    private ValueAnimator valueAnimator;
    private PorterDuffXfermode porterDuffXfermode;
//    private int left = -1;
//    private int top = 100;
    private int viewWidth, viewHeight;
    private int lastRandom = 0;

    private Bitmap[] virusArr = new Bitmap[4];
    private Random random;


    public AntivirusScanView(Context context) {
        super(context);
        init();
    }

    public AntivirusScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AntivirusScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            width = widthSize;
            height = heightSize;
        } else {
            width = viewWidth;
            height = viewHeight;
        }

        lineTop = bitmap.getHeight() + (height - bitmap.getHeight()) / 2;

        setMeasuredDimension(width, height);
    }

    private void init() {

        random = new Random();

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

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        bmScanningVirus1 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.antivirus_scanning_virus_1, options);
        bmScanningVirus2 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.antivirus_scanning_virus_2, options);
        bmScanningVirus3 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.antivirus_scanning_virus_3, options);
        bmScanningVirus4 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.antivirus_scanning_virus_4, options);
        virusArr[0] = bmScanningVirus1;
        virusArr[1] = bmScanningVirus2;
        virusArr[2] = bmScanningVirus3;
        virusArr[3] = bmScanningVirus4;

//        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.privacy_scanning_shield, options);
        bitmap = BitmapFactory.decodeStream(getContext().getResources().openRawResource(R.mipmap.privacy_scanning_shield), null, null);

        options.outWidth = bitmap.getWidth();
        options.outHeight = bitmap.getHeight();
        lineBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.line, options);

        viewWidth = lineBitmap.getWidth();
        viewHeight = viewWidth;

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);


        valueAnimator = ValueAnimator.ofFloat(0, 1, 0);
        valueAnimator.setDuration(2600);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new MyInterpolator());
//        valueAnimator.setEvaluator(new MyTypeEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                lineTop = bitmap.getHeight() - bitmap.getHeight() * value + (getHeight() - bitmap.getHeight()) / 2;
//                LogManager.i("value === " + value + " --- lineTop === " + lineTop);
                invalidate();
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
                Log.i("YYY", "--------------onAnimationRepeat---------------");

                int i;
                do {
                    i = random.nextInt(4);
                } while (i == lastRandom);
                lastRandom = i;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                Log.i("YYY", "--------------onAnimationCancel---------------");
            }
        });

    }

    public void startAnimation() {
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
        canvas.drawColor(Color.rgb(66, 125, 245));

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

        Bitmap bitmapVirus = virusArr[lastRandom];
        canvas.drawBitmap(bitmapVirus, (getWidth() - bitmapVirus.getWidth()) / 2, (getHeight() - bitmapVirus.getHeight()) / 2, null);

            int canvasWidth = canvas.getWidth();
            int canvasHeight = canvas.getHeight();
            int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, (getHeight() - bitmap.getHeight()) / 2, null);

            paint.setColor(Color.WHITE);

            paint.setXfermode(porterDuffXfermode);

            float left = (getWidth() - bitmap.getWidth()) / 2;
            float right = getWidth() - left;
            float bottom = getHeight() - (getHeight() - bitmap.getHeight()) / 2;
            canvas.drawRect(left, lineTop, right, bottom, paint);
            paint.setXfermode(null);

        canvas.restoreToCount(layerId);
            canvas.drawBitmap(lineBitmap, (getWidth() - lineBitmap.getWidth()) / 2, lineTop - (lineBitmap.getHeight() / 2), null);
    }

    class MyInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            if (input < 0.5f) {
                return ((float) (Math.cos((input * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
            } else {
                return ((float) (Math.cos(((1 - input) * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
            }
        }
    }
}
