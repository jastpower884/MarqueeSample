package com.jastzeonic.marqueesample.smoothanimation;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by ptc_02008 on 2017/4/20.
 */

public class SmoothScrollLinearLayoutManager extends LinearLayoutManager {
    private float millisecondsPerInch = 1500f;
    private Context mContext;
    private int targetPosition;

    public SmoothScrollLinearLayoutManager(Context context) {
        super(context);
        mContext = context;
    }

    public SmoothScrollLinearLayoutManager(Context context, float millisecondsPerInch) {
        super(context);
        mContext = context;
        this.millisecondsPerInch = millisecondsPerInch;
    }


    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, RecyclerView.State state, final int position) {
        Log.v("MainActivity", "targetPosition:" + position);
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(mContext) {
            //This controls the direction in which smoothScroll looks
            //for your view
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {

                Log.v("MainActivity", "targetPosition:" + targetPosition);
                SmoothScrollLinearLayoutManager.this.targetPosition = targetPosition;
                return SmoothScrollLinearLayoutManager.this
                        .computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected int getVerticalSnapPreference() {
                Log.v("MainActivity", "getVerticalSnapPreference:");
//                recyclerView.scrollToPosition(0);
//                recyclerView.smoothScrollToPosition(SmoothScrollLinearLayoutManager.this.targetPosition);
                return SNAP_TO_START;
            }

            //This returns the milliseconds it takes to
            //scroll one pixel.
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                Log.v("MainActivity", "targetPosition:" + displayMetrics.densityDpi);
                return millisecondsPerInch / displayMetrics.densityDpi;
            }


        };

        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }
}
