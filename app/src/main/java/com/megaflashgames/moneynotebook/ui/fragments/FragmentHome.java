package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.annotations.ContentView;
import com.megaflashgames.moneynotebook.annotations.InjectView;
import com.megaflashgames.moneynotebook.db.DatabaseService;
import com.megaflashgames.moneynotebook.model.Home;
import com.megaflashgames.moneynotebook.ui.adapter.TravelExpensesAdapter;
import com.megaflashgames.moneynotebook.ui.dialog.BaseDialog;
import com.megaflashgames.moneynotebook.ui.dialog.DialogAddCarCost;

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

    private void resetListItems() {
        if(mAdapter != null) {
            mAllHomes = DatabaseService.GetInstance().getAllHomes();
//            mAdapter.setItems(mAllHomes);
//            mAdapter.notifyDataSetChanged();
        }
    }

    private void setNavigationInRight() {
        mNavigation = this.getFragmentNavigation();
        mNavigation.createRearrange();
//        mNavigation.createAddButton();
//        mNavigation.setOnAddButtonListener(new AddButtonAction());
    }


    private class AddButtonAction implements FragmentNavigation.OnAddButtonListener {
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
    }


}
