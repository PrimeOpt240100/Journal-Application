package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.database.DataBase;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // it is special class/component, which handle our http request
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private DataBase dataBase;

    @GetMapping("get/all")
    public List<JournalEntity> getAllList(){
        return new ArrayList<>(dataBase.list.values());
    }

    @PostMapping("add/entry")
    public boolean addEntity(@RequestBody JournalEntity journalEntity){

        if(!dataBase.list.containsKey(journalEntity.getId())){
            dataBase.list.put(journalEntity.getId(),journalEntity);
            return true;
        }
        return false;
    }

    @PostMapping("add/all/entires")
    public <T> Object addAllEntities(@RequestBody List<JournalEntity> journalEntities){

        String msg1 = "Could not add this id because it is already exist in DataBase";
        String msg2 = "This id is added in DataBase";

        Map<Integer,String> output = new HashMap<>();

        for(JournalEntity journalEntity : journalEntities){
            if(!dataBase.list.containsKey(journalEntity.getId())){
                dataBase.list.put(journalEntity.getId(),journalEntity);
                output.put(journalEntity.getId(),msg2);
            }
            else{
               output.put(journalEntity.getId(),msg1);
            }
        }

        return output;
    }

    @DeleteMapping("del/id/{id}")
    public String deleteEntity(@PathVariable int id){

        if(dataBase.list.containsKey(id)){
            dataBase.list.remove(id);
            return "Entity with id " + id + " has been removed from DataBase";
        }
        return "Id " + id + " is not present in DataBase, please check the once again";
    }

    @PutMapping("update/{id}")
    public <T> Object updateEntity(@PathVariable int id, @RequestBody JournalEntity journalEntity){

        Map<String, JournalEntity> output = new HashMap<>();

        if(dataBase.list.containsKey(id)){
            JournalEntity old = dataBase.list.get(id);
            dataBase.list.put(id,journalEntity);
            output.put("old",old);
            output.put("New",dataBase.list.get(id));
            return output;
        }

        return "Id " + id + " is not present in DataBase, please check the once again";
    }
}
