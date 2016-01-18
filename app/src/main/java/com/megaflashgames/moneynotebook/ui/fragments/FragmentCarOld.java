//package com.megaflashgames.moneynotebook.ui.fragments;
//
//import android.os.Bundle;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import com.megaflashgames.moneynotebook.R;
//import com.megaflashgames.moneynotebook.annotations.ContentView;
//import com.megaflashgames.moneynotebook.annotations.InjectView;
//import com.megaflashgames.moneynotebook.db.DatabaseService;
//import com.megaflashgames.moneynotebook.db.model.Car;
//import com.megaflashgames.moneynotebook.ui.adapter.CarAdapter;
//import com.megaflashgames.moneynotebook.ui.adapter.CarAdapterOld;
//import com.megaflashgames.moneynotebook.ui.model.ScreenSettings;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * Created by vanyamihova on 05/05/2015.
// */
//@ContentView(R.layout.fragment_car)
//public class FragmentCarOld extends FragmentBase {
//
//    public static final ScreenSettings MENU_TAG = ScreenSettings.CAR;
//    public static final String FRAGMENT_TAG = FragmentCarOld.class.getSimpleName();
//
//    private CarAdapter mAdapter;
//    private List<Car> mAllCars;
//
//    @InjectView(R.id.lv_travelExpensesList)
//    RecyclerView mTravelExpensesList;
//
//    public static FragmentBase newInstance() {
//        FragmentBase fragment = new FragmentCarOld();
//
//        return fragment;
//    }
//
//    @Override
//    public void onViewCreated(View container, Bundle savedInstanceState) {
//        super.onViewCreated(container, savedInstanceState);
//
//        setNavigationInRight();
//
//        mAllCars = DatabaseService.GetInstance().getAllCars();
//        Collections.reverse(mAllCars);
//
//        mAdapter = new CarAdapterOld(getActivity(), getActivity().getLayoutInflater(), mAllCars);
//        mAdapter.setOnAdapterListener(new CarAdapterOld.OnAdapterListener() {
//            @Override
//            public void onDialogClosed(boolean cancellation) {
//                if(!cancellation) {
//                    resetListItems();
//                }
//            }
//        });
//
//        mTravelExpensesList.setAdapter(mAdapter);
//    }
//
//    private void setNavigationInRight() {
////        mNavigation = this.getFragmentNavigation();
////        mNavigation.createRearrange();
////        mNavigation.createAddButton();
////        mNavigation.setOnAddButtonListener(new FragmentNavigation.OnAddButtonListener() {
////            @Override
////            public void onClickAddButton() {
////                DialogAddCarCost dialog = DialogAddCarCost.dialogInstance(getActivity());
////                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
////                    @Override
////                    public void onDialogClosed(boolean cancellation) {
////                        if (!cancellation) {
////                            resetListItems();
////                        }
////                    }
////                });
////            }
////        });
//    }
//
//    private void resetListItems() {
//        if(mAdapter != null) {
//            mAllCars = DatabaseService.GetInstance().getAllCars();
//            Collections.reverse(mAllCars);
//            mAdapter.setItems(mAllCars);
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//}
