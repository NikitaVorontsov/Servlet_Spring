package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {

    private static final PetModel instanse = new PetModel();

    private final Map <Integer, Pet> model;

    public PetModel () {
        model = new HashMap<Integer, Pet>();
    }

    public static PetModel getInstance(){
        return instanse;
    }

    public void add (Pet pet, int id){
        model.put(id,pet);
    }

    public Pet getFromList (Integer id){
        return model.get(id);
    }

    public Map<Integer, Pet> getAll(){
        return model;
    }

    public int sizePetModel () {
        return model.size();
    }

    public void delete (Integer id){
        model.remove(id);
    }

    public void update (Integer id, Pet pet){
        model.get(id).setAge(pet.getAge());
        model.get(id).setName(pet.getName());
        model.get(id).setType(pet.getType());
    }
}
