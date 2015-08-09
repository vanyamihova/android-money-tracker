package com.megaflashgames.budgethelper.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.db.DatabaseService;
import com.megaflashgames.budgethelper.model.Car;
import com.megaflashgames.budgethelper.model.Home;
import com.megaflashgames.budgethelper.ui.adapter.TravelExpensesAdapter;

import java.util.List;

/**
 * Created by VanyaMihova on 8/2/2015.
 */
@ContentView(R.layout.fragment_home)
public class FragmentHome extends FragmentBase {

    public static final String FRAGMENT_TAG = FragmentHome.class.getSimpleName();

    private TravelExpensesAdapter mAdapter;
    private List<Home> mAllHomes;

    @InjectView(R.id.lv_homeExpensesList)
    ListView mHomeExpensesList;

    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentHome();

        return fragment;
    }

    @Override
    public void onViewCreated(View container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);

        setNavigationInRight();

        mAllHomes = DatabaseService.GetInstance().getAllHomes();

//        mAllCarCosts = DatabaseService.GetInstance().getAllCosts();
//
//        mAdapter = new TravelExpensesAdapter(getActivity(), getActivity().getLayoutInflater(), mAllCarCosts);
//        mAdapter.setOnAdapterListener(new TravelExpensesAdapter.OnAdapterListener() {
//            @Override
//            public void onDialogClosed(boolean cancellation) {
//                if(!cancellation) {
//                    resetListItems();
//                }
//            }
//        });
//        mHomeExpensesList.setAdapter(mAdapter);
    }

    private void setNavigationInRight() {
        mNavigation = this.getFragmentNavigation();
        mNavigation.createRearrange();
        mNavigation.createAddCarCost();
        mNavigation.setDialogListener(new FragmentNavigation.OnDialogListener() {
            @Override
            public void onDialogClosed(boolean cancellation) {
                if(!cancellation) {
                    resetListItems();
                }
            }
        });
    }

    private void resetListItems() {
        if(mAdapter != null) {
            mAllHomes = DatabaseService.GetInstance().getAllHomes();
//            mAdapter.setItems(mAllHomes);
//            mAdapter.notifyDataSetChanged();
        }
    }

}
