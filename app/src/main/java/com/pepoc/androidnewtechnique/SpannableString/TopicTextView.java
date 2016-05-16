package com.pepoc.androidnewtechnique.SpannableString;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.log.LogManager;

/**
 * Created by Yangchen on 2016/2/1.
 */
public class TopicTextView extends TextView {

    private int touchStatus = -1;

    public TopicTextView(Context context) {
        super(context);
    }

    public TopicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStatus = 0;
                LogManager.i("TopicTextView === " + event.getX() + " - " + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                touchStatus = -1;
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 可点击文本
     */
    public void clickableSpan() {
        SpannableString spanString = new SpannableString("微博话题嘎嘎嘎嘎");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                LogManager.i("---ClickableSpan---");
//                Selection.setSelection(spanString, 3, 8);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (touchStatus == 0) {
                    ds.bgColor = Color.BLUE;
                } else {
                    ds.bgColor = Color.TRANSPARENT;
                }
            }
        };
        spanString.setSpan(span, 3, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getText());
        stringBuilder.insert(stringBuilder.length(), spanString);
        setText(stringBuilder);
        setMovementMethod(TopicLinkMovementMethod.getInstance());
    }
}
