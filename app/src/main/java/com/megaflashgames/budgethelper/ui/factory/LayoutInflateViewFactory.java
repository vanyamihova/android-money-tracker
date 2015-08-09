package com.megaflashgames.budgethelper.ui.factory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.megaflashgames.budgethelper.ui.view.FTextView;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class LayoutInflateViewFactory implements LayoutInflater.Factory {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return createView(name, context, attrs);
    }


    public View createView(String name, Context context, AttributeSet attrs) {
        if(android.widget.TextView.class.getSimpleName().equals(name) || FTextView.class.getName().equals(name)) {
            return new FTextView(context, attrs);
        }

        return null;
    }

}
