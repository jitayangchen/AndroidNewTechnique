package com.pepoc.androidnewtechnique.keyboardinput;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pepoc.androidnewtechnique.MainActivity;
import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

import java.util.Objects;

public class EditTextDemoActivity extends Activity {

    private EditText etTestInput;
    private int lastBottom = 0;
    private int keyboardHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_demo);

//        FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
//        View view = LayoutInflater.from(this).inflate(R.layout.keyboard, decorView, false);
//        decorView.addView(view);

        etTestInput = findViewById(R.id.et_test_input);
        etTestInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        etTestInput.setFocusable(false);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                etTestInput.setVisibility(View.VISIBLE);

                etTestInput.setFocusableInTouchMode(true);
                etTestInput.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etTestInput, 0);

                etTestInput.setAlpha(1.0f);
            }
        });

        findViewById(R.id.btn_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etTestInput.getWindowToken(), InputMethodManager.SHOW_FORCED);
//                etTestInput.setVisibility(View.GONE);

                etTestInput.setAlpha(0f);
            }
        });

//        etTestInput.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                LogManager.i("onLayoutChange bottom = " + bottom);
//                LogManager.i("onLayoutChange oldBottom = " + oldBottom);
//            }
//        });


        final Rect rect = new Rect();
        final View rootView = getWindow().getDecorView();
        final DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getWindowVisibleDisplayFrame(rect);
                LogManager.i("onGlobalLayout onGlobalLayout rect = " + rect.toString());
                LogManager.i("onGlobalLayout onGlobalLayout outMetrics.heightPixels = " + outMetrics.heightPixels);

                if (lastBottom != 0 && lastBottom < rect.bottom) {
                    keyboardHeight = rect.bottom - lastBottom;

                    etTestInput.setAlpha(0f);
                }
                lastBottom = rect.bottom;
            }
        });

        etTestInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                switch (i) {
//                    case EditorInfo.IME_ACTION_SEARCH:
                        Toast.makeText(EditTextDemoActivity.this, "" + i,Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//                }
                return true;
            }
        });

//        etTestInput.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Toast.makeText(EditTextDemoActivity.this, "keyCode = " + keyCode,Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        etTestInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", "word = " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            throw new Exception("onResume");
        } catch (Exception e) {
            Log.i("NYangchen", "onResume", e);
        }
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        Toast.makeText(EditTextDemoActivity.this, "keyCode = " + event.getKeyCode(),Toast.LENGTH_SHORT).show();
//        return super.dispatchKeyEvent(event);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i("onKeyT", "onKeyDown keyCode = " + keyCode);
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        Log.i("onKeyT", "onKeyUp keyCode = " + keyCode);
//        return super.onKeyUp(keyCode, event);
//    }
}
