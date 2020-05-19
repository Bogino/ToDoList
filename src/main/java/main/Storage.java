package main;

import main.model.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private static int currentId = 1;
    private static ConcurrentHashMap<Integer, Activity> activities = new ConcurrentHashMap<Integer, Activity>();


    public static List<Activity> getAllActivities(){

        ArrayList<Activity> activitiesList = new ArrayList<Activity>();
        activitiesList.addAll(activities.values());
        return activitiesList;

    }

    public static int addActivity (Activity activity){
        int id = currentId++;
        activity.setId(id);
        activities.put(id, activity);
        return id;
    }

    public static Activity getActivity(int id){

        if (activities.containsKey(id)){
            return activities.get(id);
        }
        return null;
    }

    public static void delete(int id){
        if (activities.containsKey(id)){
        Activity activity = activities.remove(id);
        activity = null;
        }
    }

}
