package com.megaflashgames.budgethelper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.annotations.RowView;
import com.megaflashgames.budgethelper.model.Car;
import com.megaflashgames.budgethelper.ui.ViewHolder;
import com.megaflashgames.budgethelper.ui.dialog.BaseDialog;
import com.megaflashgames.budgethelper.ui.dialog.DialogEditCarCost;
import com.megaflashgames.budgethelper.util.DateAndTimeUtil;

import java.util.List;

/**
 * Created by vanyamihova on 06/05/2015.
 */
@RowView(R.layout.adapter_travel_expenses)
public class TravelExpensesAdapter extends BaseItemAdapter<Car> {

    private OnAdapterListener mListener;

    public TravelExpensesAdapter(Context context, LayoutInflater inflater,
                          List<Car> items) {
        super(context, inflater, items);
    }

    @Override
    protected void bindData(final Car item, ViewHolder holder, int position) {
        Holder h = (Holder) holder;

        h.icon.setText(item.getType().getIcon());
        h.total.setText(String.valueOf(item.getTotal()));

        if(item.getType().isNeedKilometers()) {
            h.kilometers.setText(String.valueOf(item.getKilometers()));
            h.kilometers.setVisibility(View.VISIBLE);
        } else {
            h.kilometers.setVisibility(View.INVISIBLE);
        }

        DateAndTimeUtil dateAndTimeUtil = DateAndTimeUtil.GetInstance();
        dateAndTimeUtil.setTimeInMillis(item.getModifyAt());
        h.timestamp.setText(dateAndTimeUtil.getDate());

//        TODO:
//        final CarCost itemForEdit = item;
//
        h.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogEditCarCost dialog = DialogEditCarCost.dialogInstance(mContext, item);
                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
                    @Override
                    public void onDialogClosed(boolean cancellation) {
                        mListener.onDialogClosed(cancellation);
                    }
                });
                return false;
            }
        });
    }

    @Override
    protected ViewHolder newViewHolder() {
        return new Holder();
    }



    private static class Holder extends ViewHolder {
        @InjectView(R.id.container)
        View container;
        @InjectView(R.id.tv_icon)
        TextView icon;
        @InjectView(R.id.tv_total)
        TextView total;
        @InjectView(R.id.tv_kilometers)
        TextView kilometers;
        @InjectView(R.id.tv_timestamp)
        TextView timestamp;
    }

    public interface OnAdapterListener {
        public void onDialogClosed(boolean cancellation);
    }

    public void setOnAdapterListener(OnAdapterListener listener) {
        mListener = listener;
    }

}
