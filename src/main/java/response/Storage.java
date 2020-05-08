package response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static int currentId = 1;
    private static HashMap<Integer, Activity> activities = new HashMap<Integer, Activity>();


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

}
