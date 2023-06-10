package com.akagera.park.controller;

import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.services.impl.VolunteeringActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

    public class VolunteeringActivityController {

        @Autowired
        private VolunteeringActivityServiceImpl volunteeringActivityService;



    @GetMapping("/add")
    public String addActivity(Model model){
        model.addAttribute("activity",new VolunteeringActivity());
        //return "saveActivity";
        return "createActivitys";
    }

    @PostMapping("/save")
    public String saveActivity(@ModelAttribute("activity") VolunteeringActivity theActivity) {
        VolunteeringActivity activity=volunteeringActivityService.createActivity(theActivity);
        if (activity != null) {
            return "redirect:/view";
        }
        return "sorry";
    }

    @GetMapping("/view")
    public String showActivities(Model model){
        List<VolunteeringActivity> activities=volunteeringActivityService.getAllActivities();
        model.addAttribute("activity",activities);
        return "list_activitie";

    }
    @GetMapping("/Dash_view")
    public String showActivitiesDash(Model model){
        List<VolunteeringActivity> activities=volunteeringActivityService.getAllActivities();
        model.addAttribute("activity",activities);
        return "dis_Activity";

    }


    @GetMapping("/showUpdateActivity/{actId}")
    public String showUpdateVolunteer(@PathVariable(value = "actId") Long id,
                                      Model model){
        VolunteeringActivity volunteer = volunteeringActivityService.getActivityById(id);
        model.addAttribute("activity",volunteer);
        return "updateActivitys";
    }

    @GetMapping("/deleteActivity/{actId}")
    public String deleteVolunteer(@PathVariable (value = "actId") Long actId){
        this.volunteeringActivityService.deleteActivity(actId);
        return "redirect:/view";
    }

    @GetMapping("/pageVolunteer/{pageNo}")
    public String findPaginatedVolunteer(@PathVariable(value = "pageNo") int pageNo,
                                         @RequestParam("sortFieldVolunteer") String sortFieldVolunteer,
                                         @RequestParam("sortDirVolunteer") String sortDirVolunteer,
                                         Model model) {
        int pageSizeVolunteer = 3;

        Page< VolunteeringActivity > page = volunteeringActivityService.findPaginated(pageNo, pageSizeVolunteer, sortFieldVolunteer, sortDirVolunteer);
        List< VolunteeringActivity > listVolunteers = page.getContent();

        model.addAttribute("currentPageVolunteer", pageNo);
        model.addAttribute("totalPagesVolunteer", page.getTotalPages());
        model.addAttribute("totalItemsVolunteer", page.getTotalElements());

        model.addAttribute("sortFieldVolunteer", sortFieldVolunteer);
        model.addAttribute("sortDirVolunteer", sortDirVolunteer);
        model.addAttribute("listVolunteers", listVolunteers);
        model.addAttribute("reverseSortDirVolunteer", sortDirVolunteer.equals("asc") ? "desc" : "asc");


        return "list_activitie";
    }



}





