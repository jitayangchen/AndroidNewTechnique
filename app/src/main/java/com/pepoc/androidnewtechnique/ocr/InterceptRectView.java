package com.pepoc.androidnewtechnique.ocr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchen on 2017/10/16.
 */

public class InterceptRectView extends View {

    private RectF rect, lastRect;
    private Paint borderPaint, cornerPaint;
    private float startX;
    private float startY;
    private float lastMoveX;
    private float lastMoveY;
    private float fixedControlPointX;
    private float fixedControlPointY;
    private float moveControlPointX;
    private float moveControlPointY;
    private boolean isMove = false;
    private boolean isControl = false;

    private RectF leftTopRect;
    private RectF rightTopRect;
    private RectF leftBottomRect;
    private RectF rightBottomRect;
    private RectF leftBorderRect;
    private RectF topBorderRect;
    private RectF rightBorderRect;
    private RectF bottomBorderRect;

    private List<RectF> controlPointRects;

    private final static int CONTROL_WIDTH = 40;
    private final static int LEAST_WIDTH = 10;

    private final static int cornerWidth = 8;
    private final static int cornerHeight = 40;

    private int currentControlPointPosition;

    public InterceptRectView(Context context) {
        super(context);

        init();
    }

    public InterceptRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public InterceptRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setClickable(true);

        rect = new RectF();
        lastRect = new RectF();

        leftTopRect = new RectF();
        rightTopRect = new RectF();
        leftBottomRect = new RectF();
        rightBottomRect = new RectF();
        leftBorderRect = new RectF();
        topBorderRect = new RectF();
        rightBorderRect = new RectF();
        bottomBorderRect = new RectF();

        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(3);
        borderPaint.setStyle(Paint.Style.STROKE);

        cornerPaint = new Paint(borderPaint);
        cornerPaint.setStrokeWidth(1);
        cornerPaint.setStyle(Paint.Style.FILL);

        controlPointRects = new ArrayList<>();
        controlPointRects.add(leftTopRect);
        controlPointRects.add(rightTopRect);
        controlPointRects.add(leftBottomRect);
        controlPointRects.add(rightBottomRect);

        controlPointRects.add(leftBorderRect);
        controlPointRects.add(topBorderRect);
        controlPointRects.add(rightBorderRect);
        controlPointRects.add(bottomBorderRect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                if (!rect.isEmpty()) {
                    currentControlPointPosition = getControlPointRectPosition(startX, startY);
                    if (currentControlPointPosition != -1) {
                        isControl = true;

                        switch (currentControlPointPosition) {
                            case 0:
                                fixedControlPointX = lastRect.right;
                                fixedControlPointY = lastRect.bottom;

                                moveControlPointX = lastRect.left;
                                moveControlPointY = lastRect.top;
                                break;
                            case 1:
                                fixedControlPointX = lastRect.left;
                                fixedControlPointY = lastRect.bottom;

                                moveControlPointX = lastRect.right;
                                moveControlPointY = lastRect.top;
                                break;
                            case 2:
                                fixedControlPointX = lastRect.right;
                                fixedControlPointY = lastRect.top;

                                moveControlPointX = lastRect.left;
                                moveControlPointY = lastRect.bottom;
                                break;
                            case 3:
                                fixedControlPointX = lastRect.left;
                                fixedControlPointY = lastRect.top;

                                moveControlPointX = lastRect.right;
                                moveControlPointY = lastRect.bottom;
                                break;
                            case 4:
                                fixedControlPointX = lastRect.right;

                                moveControlPointX = lastRect.left;
                                break;
                            case 5:
                                fixedControlPointY = lastRect.bottom;

                                moveControlPointY = lastRect.top;
                                break;
                            case 6:
                                fixedControlPointX = lastRect.left;

                                moveControlPointX = lastRect.right;
                                break;
                            case 7:
                                fixedControlPointY = lastRect.top;

                                moveControlPointY = lastRect.bottom;
                                break;
                        }
                    } else {
                        isMove = rect.contains(startX, startY);
                    }
                }

                lastMoveX = startX;
                lastMoveY = startY;
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();




                if (isMove) {
                    float x = getMoveDiffX(rect, moveX, lastMoveX);
                    float y = getMoveDiffY(rect, moveY, lastMoveY);
                    rect.set(rect.left + x, rect.top + y, rect.right + x, rect.bottom + y);

                    lastMoveX = moveX;
                    lastMoveY = moveY;
                } else if (isControl) {
//                    float x = moveX - lastMoveX;
//                    float y = moveY - lastMoveY;
//                    LogManager.i("x === " + x + " --- y === " + y);

//                    switch (currentControlPointPosition) {
//                        case 0:
//                            rect.left = rect.left + x;
//                            rect.top = rect.top + y;
//                            break;
//                        case 1:
//                            rect.right = rect.right + x;
//                            rect.top = rect.top + y;
//                            break;
//
//                        case 2:
//                            rect.left = rect.left + x;
//                            rect.bottom = rect.bottom + y;
//                            break;
//                        case 3:
//                            rect.right = rect.right + x;
//                            rect.bottom = rect.bottom + y;
//                            break;
//                    }

                    float x = 0;
                    float y = 0;
                    if (currentControlPointPosition > 3) {
                        switch (currentControlPointPosition) {
                            case 4:
                            case 6:
                                x = getControlDiffX(moveControlPointX, moveX, lastMoveX);

                                rect.left = Math.min(fixedControlPointX, moveControlPointX + x);
                                rect.right = Math.max(fixedControlPointX, moveControlPointX + x);
                                break;
                            case 5:
                            case 7:
                                y = getControlDiffY(moveControlPointY, moveY, lastMoveY);

                                rect.top = Math.min(fixedControlPointY, moveControlPointY + y);
                                rect.bottom = Math.max(fixedControlPointY, moveControlPointY + y);
                                break;
                        }
                    } else {
                        x = getControlDiffX(moveControlPointX, moveX, lastMoveX);
                        y = getControlDiffY(moveControlPointY, moveY, lastMoveY);

                        rect.set(Math.min(fixedControlPointX, moveControlPointX + x),
                                Math.min(fixedControlPointY, moveControlPointY + y),
                                Math.max(fixedControlPointX, moveControlPointX + x),
                                Math.max(fixedControlPointY, moveControlPointY + y));
                    }

                    lastMoveX = moveX;
                    lastMoveY = moveY;

                    moveControlPointX += x;
                    moveControlPointY += y;
                } else {
                    rect.set(Math.min(startX, moveX),
                            Math.min(startY, moveY),
                            Math.max(startX, moveX),
                            Math.max(startY, moveY));
                }

                leftTopRect.set(rect.left - CONTROL_WIDTH, rect.top - CONTROL_WIDTH, rect.left + CONTROL_WIDTH, rect.top + CONTROL_WIDTH);
                rightTopRect.set(rect.right - CONTROL_WIDTH, rect.top - CONTROL_WIDTH, rect.right + CONTROL_WIDTH, rect.top + CONTROL_WIDTH);
                leftBottomRect.set(rect.left - CONTROL_WIDTH, rect.bottom - CONTROL_WIDTH, rect.left + CONTROL_WIDTH, rect.bottom + CONTROL_WIDTH);
                rightBottomRect.set(rect.right - CONTROL_WIDTH, rect.bottom - CONTROL_WIDTH, rect.right + CONTROL_WIDTH, rect.bottom + CONTROL_WIDTH);

                if (rect.width() > CONTROL_WIDTH * 2) {
                    topBorderRect.set(rect.left + CONTROL_WIDTH, rect.top - CONTROL_WIDTH, rect.right - CONTROL_WIDTH, rect.top + CONTROL_WIDTH);
                    bottomBorderRect.set(rect.left + CONTROL_WIDTH, rect.bottom - CONTROL_WIDTH, rect.right - CONTROL_WIDTH, rect.bottom + CONTROL_WIDTH);
                } else {
                    topBorderRect.setEmpty();
                    bottomBorderRect.setEmpty();
                }

                if (rect.height() > CONTROL_WIDTH * 2) {
                    leftBorderRect.set(rect.left - CONTROL_WIDTH, rect.top + CONTROL_WIDTH, rect.left + CONTROL_WIDTH, rect.bottom - CONTROL_WIDTH);
                    rightBorderRect.set(rect.right - CONTROL_WIDTH, rect.top + CONTROL_WIDTH, rect.right + CONTROL_WIDTH, rect.bottom - CONTROL_WIDTH);
                } else {
                    leftBorderRect.setEmpty();
                    rightBorderRect.setEmpty();
                }

                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                lastRect.set(rect);

                isControl = false;
                isMove = false;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        LogManager.i("=========onDraw=======");
//        canvas.drawRect(rect, paint);

//        canvas.save();

//        if (isMove) {
//            float x = getDiffX(rect, moveX, lastMoveX);
//            float y = getDiffY(rect, moveY, lastMoveY);
//            rect.set(rect.left + x, rect.top + y, rect.right + x, rect.bottom + y);
//
//            lastMoveX = moveX;
//            lastMoveY = moveY;
//        } else {
//            rect.set(Math.min(startX, moveX), Math.min(startY, moveY), Math.max(startX, moveX), Math.max(startY, moveY));
//        }
        boolean empty = rect.isEmpty();

        if (!empty) {
            canvas.save();
            canvas.clipRect(rect, Region.Op.DIFFERENCE);
        }

        canvas.drawColor(Color.parseColor("#99000000"));

        if (!empty) {
            canvas.restore();

//            canvas.drawRect(leftTopRect, paint);
//            canvas.drawRect(rightTopRect, paint);
//            canvas.drawRect(leftBottomRect, paint);
//            canvas.drawRect(rightBottomRect, paint);

            canvas.drawRect(rect, borderPaint);

            canvas.drawRect(rect.left - 3, rect.top - 3, rect.left - 3 + cornerWidth, rect.top - 3 + cornerHeight, cornerPaint);
            canvas.drawRect(rect.left - 3, rect.top - 3, rect.left - 3 + cornerHeight, rect.top - 3 + cornerWidth, cornerPaint);

            canvas.drawRect(rect.right + 3 - cornerWidth, rect.top - 3, rect.right + 3, rect.top - 3 + cornerHeight, cornerPaint);
            canvas.drawRect(rect.right + 3 - cornerHeight, rect.top - 3, rect.right + 3, rect.top - 3 + cornerWidth, cornerPaint);

            canvas.drawRect(rect.left - 3, rect.bottom + 3 - cornerWidth, rect.left - 3 + cornerHeight, rect.bottom + 3, cornerPaint);
            canvas.drawRect(rect.left - 3, rect.bottom + 3 - cornerHeight, rect.left - 3 + cornerWidth, rect.bottom + 3, cornerPaint);

            canvas.drawRect(rect.right + 3 - cornerHeight, rect.bottom + 3 - cornerWidth, rect.right + 3, rect.bottom + 3, cornerPaint);
            canvas.drawRect(rect.right + 3 - cornerWidth, rect.bottom + 3 - cornerHeight, rect.right + 3, rect.bottom + 3, cornerPaint);


//            if (!leftBorderRect.isEmpty())
//                canvas.drawRect(leftBorderRect, cornerPaint);
//
//            if (!topBorderRect.isEmpty())
//                canvas.drawRect(topBorderRect, cornerPaint);
//
//            if (!rightBorderRect.isEmpty())
//                canvas.drawRect(rightBorderRect, cornerPaint);
//
//            if (!bottomBorderRect.isEmpty())
//                canvas.drawRect(bottomBorderRect, cornerPaint);
        }
    }

    private float getMoveDiffX(RectF rect, float moveX, float lastMoveX) {
        float diff = moveX - lastMoveX;
        if (diff < 0) {
            if (rect.left + diff < 0) {
                diff = -rect.left;
            }
        } else {
            if (rect.right + diff > getMeasuredWidth()) {
                diff = getMeasuredWidth() - rect.right;
            }
        }
        return diff;
    }

    private float getMoveDiffY(RectF rect, float moveY, float lastMoveY) {
        float diff = moveY - lastMoveY;
        if (diff < 0) {
            if (rect.top + diff < 0) {
                diff = -rect.top;
            }
        } else {
            if (rect.bottom + diff > getMeasuredHeight()) {
                diff = getMeasuredHeight() - rect.bottom;
            }
        }
        return diff;
    }

    private float getControlDiffX(float moveControlPointX, float moveX, float lastMoveX) {
        float diff = moveX - lastMoveX;
        if (diff < 0) {
            if (moveControlPointX + diff < 0) {
                diff = -moveControlPointX;
            }
        } else {
            if (moveControlPointX + diff > getMeasuredWidth()) {
                diff = getMeasuredWidth() - moveControlPointX;
            }
        }
        return diff;
    }

    private float getControlDiffY(float moveControlPointY, float moveY, float lastMoveY) {
        float diff = moveY - lastMoveY;
        if (diff < 0) {
            if (moveControlPointY + diff < 0) {
                diff = -moveControlPointY;
            }
        } else {
            if (moveControlPointY + diff > getMeasuredHeight()) {
                diff = getMeasuredHeight() - moveControlPointY;
            }
        }
        return diff;
    }

    public RectF getRect() {
        return rect;
    }

    private int getControlPointRectPosition(float x, float y) {
        for (int i = 0; i < controlPointRects.size(); i++) {
            RectF controlPointRect = controlPointRects.get(i);
            if (controlPointRect.contains(x, y)) {
                return i;
            }
        }
        return -1;
    }
}
