package com.megaflashgames.moneynotebook.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.ui.factory.LayoutInflateViewFactory;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentBase;
import com.megaflashgames.moneynotebook.ui.injector.MFGInjector;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class ScreenBase extends FragmentActivity {

    protected LayoutInflateViewFactory mLayoutInflateViewFactory;
    private boolean enableInInjector = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayoutInflateViewFactory = new LayoutInflateViewFactory();
        super.onCreate(savedInstanceState);
        if(enableInInjector) {
            MFGInjector.onCreate(this);
        }
    }

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return mLayoutInflateViewFactory.onCreateView(name, context, attrs);
    }

    protected void enableInjector(boolean enable) {
        enableInInjector = enable;
    }

    protected void keyboardHide() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    protected void displayFragment(String fragmentTag, boolean addToBackStack, Object... data) {
        FragmentBase fragment = (FragmentBase) getFragmentManager().findFragmentByTag(fragmentTag);
        fragment = onNewFragment(fragment, fragmentTag, data);


        fragment.setOnFragmentActionDelegate(onFragmentAction());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if(addToBackStack) {
            transaction.addToBackStack(fragmentTag);
        } else {
            getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        transaction.replace(R.id.fragment_content, fragment, fragmentTag).commit();
    }

    protected FragmentBase onNewFragment(FragmentBase fragment, String fragmentTag, Object... data) {
        return null;
    }

    protected FragmentBase.OnFragmentAction onFragmentAction() { return null; }
}
