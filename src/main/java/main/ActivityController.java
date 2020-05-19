package main;

import main.model.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Activity;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/activities/")
    public List<Activity> list(){
        Iterable<Activity> activityIterable = activityRepository.findAll();
        ArrayList<Activity> activities = new ArrayList<>();
        for (Activity activity : activityIterable){
            activities.add(activity);
        }
        return activities;
    }

    @PostMapping("/activities/")
    public int add(Activity activity){

        Activity newActivity = activityRepository.save(activity);
        return newActivity.getId();
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity get(@PathVariable int id){

       Optional<Activity> optionalActivity =  activityRepository.findById(id);
       if (!optionalActivity.isPresent()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       return new ResponseEntity(optionalActivity, HttpStatus.OK);
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity put(@PathVariable int id, String name){

        Optional<Activity> optionalActivity =  activityRepository.findById(id);
        if (!optionalActivity.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Activity activity = optionalActivity.get();
        activity.setName(name);
        activityRepository.save(activity);
        return new  ResponseEntity(activity, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{id}")
    public void delete(@PathVariable int id){

        activityRepository.deleteById(id);
    }

    @RequestMapping(
            value = "/activities/",
            params = "name",
            method = GET)
    @ResponseBody
    public List<Activity> list(@RequestParam("name") final String name){
        Iterable<Activity> activityIterable = activityRepository.findAll();
        ArrayList<Activity> activities = new ArrayList<>();
        for (Activity activity : activityIterable){
            activities.add(activity);
        }
        return activities.stream()
                .filter(a ->a.getName().contains(name))
                .collect(Collectors.toList());
    }

}
