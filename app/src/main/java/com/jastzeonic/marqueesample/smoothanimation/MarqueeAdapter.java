package com.jastzeonic.marqueesample.smoothanimation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jastzeonic.marqueesample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptc_02008 on 2017/4/20.
 */

public class MarqueeAdapter extends RecyclerView.Adapter<MarqueeViewHolder> {

    private List<String> items;
    private List<String> viewItems;


    private Context context;

    private final int itemScrollCount = 4;

    private int itemHeight;

    private int lastPosition;


    public MarqueeAdapter(Context context, List<String> items, int recyclerViewHeight) {
        this.context = context;
        this.items = items;
        this.itemHeight = countItemHeight(recyclerViewHeight, itemScrollCount);
        buildMarqueeItem(items);
    }

    private int countItemHeight(int recyclerViewHeight, int itemScrollCount) {
        return recyclerViewHeight / itemScrollCount;
    }

    private void buildMarqueeItem(List<String> items) {
        viewItems = new ArrayList<>();

        int viewItemCount = 0;
        int itemCount = 0;

        while (viewItemCount < (items.size() + itemScrollCount)) {
            viewItems.add(items.get(itemCount));
            if (itemCount >= items.size() - 1) {
                itemCount = 0;
            } else {
                itemCount++;
            }
            viewItemCount++;
        }
    }

    @Override
    public MarqueeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mqrquee_text_view, parent, false);
        view.getLayoutParams().height = itemHeight;
        return new MarqueeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MarqueeViewHolder holder, int position) {
        holder.getTextViewContent().setText(viewItems.get(position));

//        Animation animation = AnimationUtils.loadAnimation(context,
//                (position > lastPosition) ? R.anim.anim_up_from_bottom
//                        : R.anim.anim_down_from_top);
//        holder.itemView.startAnimation(animation);
//        lastPosition = position;


    }

    @Override
    public int getItemCount() {
        return viewItems != null ? viewItems.size() : 0;
    }

    public int getItemHeight() {
        return itemHeight;
    }
}
