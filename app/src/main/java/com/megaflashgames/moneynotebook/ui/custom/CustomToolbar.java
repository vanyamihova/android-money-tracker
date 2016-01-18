package com.megaflashgames.moneynotebook.ui.custom;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.megaflashgames.moneynotebook.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public class CustomToolbar extends Toolbar {

    private OnMenuButtonClickListener mOnMenuButtonClickListener;
    private OnAddButtonClickListener mOnAddButtonClickListener;

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomToolbar(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_toolbar, this);

        ButterKnife.bind(this);

        setContentInsetsAbsolute(0, 0);
        setTitle(null);
    }

    public void setDefaultSettings() {
        mOnAddButtonClickListener = null;
    }

    @OnClick(R.id.btn_menu)
    public void onClickMenuButton() {
        if(mOnMenuButtonClickListener != null)
            mOnMenuButtonClickListener.onMenuClickListener();
    }

    public void setOnMenuButtonClickListener(OnMenuButtonClickListener listener) {
        this.mOnMenuButtonClickListener = listener;
    }
    public interface OnMenuButtonClickListener {
        void onMenuClickListener();
    }



    @OnClick(R.id.btn_add)
    public void onClickAddButton() {
        if(mOnAddButtonClickListener != null)
            mOnAddButtonClickListener.onAddClickListener();
    }

    public void setOnAddButtonClickListener(OnAddButtonClickListener listener) {
        this.mOnAddButtonClickListener = listener;
    }
    public interface OnAddButtonClickListener {
        void onAddClickListener();
    }
}
