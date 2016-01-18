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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vanyamihova on 10/05/2015.
 */
public class DialogEditCarCost extends BaseDialog {

    public List<CarSpendType> allCarCostTypes;

    public static DialogEditCarCost dialogInstance(Context context, Car car) {
        return new DialogEditCarCost(context, car);
    }

    public DialogEditCarCost(Context context, Car car) {
        super(context, car);
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
    protected void setEditText() { }

    @Override
    protected void setTextView() {
        textView.setText("Edit");
    }

    @Override
    protected void setButtonsText() {
        finishButton.setText(mContext.getString(R.string.textCancel));
        actionButton.setText(mContext.getString(R.string.textSave));
    }

    @Override
    protected void actionOnFinishButton() { }

    @Override
    protected void actionOnActionButton() {
        if(!editText.getText().toString().isEmpty()) {
            mCar.total = Double.parseDouble(editText.getText().toString());
            mCar.modifyAt = DateAndTimeUtil.GetInstance().setTimestamp(editTextDate.getText().toString());

            if(!editTextKilometers.getText().toString().isEmpty())
                mCar.kilometers = Integer.parseInt(editTextKilometers.getText().toString());

            mCar.saveData();
        }
    }

    @Override
    protected void setAdditionalSource() {
        this.allCarCostTypes = sortCostTypes();

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(mContext, allCarCostTypes);
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarSpendType type = allCarCostTypes.get(position);
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

        editText.setText(String.valueOf(mCar.total));
        editTextKilometers.setText(String.valueOf(mCar.kilometers));

        DateAndTimeUtil dateAndTimeUtil = DateAndTimeUtil.GetInstance();
        dateAndTimeUtil.setTimeInMillis(mCar.modifyAt);
        editTextDate.setText(dateAndTimeUtil.getDate());
    }


    private List<CarSpendType> sortCostTypes() {
        List<CarSpendType> resultTypes = new ArrayList<>();
        List<CarSpendType> localeCarCostTypes = Arrays.asList(CarSpendType.values());
        for(int i = 0 ; i < localeCarCostTypes.size() ; i++) {
            CarSpendType carType = localeCarCostTypes.get(i);
            if(carType == mCar.type && i != 0) {
                resultTypes.add(0, carType);
            } else {
                resultTypes.add(carType);
            }
        }
        return resultTypes;
    }



}
