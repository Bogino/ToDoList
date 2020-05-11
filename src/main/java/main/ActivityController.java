package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Activity;
import response.Storage;

import java.util.List;

@RestController
public class ActivityController {

    @GetMapping("/activities/")
    public List<Activity> list(){
        return Storage.getAllActivities();
    }

    @PostMapping("/activities/")
    public int add(Activity activity){
        return Storage.addActivity(activity);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity get(@PathVariable int id){

       Activity activity =  Storage.getActivity(id);
       if (activity == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       return new ResponseEntity(activity, HttpStatus.OK);
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity put(@PathVariable int id, String name){

        Activity activity = Storage.getActivity(id);
        if (activity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        activity.setName(name);
        return new  ResponseEntity(activity, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{id}")
    public void delete(@PathVariable int id){
        Storage.delete(id);
    }

}
