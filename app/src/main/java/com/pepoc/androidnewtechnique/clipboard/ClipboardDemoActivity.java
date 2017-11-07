package com.pepoc.androidnewtechnique.clipboard;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class ClipboardDemoActivity extends AppCompatActivity {

    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard_demo);

        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        int itemCount = clipboardManager.getPrimaryClip().getItemCount();
        LogManager.i("itemCount === " + itemCount);

//        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
//            @Override
//            public void onPrimaryClipChanged() {
//                ClipData primaryClip = clipboardManager.getPrimaryClip();
//                if (clipboardManager.hasPrimaryClip() && primaryClip.getItemCount() > 0) {
//                    for (int i = 0; i < primaryClip.getItemCount(); i++) {
//                        CharSequence addedText = primaryClip.getItemAt(i).getText();
//                        LogManager.i("ClipboardData", addedText.toString());
//                    }
//
//
//                }
//            }
//        });
    }
}
