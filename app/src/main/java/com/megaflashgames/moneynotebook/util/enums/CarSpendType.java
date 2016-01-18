package com.megaflashgames.moneynotebook.util.enums;

import com.megaflashgames.moneynotebook.R;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public enum CarSpendType {
    FUEL(R.string.icon_car_fuel, "гориво", true),
    SERVICE(R.string.icon_car_service, "сервиз", false),
    CARWASH(R.string.icon_car_wash, "автомивка", false),
    ROAD(R.string.icon_road, "винетка", false),
    VEHICLE(R.string.icon_vehicle, "вулканизатор", false);

    CarSpendType(int icon, String text, boolean isNeedKilometers) {
        this.icon = icon;
        this.text = text;
        this.isNeedKilometers = isNeedKilometers;
    }
    private int icon;
    private String text;
    private boolean isNeedKilometers;

    public int getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public boolean isNeedKilometers() {
        return isNeedKilometers;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsNeedKilometers(boolean isNeedKilometers) {
        this.isNeedKilometers = isNeedKilometers;
    }
}
