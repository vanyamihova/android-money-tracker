package com.megaflashgames.moneynotebook.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.db.model.Car;
import com.megaflashgames.moneynotebook.ui.dialog.BaseDialog;
import com.megaflashgames.moneynotebook.ui.dialog.DialogEditCarCost;
import com.megaflashgames.moneynotebook.util.DateAndTimeUtil;
import com.megaflashgames.moneynotebook.util.IntegerUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vanya Mihova on 11/22/2015.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    List<Car> allCars;

    public CarAdapter(List<Car> list) {
        this.allCars = list;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = allCars.get(position);

        holder.icon.setText(car.type.getIcon());
        holder.title.setText(car.type.getText());
        holder.total.setText(String.format("%.2f", car.total));

        String subInfo = "";

        if(!IntegerUtils.isEmpty(car.kilometers))
            subInfo += "km: " + car.kilometers;

        if(!TextUtils.isEmpty(subInfo)) {
            holder.subInfo.setVisibility(View.VISIBLE);
            holder.subInfo.setText(subInfo);
        } else {
            holder.subInfo.setVisibility(View.GONE);
        }

//        if(item.getType().isNeedKilometers()) {
//            h.kilometers.setText(String.valueOf(item.getKilometers()));
//            h.kilometers.setVisibility(View.VISIBLE);
//        } else {
//            h.kilometers.setVisibility(View.INVISIBLE);
//        }

        DateAndTimeUtil dateAndTimeUtil = DateAndTimeUtil.GetInstance();
        dateAndTimeUtil.setTimeInMillis(car.modifyAt);
        holder.timestamp.setText(dateAndTimeUtil.getDate());

//        TODO:
//        final CarCost itemForEdit = item;

//        h.container.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                DialogEditCarCost dialog = DialogEditCarCost.dialogInstance(mContext, item);
//                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
//                    @Override
//                    public void onDialogClosed(boolean cancellation) {
//                        mListener.onDialogClosed(cancellation);
//                    }
//                });
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return allCars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.card_view)CardView cardView;
        @Bind(R.id.tv_icon)TextView icon;
        @Bind(R.id.tv_total)TextView total;
        @Bind(R.id.tv_timestamp)TextView timestamp;
        @Bind(R.id.tv_title)TextView title;
        @Bind(R.id.tv_subInfo)TextView subInfo;

        public CarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Car car = allCars.get(getAdapterPosition());

            DialogEditCarCost dialog = DialogEditCarCost.dialogInstance(v.getContext(), car);
            dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
                @Override
                public void onDialogClosed(boolean cancellation) {
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
