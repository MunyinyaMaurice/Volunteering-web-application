package com.akagera.park.controller;

import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.services.impl.VolunteeringActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class indexx {

    @Autowired
    private VolunteeringActivityServiceImpl volunteeringActivityService;

    @GetMapping("/C")
    public String addActivity() {
        return "dis_Activity";
    }

    @GetMapping("/views")
    public String showActiviti(Model model) {
        List<VolunteeringActivity> activities = volunteeringActivityService.getAllActivities();
        model.addAttribute("activitys", activities);
//        return "list_activitie";
        return "dis_Activity";
    }
}