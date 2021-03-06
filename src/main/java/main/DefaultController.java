package main;

import main.model.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("activities", activityRepository.list());
        model.addAttribute("activitiesCount",activityRepository.list().size());
        return "index";
    }
}
