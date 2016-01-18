//package com.megaflashgames.moneynotebook.ui.adapter;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import com.megaflashgames.moneynotebook.R;
//import com.megaflashgames.moneynotebook.annotations.InjectView;
//import com.megaflashgames.moneynotebook.annotations.RowView;
//import com.megaflashgames.moneynotebook.db.model.Car;
//import com.megaflashgames.moneynotebook.ui.ViewHolder;
//import com.megaflashgames.moneynotebook.util.DateAndTimeUtil;
//import com.megaflashgames.moneynotebook.util.IntegerUtils;
//
//import java.util.List;
//
///**
// * Created by vanyamihova on 06/05/2015.
// */
//@RowView(R.layout.adapter_car)
//public class CarAdapterOld extends BaseItemAdapter<Car> {
//
//    private OnAdapterListener mListener;
//
//    public CarAdapterOld(Context context, LayoutInflater inflater,
//                         List<Car> items) {
//        super(context, inflater, items);
//    }
//
//    @Override
//    protected void bindData(final Car item, ViewHolder holder, int position) {
//        Holder h = (Holder) holder;
//
//        h.icon.setText(item.type.getIcon());
//        h.title.setText(item.type.getText());
//        h.total.setText(String.format("%.2f", item.total));
//
//        String subInfo = "";
//
//        if(!IntegerUtils.isEmpty(item.kilometers))
//            subInfo += "km: " + item.kilometers;
//
//        if(!TextUtils.isEmpty(subInfo)) {
//            h.subInfo.setVisibility(View.VISIBLE);
//            h.subInfo.setText(subInfo);
//        } else {
//            h.subInfo.setVisibility(View.GONE);
//        }
//
////        if(item.getType().isNeedKilometers()) {
////            h.kilometers.setText(String.valueOf(item.getKilometers()));
////            h.kilometers.setVisibility(View.VISIBLE);
////        } else {
////            h.kilometers.setVisibility(View.INVISIBLE);
////        }
//
//        DateAndTimeUtil dateAndTimeUtil = DateAndTimeUtil.GetInstance();
//        dateAndTimeUtil.setTimeInMillis(item.modifyAt);
//        h.timestamp.setText(dateAndTimeUtil.getDate());
//
////        TODO:
////        final CarCost itemForEdit = item;
//
////        h.container.setOnLongClickListener(new View.OnLongClickListener() {
////            @Override
////            public boolean onLongClick(View v) {
////                DialogEditCarCost dialog = DialogEditCarCost.dialogInstance(mContext, item);
////                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
////                    @Override
////                    public void onDialogClosed(boolean cancellation) {
////                        mListener.onDialogClosed(cancellation);
////                    }
////                });
////                return false;
////            }
////        });
//    }
//
//    @Override
//    protected ViewHolder newViewHolder() {
//        return new Holder();
//    }
//
//
//
//    private static class Holder extends ViewHolder {
//        @InjectView(R.id.tv_icon)
//        TextView icon;
//        @InjectView(R.id.tv_total)
//        TextView total;
//        @InjectView(R.id.tv_timestamp)
//        TextView timestamp;
//        @InjectView(R.id.tv_title)
//        TextView title;
//        @InjectView(R.id.tv_subInfo)
//        TextView subInfo;
//    }
//
//    public interface OnAdapterListener {
//        public void onDialogClosed(boolean cancellation);
//    }
//
//    public void setOnAdapterListener(OnAdapterListener listener) {
//        mListener = listener;
//    }
//
//}
