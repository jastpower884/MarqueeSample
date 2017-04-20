package com.jastzeonic.marqueesample;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.animation.LinearInterpolator;

import com.jastzeonic.marqueesample.smoothanimation.MarqueeAdapter;
import com.jastzeonic.marqueesample.smoothanimation.SmoothScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class SmoothAnimationActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_animation);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        List<String> items = new ArrayList<>();
        items.add("報數！");
        items.add("1");
        items.add("2");
        items.add("3");
        items.add("4");
        items.add("5");
        items.add("6");
        items.add("7");
        items.add("8");
        items.add("再來一次");


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int screenHeight = size.y;
        int recyclerViewHeight = screenHeight / 5;
        recyclerView.getLayoutParams().height = recyclerViewHeight;

        MarqueeAdapter adapter = new MarqueeAdapter(this, items, recyclerViewHeight);
        final SmoothScrollLinearLayoutManager manager = new SmoothScrollLinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        final int itemSize = items.size();

        final int scrollingHeight = (adapter.getItemHeight() * items.size()) + recyclerViewHeight;

        Log.v("MainActivity", "itemSize:" + itemSize);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(itemSize + 1);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(itemSize * 1500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        Log.v("TAG", "animatedValue:" + animatedValue);
                        if (animatedValue == 0) {
                            recyclerView.scrollToPosition(0);
                            animatedValue = 1;
                        }
                        recyclerView.smoothScrollToPosition(animatedValue);

                    }
                }
        );
        valueAnimator.start();

    }


}
