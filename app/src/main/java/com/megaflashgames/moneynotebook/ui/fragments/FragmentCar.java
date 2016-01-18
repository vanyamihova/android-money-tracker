package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.db.DatabaseService;
import com.megaflashgames.moneynotebook.db.model.Car;
import com.megaflashgames.moneynotebook.ui.adapter.CarAdapter;
import com.megaflashgames.moneynotebook.ui.custom.CustomToolbar;
import com.megaflashgames.moneynotebook.ui.dialog.BaseDialog;
import com.megaflashgames.moneynotebook.ui.dialog.DialogAddCarCost;
import com.megaflashgames.moneynotebook.ui.model.ScreenSettings;
import com.megaflashgames.moneynotebook.util.enums.CarSpendType;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public class FragmentCar extends FragmentBase {

    public static final ScreenSettings MENU_TAG = ScreenSettings.CAR;
    public static final String FRAGMENT_TAG = FragmentCar.class.getSimpleName();

    private List<Car> mAllCars;
    private CarAdapter mAdapter;

    @Bind(R.id.list_travelExpensesList)RecyclerView mList;


    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentCar();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        ButterKnife.bind(this, view);

        mAllCars = DatabaseService.GetInstance().getAllCars();
        Collections.reverse(mAllCars);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CarAdapter(mAllCars);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(mAdapter);

        return view;
    }


    @Override
    protected void customizeToolbar(CustomToolbar toolbar) {
        super.customizeToolbar(toolbar);
        toolbar.setOnAddButtonClickListener(new CustomToolbar.OnAddButtonClickListener() {
            @Override
            public void onAddClickListener() {
                DialogAddCarCost dialog = DialogAddCarCost.dialogInstance(getActivity());
                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
                    @Override
                    public void onDialogClosed(boolean cancellation) {
                        if (!cancellation) {
                            Toast.makeText(getActivity(), "Add to FragmentCar", Toast.LENGTH_SHORT).show();
                            //resetListItems();
                        }
                    }
                });
            }
        });
    }
}
