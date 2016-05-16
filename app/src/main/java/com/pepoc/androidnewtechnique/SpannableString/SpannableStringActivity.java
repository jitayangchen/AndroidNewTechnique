package com.pepoc.androidnewtechnique.SpannableString;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpannableStringActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv)
    TopicTextView tv;
    @Bind(R.id.image_btn)
    Button imageBtn;
    @Bind(R.id.url_btn)
    Button urlBtn;
    @Bind(R.id.color_btn1)
    Button colorBtn1;
    @Bind(R.id.color_btn2)
    Button colorBtn2;
    @Bind(R.id.font_btn)
    Button fontBtn;
    @Bind(R.id.style_btn)
    Button styleBtn;
    @Bind(R.id.strike_btn)
    Button strikeBtn;
    @Bind(R.id.underline_btn)
    Button underlineBtn;
    @Bind(R.id.clickable_btn)
    Button clickableBtn;

    private int touchStatus = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(toolbar);

        imageBtn.setOnClickListener(this);
        urlBtn.setOnClickListener(this);
        colorBtn1.setOnClickListener(this);
        colorBtn2.setOnClickListener(this);
        fontBtn.setOnClickListener(this);
        styleBtn.setOnClickListener(this);
        strikeBtn.setOnClickListener(this);
        underlineBtn.setOnClickListener(this);
        clickableBtn.setOnClickListener(this);

//        tv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        LogManager.i("----------------ACTION_DOWN");
////                        tv.setHighlightColor(Color.BLUE);
////                        tv.invalidate();
//                        touchStatus = 0;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        LogManager.i("ACTION_UP----------------");
//                        tv.setHighlightColor(Color.TRANSPARENT);
////                        tv.invalidate();
//                        touchStatus = -1;
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.underline_btn:
                addUnderLineSpan();
                break;
            case R.id.strike_btn:
                addStrikeSpan();
                break;
            case R.id.style_btn:
                addStyleSpan();
                break;
            case R.id.font_btn:
                addFontSpan();
                break;
            case R.id.color_btn1:
                addForeColorSpan();
                break;
            case R.id.color_btn2:
                addBackColorSpan();
                break;
            case R.id.url_btn:
                addUrlSpan();
                break;
            case R.id.image_btn:
                addImageSpan();
                break;
            case R.id.clickable_btn:
                tv.clickableSpan();
                break;
        }
    }

    /**
     * 超链接
     */
    private void addUrlSpan() {
        SpannableString spanString = new SpannableString("超链接");
        URLSpan span = new URLSpan("tel:0123456789");
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 文字背景颜色
     */
    private void addBackColorSpan() {
        SpannableString spanString = new SpannableString("颜色2");
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 文字颜色
     */
    private void addForeColorSpan() {
        SpannableString spanString = new SpannableString("颜色1");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 字体大小
     */
    private void addFontSpan() {
        SpannableString spanString = new SpannableString("36号字体");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(36);
        spanString.setSpan(span, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 粗体，斜体
     */
    private void addStyleSpan() {
        SpannableString spanString = new SpannableString("BIBI");
        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
        spanString.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 删除线
     */
    private void addStrikeSpan() {
        SpannableString spanString = new SpannableString("删除线");
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }

    /**
     * 下划线
     */
    private void addUnderLineSpan() {
        SpannableString spanString = new SpannableString("下划线");
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 图片
     */
    private void addImageSpan() {
        SpannableString spanString = new SpannableString(" ");
        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }

    /**
     * 可点击文本
     */
    private void clickableSpan() {
        SpannableString spanString = new SpannableString("微博话题");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                LogManager.i("---ClickableSpan---");
//                tv.setHighlightColor(Color.BLUE);
//                spanString.setSpan(new BackgroundColorSpan(Color.BLUE), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tv.append(spanString);
//                tv.invalidate();
            }

//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setUnderlineText(false);
//                if (touchStatus == 0) {
//                    tv.setHighlightColor(Color.GREEN);
////                    tv.invalidate();
//                }
////                LogManager.i("----------updateDrawState---------" + tv.getHighlightColor() + " --- " + Color.GREEN);
//            }
        };
        spanString.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);
        tv.setMovementMethod(TopicLinkMovementMethod.getInstance());
    }

}
