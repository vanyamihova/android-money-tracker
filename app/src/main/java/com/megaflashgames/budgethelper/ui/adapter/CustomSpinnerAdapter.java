package com.megaflashgames.budgethelper.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.model.Car;

import java.util.List;

/**
 * Created by vanyamihova on 18/06/2015.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<Car.Type> mObjects;

    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, List objects) {
        super(context, R.layout.custom_spinner_row, objects);

        mContext = context;
        mObjects = objects;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomRowView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomRowView(position, convertView, parent);
    }

    private View getCustomRowView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.custom_spinner_row, parent, false);

        Car.Type model = mObjects.get(position);

        TextView label = (TextView) row.findViewById(R.id.company);
        TextView companyLogo = (TextView) row.findViewById(R.id.image);

        label.setText(model.getText());
        companyLogo.setText(model.getIcon());

        return row;
    }


}
