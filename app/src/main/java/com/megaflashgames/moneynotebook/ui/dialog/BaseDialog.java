package com.megaflashgames.moneynotebook.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.model.Car;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentBase;

public abstract class BaseDialog {

	public interface BaseDialogListener {
		public void onDialogClosed(boolean cancellation);
	}

	protected Car mCar;
	protected Context mContext;
	
	protected int itemWidth;
	protected InputMethodManager imm;
	
	protected static Dialog dialog;
	protected TextView textView;
	protected EditText editText;
	protected EditText editTextKilometers;
	protected EditText editTextDate;
	protected Button finishButton;
	protected Button actionButton;
	protected ListView listView;

	private BaseDialogListener mListener;


	// Delegate
	private FragmentBase.OnFragmentAction mOnFragmentActionDelegate;



    public BaseDialog(Context context) {
		this.mContext = context;
		
		loadDialog();
	}

	public BaseDialog(Context context, Car car) {
		this.mContext = context;
		this.mCar = car;

		loadDialog();
	}


	private void loadDialog()  {
		imm 		= (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
	    itemWidth  	= (int) (metrics.widthPixels - 20);
	    
		dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setDialogView();
        dialog.getWindow().setLayout(itemWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

		initializeDialogItems();
        
        setEditText();
        setTextView();
        setButtonsText();
        setAdditionalSource();
        
        if(finishButton != null) {
	        finishButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// hide soft keyboard and dismiss dialog
					if(editText != null)
						imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	        		dialog.dismiss();
	        		
	        		// custom action
					actionOnFinishButton();

					mListener.onDialogClosed(true);
				}
			});
        }
        
        if(actionButton != null) {
	        actionButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// hide soft keyboard
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
					dialog.dismiss();
	        		
	        		// custom action
	        		actionOnActionButton();

					mListener.onDialogClosed(false);
				}
			});
        }
        
        showDialog();
	}


	private void initializeDialogItems() {
		editText = (EditText) dialog.findViewById(R.id.et_cost);
		editTextKilometers = (EditText) dialog.findViewById(R.id.et_kilometers);
		editTextDate = (EditText) dialog.findViewById(R.id.et_date);
	}
	
	private void showDialog() {
		if(!((Activity) mContext).isFinishing()) {
			dialog.show();
			
			if(showSoftKeybord()) {
				// show soft keyboard
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			}
		}
	}
	
	private void setDialogView() {
		dialog.setContentView(setLayoutResId());

		if(setLayoutResId() == R.layout.dialog_add_car_cost) {
			textView		= (TextView) dialog.findViewById(R.id.tv_textInDialog);
			finishButton 	= (Button) dialog.findViewById(R.id.btn_finishDialog);
			actionButton 	= (Button) dialog.findViewById(R.id.btn_actionDialog);
		}
	}
	
	
	public void setOnFragmentActionDelegate(FragmentBase.OnFragmentAction delegate) {
		this.mOnFragmentActionDelegate = delegate;
	}
	
	protected void notifyDisplayFragment(String fragmentTag, boolean addToBackStack, Object... data) {
		if(mOnFragmentActionDelegate != null) {
			mOnFragmentActionDelegate.onDisplayFragment(fragmentTag, addToBackStack, data);
		}
	}
	
	protected FragmentBase.OnFragmentAction getOnFragmentAction() {
		return mOnFragmentActionDelegate;
	}
	
	abstract protected boolean showSoftKeybord();
	
	abstract protected int setLayoutResId();
	
	abstract protected void setEditText();
	
	abstract protected void setTextView();
	
	abstract protected void setButtonsText();
	
	abstract protected void actionOnFinishButton();
	
	abstract protected void actionOnActionButton();
	
	abstract protected void setAdditionalSource();

	public void setBaseDialogListener(BaseDialogListener listener) {
		this.mListener = listener;
	}
}
