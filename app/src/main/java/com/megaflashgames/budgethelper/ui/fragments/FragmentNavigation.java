package com.megaflashgames.budgethelper.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.ui.dialog.BaseDialog;
import com.megaflashgames.budgethelper.ui.dialog.DialogAddCarCost;

/**
 * Created by vanyamihova on 05/05/2015.
 */
@ContentView(R.layout.fragment_navigation)
public class FragmentNavigation extends FragmentBase {

    public interface OnDialogListener {
        public void onDialogClosed(boolean cancellation);
    }

    public interface OnFragmentNavigationListener {
        public void onSlidingMenuToggle();
    }



    public static final String FRAGMENT_TAG = FragmentNavigation.class.getSimpleName();

    @InjectView(R.id.iv_menu)
    private ImageView mButtonMenu;
    @InjectView(R.id.tv_add_car_cost)
    private TextView addCarCost;
    @InjectView(R.id.tv_rearrange)
    private TextView rearrange;
    @InjectView(R.id.container_Navigation)
    RelativeLayout container;

    private OnFragmentNavigationListener mDelegate;
    private OnDialogListener mListener;


    public static FragmentNavigation newInstance() {
        FragmentNavigation fragment = new FragmentNavigation();

        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createMenuButton();
    }


    private void createMenuButton() {
        mButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDelegate != null) {
                    mDelegate.onSlidingMenuToggle();
                }
            }
        });
    }


    public void setOnFragmentNavigationListener(OnFragmentNavigationListener listener) {
        mDelegate = listener;
    }

    public void setDialogListener(OnDialogListener listener) {
        mListener = listener;
    }

    public void resetNavigationInRight() {
        addCarCost.setVisibility(View.INVISIBLE);
        rearrange.setVisibility(View.INVISIBLE);
    }

    public void createAddCarCost() {
        addCarCost.setVisibility(View.VISIBLE);
        addCarCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddCarCost dialog = DialogAddCarCost.dialogInstance(getActivity());
                dialog.setBaseDialogListener(new BaseDialog.BaseDialogListener() {
                    @Override
                    public void onDialogClosed(boolean cancellation) {
                        mListener.onDialogClosed(cancellation);
                    }
                });
            }
        });
    }

    public void createRearrange() {
        rearrange.setVisibility(View.VISIBLE);
        rearrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Rearrange items in list
            }
        });
    }

    public void setBackgroundOnNavigation(int backgroundColor) {
        if(container != null)
            container.setBackgroundColor(backgroundColor);
    }


}
