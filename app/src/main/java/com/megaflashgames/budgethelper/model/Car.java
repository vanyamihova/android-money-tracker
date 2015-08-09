package com.megaflashgames.budgethelper.model;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.db.model.BaseDbObject;

/**
 * Created by vanyamihova on 06/05/2015.
 */
public class Car extends BaseDbObject {

    private static final long serialVersionUID = 1L;

    Type type;
    int total;
    int kilometers;
    long crateAt;
    long modifyAt;


    /* setters */
    public void setModifyAt(long modifyAt) {
        this.modifyAt = modifyAt;
    }

    public void setCrateAt(long crateAt) {
        this.crateAt = crateAt;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /* getters */
    public Type getType() {
        return type;
    }

    public int getTotal() {
        return total;
    }

    public int getKilometers() {
        return kilometers;
    }

    public long getCrateAt() {
        return crateAt;
    }

    public long getModifyAt() {
        return modifyAt;
    }

    @Override
    public String toString() {
        return "Car { " +
                "id: "  + ( getId() != null ? getId() : -1) + ", " +
                "type : " + getType() + " , " +
                "total : " + getTotal() + " , " +
                "kilometers : " + getKilometers() + ", " +
                "createAt : " + getCrateAt() + ", " +
                "modifyAt : " + getModifyAt() + ", " +
                " }";
    }



    public enum Type {
        FUEL(R.string.icon_car_fuel, "гориво", true),
        SERVICE(R.string.icon_car_service, "сервиз", false),
        CARWASH(R.string.icon_car_wash, "автомивка", false),
        ROAD(R.string.icon_road, "винетка", false),
        VEHICLE(R.string.icon_vehicle, "вулканизатор", false);

        Type(int icon, String text, boolean isNeedKilometers) {
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


}
