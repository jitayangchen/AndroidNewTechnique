package com.pepoc.androidnewtechnique.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.util.Util;

/**
 * Created by yangchen on 2017/10/23.
 */

public class AuthenticationAreaView extends View {

    private Paint paint;
    private Paint paintText;
    private Rect headRect;
    private Rect identityCardRect;
    private int headRectMargin = 200;
    private int identityCardMargin = 50;
    // 身份证长宽比
    private final static float IDENTITY_CARD_RATIO = 1.5852f;
    private int cornerWidth = 8;
    private int cornerHeight = 40;

    private String headText = "您的头部区域";
    private String identityCardText = "您的身份证区域";

    public AuthenticationAreaView(Context context) {
        super(context);
        init();
    }

    public AuthenticationAreaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AuthenticationAreaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        headRect.left = headRectMargin;
        headRect.top = headRectMargin;
        headRect.right = getMeasuredWidth() - headRectMargin;
        headRect.bottom = (int) (getMeasuredWidth() - (headRectMargin * 2) + headRect.top * 0.5f);

        identityCardRect.left = identityCardMargin;
        identityCardRect.top = headRect.bottom + headRectMargin;
        identityCardRect.right = getMeasuredWidth() - identityCardMargin;
        identityCardRect.bottom = (int) ((getMeasuredWidth() - (identityCardMargin * 2)) / IDENTITY_CARD_RATIO + identityCardRect.top);
    }

    private void init() {
        headRect = new Rect();
        identityCardRect = new Rect();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        paintText = new Paint();
        paintText.setStyle(Paint.Style.FILL);
        paintText.setColor(Color.RED);
        paintText.setTextSize(Util.sp2px(getContext(), 14));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.clipRect(headRect, Region.Op.DIFFERENCE);
        canvas.clipRect(identityCardRect, Region.Op.DIFFERENCE);

        canvas.drawColor(Color.parseColor("#55000000"));

        canvas.restore();

        // Head View
        canvas.drawRect(headRect.left, headRect.top, headRect.left + cornerWidth, headRect.top + cornerHeight, paint);
        canvas.drawRect(headRect.left, headRect.top, headRect.left + cornerHeight, headRect.top + cornerWidth, paint);

        canvas.drawRect(headRect.right - cornerWidth, headRect.top, headRect.right, headRect.top + cornerHeight, paint);
        canvas.drawRect(headRect.right - cornerHeight, headRect.top, headRect.right, headRect.top + cornerWidth, paint);

        canvas.drawRect(headRect.left, headRect.bottom - cornerWidth, headRect.left + cornerHeight, headRect.bottom, paint);
        canvas.drawRect(headRect.left, headRect.bottom - cornerHeight, headRect.left + cornerWidth, headRect.bottom, paint);

        canvas.drawRect(headRect.right - cornerHeight, headRect.bottom - cornerWidth, headRect.right, headRect.bottom, paint);
        canvas.drawRect(headRect.right - cornerWidth, headRect.bottom - cornerHeight, headRect.right, headRect.bottom, paint);


        // Identity Card View
        canvas.drawRect(identityCardRect.left, identityCardRect.top, identityCardRect.left + cornerWidth, identityCardRect.top + cornerHeight, paint);
        canvas.drawRect(identityCardRect.left, identityCardRect.top, identityCardRect.left + cornerHeight, identityCardRect.top + cornerWidth, paint);

        canvas.drawRect(identityCardRect.right - cornerWidth, identityCardRect.top, identityCardRect.right, identityCardRect.top + cornerHeight, paint);
        canvas.drawRect(identityCardRect.right - cornerHeight, identityCardRect.top, identityCardRect.right, identityCardRect.top + cornerWidth, paint);

        canvas.drawRect(identityCardRect.left, identityCardRect.bottom - cornerWidth, identityCardRect.left + cornerHeight, identityCardRect.bottom, paint);
        canvas.drawRect(identityCardRect.left, identityCardRect.bottom - cornerHeight, identityCardRect.left + cornerWidth, identityCardRect.bottom, paint);

        canvas.drawRect(identityCardRect.right - cornerHeight, identityCardRect.bottom - cornerWidth, identityCardRect.right, identityCardRect.bottom, paint);
        canvas.drawRect(identityCardRect.right - cornerWidth, identityCardRect.bottom - cornerHeight, identityCardRect.right, identityCardRect.bottom, paint);

        float headTextWidth = paintText.measureText(headText);
        float identityCardTextWidth = paintText.measureText(identityCardText);

        int headTextX = (int) ((headRect.width() - headTextWidth) / 2 + headRect.left);
        canvas.drawText(headText, headTextX, headRect.top + 100, paintText);

        int identityCardX = (int) ((identityCardRect.width() - identityCardTextWidth) / 2 + identityCardRect.left);
        canvas.drawText(identityCardText, identityCardX, identityCardRect.top + 100, paintText);
    }
}
