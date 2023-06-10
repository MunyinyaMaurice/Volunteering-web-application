package com.akagera.park.controller;

import com.akagera.park.model.VolunteeringApplication;
import com.akagera.park.services.VolunteeringApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private VolunteeringApplicationService volunteeringApplicationService;

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "/dash";
    }

    @GetMapping("/user")
    public String showUserApplications(Model model, Principal principal) {
        String userEmail = principal.getName();
        List<VolunteeringApplication> applications = volunteeringApplicationService.getApplicationsForUser(userEmail);
        model.addAttribute("UserAppView", applications);
        return "userApplication";
    }

}
