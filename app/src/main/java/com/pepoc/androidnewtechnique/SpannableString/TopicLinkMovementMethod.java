package com.pepoc.androidnewtechnique.SpannableString;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Yangchen on 2016/3/17.
 */
public class TopicLinkMovementMethod extends LinkMovementMethod {

    // 点击状态 true为点击  false则相反
//    private boolean status = false;

    private static TopicLinkMovementMethod sInstance;

    public static MovementMethod getInstance() {
        if (sInstance == null)
            sInstance = new TopicLinkMovementMethod();

        return sInstance;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= widget.getTotalPaddingLeft();
        y -= widget.getTotalPaddingTop();

        x += widget.getScrollX();
        y += widget.getScrollY();

        Layout layout = widget.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);


        ClickableSpan[] spans = buffer.getSpans(off, off, ClickableSpan.class);
        Log.i("onTouchEvent", "spans.length === " + spans.length);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (spans.length > 0) {
//                    status = true;
                    ClickableSpan span = spans[0];
                    Selection.setSelection(buffer, buffer.getSpanStart(span), buffer.getSpanEnd(span));
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                if (status) {
//                    if (spans.length > 0) {
//                        ClickableSpan span = spans[0];
//                        Selection.setSelection(buffer, buffer.getSpanStart(span), buffer.getSpanEnd(span));
//                    } else {
//                        Selection.removeSelection(buffer);
//                        status = false;
//                    }
//                }
                break;
            case MotionEvent.ACTION_UP:
//                status = false;
                Selection.removeSelection(buffer);
                break;
        }
        return super.onTouchEvent(widget, buffer, event);
    }
}
