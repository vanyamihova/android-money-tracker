package com.megaflashgames.moneynotebook.db;

import com.megaflashgames.moneynotebook.db.model.Car;
import com.megaflashgames.moneynotebook.db.model.Home;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by vanyamihova on 19/06/2015.
 */
public class DatabaseService {

    private static DatabaseService Instance;

    public static DatabaseService GetInstance() {
        if(Instance == null)
            Instance = new DatabaseService();
        return Instance;
    }

    private DatabaseService() {}




    ///////////////////////////////////////////////////////////////////////////


    public List<Car> getAllCars() {
        return new Select().from(Car.class).where().queryList();
    }

    public List<Home> getAllHomes() {
        return new Select().from(Home.class).where().queryList();
    }



}
