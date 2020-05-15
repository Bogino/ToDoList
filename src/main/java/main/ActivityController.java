package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Activity;
import response.Storage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
    public ResponseEntity put(@PathVariable int id, String name, String day){

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

    @RequestMapping(
            value = "/activities/",
            params = "name",
            method = GET)
    @ResponseBody
    public List<Activity> list(@RequestParam("name") final String name){
        return Storage.getAllActivities().stream()
                .filter(a ->{
                    Pattern pattern = Pattern.compile(name + "+.");
                    Matcher matcher = pattern.matcher(a.getName());
                    if (matcher.find())
                        return true;
                return false;}
                    )
                .collect(Collectors.toList());
    }

}
