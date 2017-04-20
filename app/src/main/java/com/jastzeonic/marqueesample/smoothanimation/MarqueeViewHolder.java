package com.jastzeonic.marqueesample.smoothanimation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ptc_02008 on 2017/4/20.
 */

public class MarqueeViewHolder extends RecyclerView.ViewHolder {


    private TextView textViewContent;

    public MarqueeViewHolder(View itemView) {
        super(itemView);
        textViewContent = (TextView) itemView;
    }

    public TextView getTextViewContent() {

        return textViewContent;
    }
}
