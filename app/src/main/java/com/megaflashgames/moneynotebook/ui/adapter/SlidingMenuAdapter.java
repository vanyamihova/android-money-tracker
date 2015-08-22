package com.megaflashgames.moneynotebook.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.annotations.InjectView;
import com.megaflashgames.moneynotebook.annotations.RowView;
import com.megaflashgames.moneynotebook.ui.ViewHolder;
import com.megaflashgames.moneynotebook.ui.model.MenuItem;

import java.util.List;

/**
 * Created by vanyamihova on 04/05/2015.
 */
@RowView(R.layout.sliding_menu_item)
public class SlidingMenuAdapter extends BaseItemAdapter<MenuItem> {

    private Context mContext;

    public SlidingMenuAdapter(Context context, LayoutInflater inflater, List<MenuItem> items) {
        super(context, inflater, items);

        this.mContext = context;
    }

    @Override
    protected void bindData(MenuItem item, ViewHolder holder, int position) {
//        Drawable drawable = mContext.getResources().getDrawable(R.drawable.menu_item_shape);
//        int colorFromPosition = RainbowColor.setPosition(position).getColor();
//        drawable.setColorFilter(mContext.getResources().getColor(colorFromPosition), PorterDuff.Mode.SRC_ATOP);

        Holder h = (Holder) holder;

//        h.container.setBackgroundDrawable(drawable);

        h.icon.setText(item.icon());
//        h.title.setText(item.resTitle());
    }

    @Override
    protected ViewHolder newViewHolder() {
        return new Holder();
    }

    private static class Holder extends ViewHolder {
        @InjectView(R.id.icon)
        private TextView icon;
    }


}
