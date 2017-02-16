package com.pepoc.androidnewtechnique.beziercurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class BezierCurveActivity extends AppCompatActivity {

    private BezierCurveView bezierCurveView;
    private Button btnRecordData;
    private ImageView ivTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_curve);

        init();
    }

    private void init() {
        bezierCurveView = (BezierCurveView) findViewById(R.id.bezier_curve_view);
        btnRecordData = (Button) findViewById(R.id.btn_record_data);
        ivTest = (ImageView) findViewById(R.id.iv_test);

        btnRecordData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] startPoint = bezierCurveView.getStartPoint();
                float[] firstPoint = bezierCurveView.getFirstPoint();
                float[] secondPoint = bezierCurveView.getSecondPoint();
                float[] endPoint = bezierCurveView.getEndPoint();

                float imageViewW = ivTest.getWidth();
                float imageViewH = ivTest.getHeight();

                LogManager.i("startPoint = " + (startPoint[0] / imageViewW) + " --- " + ((imageViewH - startPoint[1]) / imageViewH));
                LogManager.i("firstPoint = " + (firstPoint[0] / imageViewW) + " --- " + ((imageViewH - firstPoint[1]) / imageViewH));
                LogManager.i("secondPoint = " + (secondPoint[0] / imageViewW) + " --- " + ((imageViewH - secondPoint[1]) / imageViewH));
                LogManager.i("endPoint = " + (endPoint[0] / imageViewW) + " --- " + ((imageViewH - endPoint[1]) / imageViewH));

//                LogManager.i("startPoint = " + (startPoint[0]) + " --- " + (imageViewH - startPoint[1]));
//                LogManager.i("firstPoint = " + (firstPoint[0]) + " --- " + (imageViewH - firstPoint[1]));
//                LogManager.i("secondPoint = " + (secondPoint[0]) + " --- " + (imageViewH - secondPoint[1]));
//                LogManager.i("endPoint = " + (endPoint[0]) + " --- " + (imageViewH - endPoint[1]));
            }
        });
    }
}
