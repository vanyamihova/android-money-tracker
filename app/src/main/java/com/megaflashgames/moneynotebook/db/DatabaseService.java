package com.megaflashgames.moneynotebook.db;

import com.google.gson.Gson;
import com.megaflashgames.moneynotebook.db.model.BaseDbObject;
import com.megaflashgames.moneynotebook.db.model.MainTableObject;
import com.megaflashgames.moneynotebook.model.Car;
import com.megaflashgames.moneynotebook.model.Home;

import java.util.ArrayList;
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

    public long saveMainObject(Object obj) {
        String type = obj.getClass().getSimpleName();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(obj);
        MainTableObject mainTableObject = new MainTableObject();
        mainTableObject.setId(((BaseDbObject) obj).getId());
        mainTableObject.setJsonObject(jsonObject);
        mainTableObject.setType(type);
        mainTableObject.setType(obj.getClass().getSimpleName());
        mainTableObject = DaoService.GetInstance().save(mainTableObject);
        return mainTableObject.getId();
    }

    public void deleteAllByType(String type) {
        DaoService.GetInstance().deleteAllRowsByType(MainTableObject.class, type);
    }


    public List<Car> getAllCars() {
        List<MainTableObject> objects = DaoService.GetInstance().getAllByType(MainTableObject.class, DatabaseHelper.MainTableTypes.CAR.getName());
        List<Car> cars = new ArrayList<>();
        if (objects == null || objects.isEmpty()) {
            return cars;
        }

        Gson gson = new Gson();
        for (MainTableObject object : objects) {
            Car car = gson.fromJson(object.getJsonObject(), Car.class);
            car.setId(object.getId());
            cars.add(car);
        }

        return cars;
    }

    public List<Home> getAllHomes() {
        List<MainTableObject> objects = DaoService.GetInstance().getAllByType(MainTableObject.class, DatabaseHelper.MainTableTypes.HOME.getName());
        List<Home> homes = new ArrayList<>();
        if (objects == null || objects.isEmpty()) {
            return homes;
        }

        Gson gson = new Gson();
        for (MainTableObject object : objects) {
            Home home = gson.fromJson(object.getJsonObject(), Home.class);
            home.setId(object.getId());
            homes.add(home);
        }

        return homes;
    }



}
