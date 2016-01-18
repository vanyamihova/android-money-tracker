package com.megaflashgames.moneynotebook.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public class KeyboardUtil {

    private static KeyboardUtil Instance;
    private Context mContext;
    private Activity mActivity;

    public static KeyboardUtil GetInstance(Context context) {
        if(Instance == null) {
            Instance = new KeyboardUtil(context);
        }
        return Instance;
    }

    private KeyboardUtil(Context context) {
        this.mContext = context;
        this.mActivity = (Activity)context;
    }

    public void hide() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void show() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
