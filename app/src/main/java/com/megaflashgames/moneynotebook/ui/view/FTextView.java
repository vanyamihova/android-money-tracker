package com.megaflashgames.moneynotebook.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.ui.service.FontsService;

/**
 * Created by vanyamihova on 05/05/2015.
 */
public class FTextView extends TextView {

    // static
    private static final String TAG = FTextView.class.getSimpleName();
    private static final String ICONS = "icons";


    // model
    private boolean isIcon = false;

    public FTextView(Context context) {
        super(context);
    }

    public FTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public void setup(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FText, 0, 0);

        try {
            String typefaceName = a.getString(R.styleable.FText_font);
            if(ICONS.equalsIgnoreCase(typefaceName)) {
                isIcon = true;
            }

            if (!isInEditMode() && !TextUtils.isEmpty(typefaceName)) {
                setTypeface(FontsService.getFontByName(context, typefaceName));
                setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
        } catch (Exception e) {
            Log.e(TAG, String.format("%s", e.getMessage()), e);
        } finally {
            a.recycle();
        }

//        Float textSizeMultiplier = SettingService.GetInstance().getFloat(SettingService.TEXT_SIZE_MULTIPLIER);
//        // do not change size of content if TextView is used as icon
//        if(textSizeMultiplier != null && !isIcon) {
//            float currentTextSize = getTextSize();
//            setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize * textSizeMultiplier);
//        }
    }
}
