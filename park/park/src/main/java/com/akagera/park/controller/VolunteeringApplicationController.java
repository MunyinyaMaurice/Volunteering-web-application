package com.akagera.park.controller;

import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.model.VolunteeringApplication;
import com.akagera.park.repository.VolunteeringApplicationRepository;
import com.akagera.park.services.UserServiceImpl;
import com.akagera.park.services.VolunteeringActivityService;
import com.akagera.park.services.VolunteeringApplicationService;

import com.akagera.park.services.impl.VolunteeringActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
    public class VolunteeringApplicationController {

        @Autowired
        private VolunteeringApplicationService volunteeringApplicationService;
        @Autowired
        private VolunteeringApplicationRepository v_repo;
        @Autowired
        private VolunteeringActivityService v_activity;
    @Autowired
    private VolunteeringActivityServiceImpl volunteeringActivityService;
    @Autowired
    private UserServiceImpl UserService;
        @GetMapping("/main")
        public String loadMainPage() {
        return "main-page";
    }

//    @GetMapping("/applys")
//    public String addActivity(Model model){
//        model.addAttribute("application",new VolunteeringApplication());
//        return "createApplication";
//    }
        @GetMapping("/applicationView")
        public String showApplications(Model model) {
            List<VolunteeringApplication> application = volunteeringApplicationService.getApplicationsForActivity();
            model.addAttribute("applications", application);
            return "list_application";
        }

    @GetMapping("/about")
    public String uploadDoc() {
       return "about-us";
    }


    @GetMapping("/applications/{id}/download")
    public String downloadFile(@PathVariable String id, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        VolunteeringApplication volunteeringApplication = volunteeringApplicationService.downloadFile(id);
        response.setContentType(volunteeringApplication.getContentType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + volunteeringApplication.getFileName() + "\"");
        response.getOutputStream().write(volunteeringApplication.getVolunteerDocument());
        redirectAttributes.addFlashAttribute("successMessage", "File downloaded successfully!");
       // return "redirect:/uploads";
        return "redirect:/applications/{id}/download";
    }


    @GetMapping("/deleteApplication/{id}")
    public String deleteVolunteer(@PathVariable (value = "id") Long id){
        this.volunteeringApplicationService.deleteApplication(id);
        return "redirect:/applicationView";
    }

    @GetMapping("/pageApplication/{pageNo}")
    public String findPaginatedVolunteer(@PathVariable(value = "pageNo") int pageNo,
                                         @RequestParam("sortFieldVolunteer") String sortFieldVolunteer,
                                         @RequestParam("sortDirVolunteer") String sortDirVolunteer,
                                         Model model) {
        int pageSizeVolunteer = 3;

        Page< VolunteeringApplication > page = volunteeringApplicationService.findPaginated(pageNo, pageSizeVolunteer, sortFieldVolunteer, sortDirVolunteer);
        List< VolunteeringApplication > listVolunteers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortFieldVolunteer);
        model.addAttribute("sortDir", sortDirVolunteer);
        model.addAttribute("list", listVolunteers);
        model.addAttribute("reverseSortDir", sortDirVolunteer.equals("asc") ? "desc" : "asc");


        return "list_application";
    }
}
