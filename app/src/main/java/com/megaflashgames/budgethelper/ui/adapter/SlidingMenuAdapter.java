package com.megaflashgames.budgethelper.ui.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.annotations.RowView;
import com.megaflashgames.budgethelper.ui.ViewHolder;
import com.megaflashgames.budgethelper.ui.model.MenuItem;
import com.megaflashgames.budgethelper.ui.model.RainbowColor;

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
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.menu_item_shape);
        int colorFromPosition = RainbowColor.setPosition(position).getColor();
        drawable.setColorFilter(mContext.getResources().getColor(colorFromPosition), PorterDuff.Mode.SRC_ATOP);

        Holder h = (Holder) holder;

        h.container.setBackgroundDrawable(drawable);

        h.icon.setText(item.icon());
        h.title.setText(item.resTitle());
    }

    @Override
    protected ViewHolder newViewHolder() {
        return new Holder();
    }

    private static class Holder extends ViewHolder {
        @InjectView(R.id.container_menuItem)
        LinearLayout container;
        @InjectView(R.id.icon)
        private TextView icon;
        @InjectView(R.id.text_title)
        private TextView title;
    }


}
