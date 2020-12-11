package ru.appline.controller;


import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;
import ru.appline.util.ResponseTransfer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseTransfer createPet (@RequestBody Pet pet){
        petModel.add(pet,newId.getAndIncrement());
        String response;
        if (petModel.sizePetModel() == 1){
            response="Поздравляем, вы создали своего первого питомца!";
        } else response="Поздравляем, вы создали питомца!";
        return new ResponseTransfer(response);
    }

    @GetMapping(value = "/getAll",produces = "application/json")
    public Map <Integer,Pet> getAll (){
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet",consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map <String, Integer> id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet",consumes = "application/json", produces = "application/json")
    public void deletePet(@RequestBody Map <String, Integer> id){
        petModel.delete(id.get("id"));
    }

    @PutMapping (value = "/putPet/{id}",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseTransfer putPet (@RequestBody Pet pet,@PathVariable Integer id){
        String response;
        if (petModel.getFromList(id)==null){
            response="Питомца с таким ID не существует!";
        } else {
            petModel.update(id,pet);
            response="Вы заменили питомца "+id+" на нового!";
        }
        return new ResponseTransfer(response);
    }




}
