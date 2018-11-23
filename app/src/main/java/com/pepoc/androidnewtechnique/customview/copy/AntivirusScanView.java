package com.pepoc.androidnewtechnique.customview.copy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
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

    private Paint paint;
    private Bitmap bmScanningShield, bmScanningShieldBg;
    private Bitmap lineBitmap;
    private float lineTop;
    private ValueAnimator valueAnimator;
    private PorterDuffXfermode porterDuffXfermode;
//    private List<Virus[]> virusList;

    private Random random;
    private int lastRandom = 0;
    private RectF shieldRectF;
    private RectF lineRectF;
    private int alpha = 255;
    private CompleteListener completeListener;
    private boolean isFinish = false;
    private boolean isStop = false;
    private boolean isDestroy = false;
    private float shieldWidth;
    private float shieldHeight;


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
            height = (int) (width / shieldWidth * shieldHeight);
        } else {
            width = lineBitmap.getWidth();
            height = (int) (shieldHeight + lineBitmap.getHeight());
        }

        shieldRectF = new RectF();
        shieldRectF.left = (width - shieldWidth) / 2.0f;
        shieldRectF.top = lineBitmap.getHeight() / 2.0f;
        shieldRectF.right = shieldRectF.left + shieldWidth;
        shieldRectF.bottom = shieldHeight + shieldRectF.top;

        lineTop = shieldRectF.bottom;

        lineRectF = new RectF();
        lineRectF.left = shieldRectF.left;
        lineRectF.top = lineTop - lineBitmap.getHeight() / 2.0f;
        lineRectF.right = shieldRectF.right;
        lineRectF.bottom = lineTop + lineBitmap.getHeight() / 2.0f;


        setMeasuredDimension(width, height);
    }

    private void init() {

//        shieldWidth = getContext().getResources().getDimension(R.dimen.antivirus_scan_view_shield_width);
//        shieldHeight = getContext().getResources().getDimension(R.dimen.antivirus_scan_view_shield_height);

        setLayerType(LAYER_TYPE_HARDWARE, null);
        initVirusData();

        random = new Random();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);


        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);


        valueAnimator = ValueAnimator.ofFloat(0, 1, 0);
        valueAnimator.setDuration(2400);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new ShieldAccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isDestroy) {
                    return ;
                }
                float value = (Float) animation.getAnimatedValue();
                lineTop = shieldHeight - shieldHeight * value + (getHeight() - shieldHeight) / 2;

                lineRectF.left = shieldRectF.left - (shieldRectF.left * value);
                lineRectF.right = shieldRectF.right + (getWidth() - shieldRectF.right) * value;

                Thread.dumpStack();
                invalidate();
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ValueAnimator alphaAnimator = ValueAnimator.ofInt(255, 0);
                alphaAnimator.setDuration(400);
                alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (isDestroy) {
                            return ;
                        }
                        alpha = (int) animation.getAnimatedValue();
                        invalidate();
                    }
                });

                alphaAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (completeListener != null) {
                            completeListener.onComplete();
                            isFinish = true;
                        }
                    }
                });

                alphaAnimator.start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);

                if (isStop) {
                    cancel();
                    valueAnimator.setRepeatCount(0);
                    valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                    return ;
                }
                int i = random.nextInt(4);
                if (i == lastRandom) {
                    lastRandom = i > 1 ? i - 1 : i + 1;
                } else {
                    lastRandom = i;
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });

    }

    public void startAnimation() {
        valueAnimator.start();
    }

    public void cancel() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }

    public void end() {
        isStop = true;
    }

    public void onDestroy() {
        isDestroy = true;
        end();
        cancel();

        if(bmScanningShield != null && !bmScanningShield.isRecycled()){
            bmScanningShield.recycle();
            bmScanningShield = null;
        }

        if(lineBitmap != null && !lineBitmap.isRecycled()){
            lineBitmap.recycle();
            lineBitmap = null;
        }

        /*if (virusList != null && virusList.size() > 0) {
            for (int i = 0; i < virusList.size(); i++) {
                Virus[] viruses = virusList.get(i);
                if (viruses != null && viruses.length > 0) {
                    for (int j = 0; j < viruses.length; j++) {
                        Virus viruse = viruses[j];
                        if (viruse != null) {
                            viruse.destroy();
                        }
                    }
                }
            }
        }*/
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDestroy) {
            return ;
        }
        canvas.drawBitmap(bmScanningShieldBg, null, shieldRectF, null);

        /*if (virusList != null && virusList.size() > 0) {
            Virus[] viruses = virusList.get(lastRandom);
            for (int i = 0; i < viruses.length; i++) {
                viruses[i].setParentViewLeft(shieldRectF.left);
                viruses[i].setParentViewTop(shieldRectF.top);
                viruses[i].onDraw(canvas);
            }
        }*/

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bmScanningShield, null, shieldRectF, null);

        paint.setXfermode(porterDuffXfermode);

        canvas.drawRect(shieldRectF.left, lineTop, shieldRectF.right, shieldRectF.bottom, paint);
        paint.setXfermode(null);

        canvas.restoreToCount(layerId);
        lineRectF.top = lineTop - lineBitmap.getHeight() / 2;
        lineRectF.bottom = lineTop + lineBitmap.getHeight() / 2;
        paint.setAlpha(alpha);
        canvas.drawBitmap(lineBitmap, null, lineRectF, paint);
    }

    class ShieldAccelerateDecelerateInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            if (input < 0.5f) {
                return ((float) (Math.cos((input * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
            } else {
                return ((float) (Math.cos(((1 - input) * 2 + 1) * Math.PI) / 2.0f) + 0.5f) / 2.0f;
            }
        }
    }

    public interface CompleteListener {
        void onComplete();
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    private void initVirusData() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        bmScanningShield = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.privacy_scanning_shield, options);
        shieldWidth = bmScanningShield.getWidth();
        shieldHeight = bmScanningShield.getHeight();
//        bmScanningShield = BitmapFactory.decodeStream(getContext().getResources().openRawResource(+R.drawable.antivirus_scanning_shield), null, options);
        bmScanningShieldBg = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.antivirus_scanning_virus_1, options);
//        bmScanningShieldBg = BitmapFactory.decodeStream(getContext().getResources().openRawResource(+R.drawable.antivirus_scanning_shield_bg), null, options);

//        options.inJustDecodeBounds = true;
        lineBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.line, options);
//        lineBitmap = BitmapFactory.decodeStream(getContext().getResources().openRawResource(+R.drawable.antivirus_scanning_line), null, options);

//        Bitmap virus1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.antivirus_scanning_virus_1, options);
//        Bitmap virus2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.antivirus_scanning_virus_2, options);
//        Bitmap virus3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.antivirus_scanning_virus_3, options);
//
//        int shieldWidth = bmScanningShieldBg.getWidth();
//        int shieldHeight = bmScanningShieldBg.getHeight();

//        Virus[] virusFirst, virusSecond, virusThird, virusFourth;
//        virusFirst = new Virus[5];
//        Virus virus;
//        virus = new Virus(virus2, shieldWidth, shieldHeight, -1, -1, 109f / 176f, 110f / 461f, 185f / 510f, 11.2f);
//        virusFirst[0] = virus;
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 57f / 176f, 242f / 461f, 147f / 510f, 14.4f);
//        virusFirst[1] = virus;
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 84f / 176f, 267f / 461f, 223f / 510f, 360f - 14.5f);
//        virusFirst[2] = virus;
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 49f / 176f, 151f / 461f, 309f / 510f, 360f - 13f);
//        virusFirst[3] = virus;
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 82f / 176f, 219f / 461f, 315f / 510f, 15f);
//        virusFirst[4] = virus;
//
//        virusSecond = new Virus[2];
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 60f / 176f, 253f / 461f, 149f / 510f, 14.2f);
//        virusSecond[0] = virus;
//        virus = new Virus(virus2, shieldWidth, shieldHeight, -1, -1, 1.0f, 141f / 461f, 233f / 510f, 9.2f);
//        virusSecond[1] = virus;
//
//        virusThird = new Virus[3];
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 114f / 176f, 92f / 461f, 190f / 510f, 12.6f);
//        virusThird[0] = virus;
//        virus = new Virus(virus1, shieldWidth, shieldHeight, -1, -1, 83f / 176f, 209f / 461f, 140f / 510f, 360f - 11.6f);
//        virusThird[1] = virus;
//        virus = new Virus(virus2, shieldWidth, shieldHeight, -1, -1, 151f / 176f, 191f / 461f, 260f / 510f, 360f - 13.6f);
//        virusThird[2] = virus;
//
//        virusFourth = new Virus[2];
//        virus = new Virus(virus3, shieldWidth, shieldHeight, -1, -1, 132f / 174f, 107f / 461f, 291f / 510f, 18.6f);
//        virusFourth[0] = virus;
//        virus = new Virus(virus3, shieldWidth, shieldHeight, -1, -1, 90f / 174f, 241f / 461f, 343f / 510f, 360f - 20f);
//        virusFourth[1] = virus;
//
//        virusList = new ArrayList<>();
//        virusList.add(virusFirst);
//        virusList.add(virusSecond);
//        virusList.add(virusThird);
//        virusList.add(virusFourth);
    }
}
