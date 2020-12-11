package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompassModel implements Serializable {

    private static final CompassModel instance = new CompassModel();

    private final Map <String,String> model;

    public CompassModel() {
        model=new HashMap<String, String>();
    }

    public static CompassModel getInstance(){
        return instance;
    }

    public void addSides(Map<String, String> data) {
        for (Map.Entry<String, String> pair: data.entrySet()) {
            model.put(pair.getKey(),pair.getValue());
        }
    }

    public Map<String, String> getAllSides() {
        return model;
    }


}
