package com.megaflashgames.budgethelper.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import com.megaflashgames.budgethelper.R;

import com.megaflashgames.budgethelper.ui.service.FontsService;


public class FButton extends Button
{
	private static final String TAG = FButton.class.getSimpleName();
	
	public FButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		setup(context, attrs);
	}

	public FButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setup(context, attrs);
	}
	
	public FButton(Context context)
	{
		super(context);
	}

	public void setup(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FText, 0, 0);

		try {
			String typefaceName = a.getString(R.styleable.FText_font);

			if (!isInEditMode() && !TextUtils.isEmpty(typefaceName)) {
				setTypeface(FontsService.getFontByName(context, typefaceName));
				setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
			}
		} catch (Exception e) {
			Log.e(TAG, String.format("%s", e.getMessage()), e);
		} finally {
			a.recycle();
		}
		
//		Float textSizeMultiplier = Setting.GetInstance().getFloat(SettingService.TEXT_SIZE_MULTIPLIER);
//		if(textSizeMultiplier != null) {
//			float currentTextSize = getTextSize();
//			setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize * textSizeMultiplier);
//		}
	}
}
