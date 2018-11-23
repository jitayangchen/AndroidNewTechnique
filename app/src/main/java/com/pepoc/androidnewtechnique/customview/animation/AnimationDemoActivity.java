package com.pepoc.androidnewtechnique.customview.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;

public class AnimationDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private View btnAnimationTest;
    private ViewPropertyAnimator btnAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);

        init();
    }

    private void init() {
        btnAnimationTest = findViewById(R.id.btn_animation_test);

        btnAnimationTest.setOnClickListener(this);
        btnAnimate = btnAnimationTest.animate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_animation_test:
//                btnAnimate.setDuration(3000).setInterpolator(new CustomInterpolator()).translationYBy(500);
//                btnAnimate.xBy(100);
//                btnAnimate.translationXBy(100);
//                btnAnimate.zBy(100);
//                btnAnimate.translationZBy(100);
//                btnAnimate.rotationBy(30);
//                btnAnimate.scaleX(2);
//                btnAnimate.scaleY(2);
//                btnAnimate.alpha(0.5f);

//                animatorSetTest();

                valueAnimatorTest();
                break;
        }
    }

    private void animatorSetTest() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(btnAnimationTest, "scaleX", 3f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(btnAnimationTest, "scaleY", 3f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
//        animatorSet.play(objectAnimator1).with(objectAnimator2);
//        animatorSet.playTogether(objectAnimator1, objectAnimator2);
//        animatorSet.play(objectAnimator1).after(objectAnimator2);
        animatorSet.play(objectAnimator1).before(objectAnimator2);
        animatorSet.start();
    }

    private void valueAnimatorTest() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 10f, 20f, 30f, 40f, 50f, 100f);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, -0.5f);
        valueAnimator.setInterpolator(new CustomInterpolator());
//        valueAnimator.setInterpolator(null);
//        valueAnimator.setEvaluator(new CustomTypeEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = (float) animation.getAnimatedValue();
                LogManager.i("animatedFraction = " + animatedFraction);
                btnAnimationTest.setTranslationX(animatedFraction);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    class CustomInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float input) {
            LogManager.i("animatedFraction input = " + input);
            int p0 = 0;
            int p1 = 1;
            int m0 = 4;
            int m1 = 4;

            float t = input;
            float t2 = t*t;
            float t3 = t2*t;
            return (2*t3 - 3*t2 + 1)*p0 + (t3-2*t2+t)*m0 + (-2*t3+3*t2)*p1 + (t3-t2)*m1;


//            int cycles = 1;
//            return (float) Math.sin(2 * cycles * Math.PI * input);
        }
    }

    class CustomTypeEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            LogManager.i("animatedFraction fraction = " + fraction + " - " + startValue + " : " + endValue);
            return fraction;
        }
    }


}
