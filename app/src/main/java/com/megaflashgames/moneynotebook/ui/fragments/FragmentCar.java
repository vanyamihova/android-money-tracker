package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.annotations.ContentView;
import com.megaflashgames.moneynotebook.annotations.InjectView;
import com.megaflashgames.moneynotebook.db.DatabaseService;
import com.megaflashgames.moneynotebook.model.Car;
import com.megaflashgames.moneynotebook.ui.adapter.TravelExpensesAdapter;
import com.megaflashgames.moneynotebook.ui.dialog.BaseDialog;
import com.megaflashgames.moneynotebook.ui.dialog.DialogAddCarCost;

import java.util.Collections;
import java.util.List;

/**
 * Created by vanyamihova on 05/05/2015.
 */
@ContentView(R.layout.fragment_car)
public class FragmentCar extends FragmentBase {

    public static final String FRAGMENT_TAG = FragmentCar.class.getSimpleName();

    private TravelExpensesAdapter mAdapter;


    private List<Car> mAllCars;

    @InjectView(R.id.lv_travelExpensesList)
    ListView mTravelExpensesList;

    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentCar();

        return fragment;
    }

    @Override
    public void onViewCreated(View container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);

        setNavigationInRight();

        mAllCars = DatabaseService.GetInstance().getAllCars();
        Collections.reverse(mAllCars);

        mAdapter = new TravelExpensesAdapter(getActivity(), getActivity().getLayoutInflater(), mAllCars);
        mAdapter.setOnAdapterListener(new TravelExpensesAdapter.OnAdapterListener() {
            @Override
            public void onDialogClosed(boolean cancellation) {
                if(!cancellation) {
                    resetListItems();
                }
            }
        });

        mTravelExpensesList.setAdapter(mAdapter);
    }

    private void setNavigationInRight() {
        mNavigation = this.getFragmentNavigation();
        mNavigation.createRearrange();
        mNavigation.createAddButton();
        mNavigation.setOnAddButtonListener(new FragmentNavigation.OnAddButtonListener() {
            @Override
            public void onClickAddButton() {
                DialogAddCarCost dialog = DialogAddCarCost.dialogInstance(getActivity());
                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
                    @Override
                    public void onDialogClosed(boolean cancellation) {
                        if (!cancellation) {
                            resetListItems();
                        }
                    }
                });
            }
        });
    }

    private void resetListItems() {
        if(mAdapter != null) {
            mAllCars = DatabaseService.GetInstance().getAllCars();
            Collections.reverse(mAllCars);
            mAdapter.setItems(mAllCars);
            mAdapter.notifyDataSetChanged();
        }
    }

}
