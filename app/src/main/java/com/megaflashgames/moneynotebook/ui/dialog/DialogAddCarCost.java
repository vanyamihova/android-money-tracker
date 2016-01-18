package com.megaflashgames.moneynotebook.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.db.DatabaseService;
import com.megaflashgames.moneynotebook.db.model.Car;
import com.megaflashgames.moneynotebook.ui.adapter.CustomSpinnerAdapter;
import com.megaflashgames.moneynotebook.util.DateAndTimeUtil;
import com.megaflashgames.moneynotebook.util.enums.CarSpendType;

import java.util.Arrays;

/**
 * Created by vanyamihova on 10/05/2015.
 */
public class DialogAddCarCost extends BaseDialog {

    private Car mCar;


    public static DialogAddCarCost dialogInstance(Context context) {
        return new DialogAddCarCost(context);
    }

    public DialogAddCarCost(Context context) {
        super(context);

        this.mCar = new Car();
    }

    @Override
    protected boolean showSoftKeybord() {
        return false;
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.dialog_add_car_cost;
    }

    @Override
    protected void setEditText() {

    }

    @Override
    protected void setTextView() {
        textView.setText("Add");
    }

    @Override
    protected void setButtonsText() {
        finishButton.setText(mContext.getString(R.string.textCancel));
        actionButton.setText(mContext.getString(R.string.textSave));
    }

    @Override
    protected void actionOnFinishButton() {

    }

    @Override
    protected void actionOnActionButton() {
        if(!editText.getText().toString().isEmpty()) {

            mCar.total = Integer.parseInt(editText.getText().toString());
            mCar.crateAt = DateAndTimeUtil.GetInstance().setTimestamp(editTextDate.getText().toString());
            mCar.modifyAt = DateAndTimeUtil.GetInstance().setTimestamp(editTextDate.getText().toString());

            if(!editTextKilometers.getText().toString().isEmpty())
                mCar.kilometers = Integer.parseInt(editTextKilometers.getText().toString());

            mCar.saveData();
        }
    }

    @Override
    protected void setAdditionalSource() {
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(mContext, Arrays.asList(CarSpendType.values()));
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarSpendType type = Arrays.asList(CarSpendType.values()).get(position);
                mCar.type = type;

                if (!type.isNeedKilometers()) {
                    editTextKilometers.setVisibility(View.INVISIBLE);
                } else {
                    editTextKilometers.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextDate.setText( DateAndTimeUtil.GetInstance().getDate() );

    }




}
