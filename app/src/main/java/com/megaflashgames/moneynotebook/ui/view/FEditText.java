package com.megaflashgames.moneynotebook.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.ui.service.FontsService;

public class FEditText extends EditText {

	private static final String TAG = FEditText.class.getSimpleName();

	public FEditText(Context context) {
		super(context);
	}

	public FEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup(context, attrs);
	}

	public FEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup(context, attrs);
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

		// TODO: if there is no background attr, than default = custom_edittext
//		setBackgroundResource(R.drawable.custom_edittext);
		
//		Float textSizeMultiplier = SettingService.GetInstance().getFloat(SettingService.TEXT_SIZE_MULTIPLIER);
//		if(textSizeMultiplier != null) {
//			float currentTextSize = getTextSize();
//			setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize * textSizeMultiplier);
//		}
	}

}
