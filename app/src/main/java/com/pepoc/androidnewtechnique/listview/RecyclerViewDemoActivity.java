package com.pepoc.androidnewtechnique.listview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.customview.ConstrictionView;
import com.pepoc.androidnewtechnique.log.LogManager;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDemo;
    private AdapterDemo adapterDemo;
    private int count = 20;
    private View ivTargetMove;
    private LinearLayoutManager linearLayoutManager;
    private int[] location = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        ivTargetMove = findViewById(R.id.iv_target_move);
        recyclerViewDemo = (RecyclerView) findViewById(R.id.recycler_view_demo);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDemo.setLayoutManager(linearLayoutManager);
        adapterDemo = new AdapterDemo();
        recyclerViewDemo.setAdapter(adapterDemo);
        recyclerViewDemo.setItemAnimator(new DefaultItemAnimator());

        recyclerViewDemo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//                LogManager.i("firstVisibleItemPosition === " + firstVisibleItemPosition);

                if (3 >= firstVisibleItemPosition && 3 <= lastVisibleItemPosition) {
//                    View viewByPosition = linearLayoutManager.findViewByPosition(3);
                    RecyclerView.ViewHolder viewHolderForLayoutPosition = recyclerViewDemo.findViewHolderForLayoutPosition(3);

                    recyclerViewDemo.getLocationInWindow(location);
//                    recyclerViewDemo.getLocationOnScreen(location);
//                    LogManager.i("y === " + location[1]);

//                    LogManager.i("Location", "Top === " + viewHolderForLayoutPosition.itemView.getTop());
                }
            }
        });

//        recyclerViewDemo.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//
//            float startDownY;
//            ViewConfiguration configuration = ViewConfiguration.get(RecyclerViewDemoActivity.this);
//            boolean isIntercept = false;
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                switch (e.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_DOWN");
//                        startDownY = e.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
////                        LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_MOVE");
//                        if (Math.abs(e.getY() - startDownY) <= configuration.getScaledTouchSlop()) {
//                            isIntercept = true;
//                        } else {
//                            isIntercept = false;
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_UP");
//                        isIntercept = false;
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_POINTER_DOWN");
//                        break;
////                        return true;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        LogManager.i("onInterceptTouchEvent", "MotionEvent.ACTION_POINTER_UP");
////                        break;
//                        return isIntercept;
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//
//        recyclerViewDemo.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return super.onInterceptTouchEvent(rv, e);
//            }
//        });
    }

    private class AdapterDemo extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_recycler_view, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return count;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ConstrictionView constrictionView;
        View ivTarget;

        ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text_view);
            constrictionView = (ConstrictionView) itemView.findViewById(R.id.constriction_view);
            ivTarget = itemView.findViewById(R.id.iv_target);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    adapterDemo.notifyItemRemoved(getLayoutPosition());
//                    adapterDemo.notifyItemInserted(3);


//                    executeAnimation(v, getLayoutPosition());

                    constrictionView.startAnimator();

                    int[] locationTarget = new int[2];
                    ivTarget.getLocationInWindow(locationTarget);

                    int[] location= new int[2];
                    ivTargetMove.getLocationInWindow(location);

                    ivTargetMove.setTranslationX(locationTarget[0] - location[0]);
                    ivTargetMove.setTranslationY(locationTarget[1] - location[1]);
                }
            });

            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            LogManager.i("onTouch", "MotionEvent.ACTION_DOWN");
                            break;
                        case MotionEvent.ACTION_MOVE:
//                        LogManager.i("onTouch", "MotionEvent.ACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_UP:
                            LogManager.i("onTouch", "MotionEvent.ACTION_UP");
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            LogManager.i("onTouch", "MotionEvent.ACTION_POINTER_DOWN");
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            LogManager.i("onTouch", "MotionEvent.ACTION_POINTER_UP");
                            break;
                    }
                    return false;
                }
            });
        }
    }

    private void executeAnimation(final View view, final int position) {
        final int[] location = new int[2];
        view.getLocationInWindow(location);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
//                float scale = 1.0f - (90.0f / 150.0f * value);
//                view.setScaleX(scale);
//                view.setScaleY(scale);
                view.setTranslationX(0 - location[0] * value);
                view.setTranslationY(0 - location[1] * value);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                count--;
                adapterDemo.notifyItemRemoved(position);

//                int[] location1 = new int[2];
//                view.getLocationInWindow(location1);
//                view.setTranslationX(location[0]);
//                view.setTranslationY(location[1]);
            }
        });

        valueAnimator.start();




//        Animation.AnimationListener al = new Animation.AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                adapterDemo.notifyItemRemoved(position);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        };
//
//        int initialWidth = view.getMeasuredWidth();
//        TranslateAnimation transAni=new TranslateAnimation(0.0f, 0, 0.0f, 500f);
//        transAni.setAnimationListener(al);
//        transAni.setDuration(300);
//        view.startAnimation(transAni);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
