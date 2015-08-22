package com.megaflashgames.moneynotebook.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.megaflashgames.moneynotebook.ui.injector.MFGInjector;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class FragmentBase extends Fragment {

    public interface OnFragmentAction {
        public void onDisplayFragment(String fragmentTag, boolean addToBackStack, Object... data);
        public void  onFragmentVisibilityChange(FragmentBase fragment, boolean goToVisible);
    }

    // By default should inject view
    private boolean enableInjector = true;
    private boolean isViewCreated = false;

    // Delegate
    private OnFragmentAction mOnFragmentActionDelegate;

    // Navigation
    protected FragmentNavigation mNavigation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(enableInjector) {
            return MFGInjector.onCreateView(this, inflater, container);
        } else {
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if(enableInjector) {
            MFGInjector.onViewCreated(this);
        }
        isViewCreated = true;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(mOnFragmentActionDelegate != null)
            mOnFragmentActionDelegate.onFragmentVisibilityChange(this, true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mOnFragmentActionDelegate != null)
            mOnFragmentActionDelegate.onFragmentVisibilityChange(this, false);
    }

    protected  void notifyDisplayFragment(String fragmentTag, boolean addToBackStack, Object... data) {
        if(mOnFragmentActionDelegate != null)
            mOnFragmentActionDelegate.onDisplayFragment(fragmentTag, addToBackStack, data);
    }


    // OnFragmentActionDelegate
    protected  OnFragmentAction getOnFragmentActionDelegate() {
        return this.mOnFragmentActionDelegate;
    }
    public void setOnFragmentActionDelegate(OnFragmentAction delegate) {
        this.mOnFragmentActionDelegate = delegate;
    }

    // Navigation
    protected FragmentNavigation getFragmentNavigation() { return this.mNavigation; }

    public void setFragmentNavigation(FragmentNavigation nav) {
        this.mNavigation = nav;
    }
}
