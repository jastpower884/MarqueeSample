package com.jastzeonic.marqueesample;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewAnimatorActivity extends AppCompatActivity {


    private ScrollView scrollView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_animator);

        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);

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
        int scrollViewHeight = screenHeight / 5;
        scrollView.getLayoutParams().height = scrollViewHeight;


        List<String> viewContentItems = new ArrayList<>();

        int viewItemCount = 0;
        int itemCount = 0;

        int itemScrollCount = 4;

        while (viewItemCount < (items.size() + itemScrollCount)) {
            viewContentItems.add(items.get(itemCount));
            if (itemCount >= items.size() - 1) {
                itemCount = 0;
            } else {
                itemCount++;
            }
            viewItemCount++;
        }


        int itemHeight = scrollViewHeight / itemScrollCount;
        for (String viewContentItem : viewContentItems) {

            TextView contextText = (TextView) LayoutInflater.from(this).inflate(R.layout.item_mqrquee_text_view, null, false);
            contextText.setText(viewContentItem);

            linearLayout.addView(contextText);
            contextText.getLayoutParams().height = itemHeight;

//            animatorHeight += itemHeight;
        }
        int animatorHeight = itemHeight * (viewContentItems.size() - 4);

        Log.v("MainActivity", "animatorHeight:" + animatorHeight);

        ObjectAnimator mAnimatorBackgroundMoving = ObjectAnimator.ofInt(scrollView, "scrollY", 0, animatorHeight);
        mAnimatorBackgroundMoving.setRepeatMode(ObjectAnimator.RESTART);
        mAnimatorBackgroundMoving.setRepeatCount(ObjectAnimator.INFINITE);
        mAnimatorBackgroundMoving.setDuration(9000);
        mAnimatorBackgroundMoving.setInterpolator(new LinearInterpolator());
        mAnimatorBackgroundMoving.start();


    }
}
