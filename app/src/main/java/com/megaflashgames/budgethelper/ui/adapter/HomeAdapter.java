package com.megaflashgames.budgethelper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.annotations.RowView;
import com.megaflashgames.budgethelper.model.Car;
import com.megaflashgames.budgethelper.model.Home;
import com.megaflashgames.budgethelper.ui.ViewHolder;
import com.megaflashgames.budgethelper.util.DateAndTimeUtil;

import java.util.List;

/**
 * Created by vanyamihova on 06/05/2015.
 */
@RowView(R.layout.adapter_travel_expenses)
public class HomeAdapter extends BaseItemAdapter<Home> {

    private OnAdapterListener mListener;

    public HomeAdapter(Context context, LayoutInflater inflater,
                       List<Home> items) {
        super(context, inflater, items);
    }

    @Override
    protected void bindData(Home item, ViewHolder holder, int position) {
        Holder h = (Holder) holder;


    }

    @Override
    protected ViewHolder newViewHolder() {
        return new Holder();
    }



    private static class Holder extends ViewHolder {
        @InjectView(R.id.container)
        View container;
        @InjectView(R.id.tv_description)
        TextView description;
        @InjectView(R.id.tv_spentMoney)
        TextView spentMoney;
    }

    public interface OnAdapterListener {
        public void onDialogClosed(boolean cancellation);
    }

    public void setOnAdapterListener(OnAdapterListener listener) {
        mListener = listener;
    }

}
