package com.akagera.park.controller;

import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.model.VolunteeringApplication;
import com.akagera.park.services.UserServiceImpl;
import com.akagera.park.services.VolunteeringApplicationService;
import com.akagera.park.services.impl.VolunteeringActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class userApplicationController {
    @Autowired
    private VolunteeringApplicationService volunteeringApplicationService;
    @Autowired
    private VolunteeringActivityServiceImpl volunteeringActivityService;
    @Autowired
    private UserServiceImpl userService;
@GetMapping("/apps")
public String showApplicationForm(Model model) {
    List<VolunteeringActivity> activities = volunteeringActivityService.getAllActivities();
    model.addAttribute("applicationss", new VolunteeringApplication());
    model.addAttribute("activities", activities);
    return "userApplicationForm";
}

    @PostMapping("/appl")
    public String ApplicationForm(@ModelAttribute("applicationss") VolunteeringApplication volunteeringApplication,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam("activity.id") Long volunteeringActivityId,
                                  RedirectAttributes redirectAttributes,
                                  HttpSession session, Principal principal) throws Exception {
        if (session.getAttribute("formSubmitted") != null) {
            // Form has already been submitted, do not process again
            return "redirect:/apps";
        }

        // Set the flag to indicate that the form has been submitted
        session.setAttribute("formSubmitted", true);

        VolunteeringApplication application = volunteeringApplicationService.applyForActivityWithFile(volunteeringApplication, volunteeringActivityId, file);
        String downloadUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/volunteering/applications/")
                .path(application.getId().toString())
                .path("/download")
                .toUriString();
        redirectAttributes.addFlashAttribute("successMessage", "Application and file uploaded successfully! Download link: " + downloadUrl);
//        return "redirect:/apps";
        if (principal != null) {
            String role = userService.getRole(principal.getName());
            if (role != null && role.equals("ADMIN")) {
               return "redirect:/applicationView";
                //return "createApplication";
            }
        }
       // return "userApplicationForm";
        return "redirect:/apps";
    }
@GetMapping("/showUpdateApplication/{id}")
public String showApplicationForm(@PathVariable("id") Long id, Model model) {
    List<VolunteeringActivity> activities = volunteeringActivityService.getAllActivities();
    model.addAttribute("activities", activities);

    if (id != null) {
        VolunteeringApplication application = volunteeringApplicationService.getApplicationByIdUpdate(id).orElse(null);
        model.addAttribute("applicationss", application);
    } else {
        model.addAttribute("applicationss", new VolunteeringApplication());
    }

    return "updateApplication";
}
}
