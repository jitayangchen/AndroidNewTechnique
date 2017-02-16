package com.pepoc.androidnewtechnique.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.pepoc.androidnewtechnique.R;

/**
 * @author Arlenyang
 * @Description:
 * @date 2016/11/10 12:00
 * @copyright TCL-MIG
 */
public class MatrixSimple extends View {

    private Paint paint;
    private Bitmap bitmap;
    private BitmapFactory.Options options;
    private Virus[] virusFirst, virusSecond, virusThird, virusFourth;

    public MatrixSimple(Context context) {
        super(context);

        init();
    }

    public MatrixSimple(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MatrixSimple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        bitmap = BitmapFactory.decodeStream(getContext().getResources().openRawResource(+R.drawable.privacy_scanning_shield), null, options);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.shield, options);

        initVirusData();
    }

    private void initVirusData() {
        Bitmap virus1 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.virus1, options);
        Bitmap virus2 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.virus2, options);
        Bitmap virus3 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.virus3, options);


//        virusFirst = new Virus[5];
//        Virus virus = new Virus(virus2, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 109f / 176f, 110f / 461f, 185f / 510f, 11.2f);
//        virusFirst[0] = virus;
//        virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 57f / 176f, 242f / 461f, 147f / 510f, 14.4f);
//        virusFirst[1] = virus;
//        virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 84f / 176f, 267f / 461f, 223f / 510f, 360f - 14.5f);
//        virusFirst[2] = virus;
//        virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 49f / 176f, 151f / 461f, 309f / 510f, 360f - 13f);
//        virusFirst[3] = virus;
//        virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 82f / 176f, 219f / 461f, 315f / 510f, 15f);
//        virusFirst[4] = virus;

//        virusSecond = new Virus[2];
//        Virus virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 60f / 176f, 253f / 461f, 149f / 510f, 14.2f);
//        virusSecond[0] = virus;
//        virus = new Virus(virus2, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 1.0f, 141f / 461f, 233f / 510f, 9.2f);
//        virusSecond[1] = virus;

//        virusThird = new Virus[3];
//        Virus virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 114f / 176f, 92f / 461f, 190f / 510f, 12.6f);
//        virusThird[0] = virus;
//        virus = new Virus(virus1, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 83f / 176f, 209f / 461f, 140f / 510f, 360f - 11.6f);
//        virusThird[1] = virus;
//        virus = new Virus(virus2, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 151f / 176f, 191f / 461f, 260f / 510f, 360f - 13.6f);
//        virusThird[2] = virus;

        virusFourth = new Virus[2];
        Virus virus = new Virus(virus3, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 132f / 174f, 107f / 461f, 291f / 510f, 18.6f);
        virusFourth[0] = virus;
        virus = new Virus(virus3, bitmap.getWidth(), bitmap.getHeight(), -1, -1, 90f / 174f, 241f / 461f, 343f / 510f, 360f - 20f);
        virusFourth[1] = virus;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*************** old code **************/
        /*bgPaint.setColor(Color.RED);
        canvas.concat(matrix);
        canvas.drawRect(0, 0, 100, 100, bgPaint);

//        canvas.save();
//        canvas.concat(matrix);
        canvas.drawRect(50, 100, 200, 200, bgPaint);
//        canvas.restore();

//        canvas.drawRect(400, 400, 500, 500, bgPaint);*/





        canvas.drawBitmap(bitmap, (getWidth() - bitmap.getWidth()) / 2, (getHeight() - bitmap.getHeight()) / 2, null);

        for (int i = 0; i < virusFourth.length; i++) {
            virusFourth[i].setParentViewLeft((getWidth() - bitmap.getWidth()) / 2.0f);
            virusFourth[i].setParentViewTop((getHeight() - bitmap.getHeight()) / 2.0f);
            virusFourth[i].onDraw(canvas);
        }

    }
}
