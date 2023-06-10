package com.akagera.park.services;


import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.model.VolunteeringApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

//public interface VolunteeringApplicationService {
    public interface VolunteeringApplicationService {
        //VolunteeringApplication updateApplication(VolunteeringApplication volunteeringApplication);
        List<VolunteeringApplication> getApplicationsForActivity();
//        VolunteeringApplication updateApplicationStatus(Long applicationId, String status);
   // VolunteeringApplication saveFile(MultipartFile file) throws Exception;
    VolunteeringApplication applyForActivityWithFile(VolunteeringApplication volunteeringApplication, Long activityId, MultipartFile file);
    VolunteeringApplication downloadFile(String fileId) throws Exception;

//    VolunteeringApplication getApplicationById(Long id);
    public Optional<VolunteeringApplication> getApplicationByIdUpdate(Long id);
    void deleteApplication(Long id);
    Page<VolunteeringApplication> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    //    @Override
    //    public List<VolunteeringApplication> getApplicationsForUser(String userEmail) {
    //        Optional<User> userOptional = userRepo.findByEmail(userEmail);
    //        if (userOptional.isPresent()) {
    //            User user = userOptional.get();
    //            // Fetch the applications related to the user
    //            return volunteeringApplicationRepository.findByUser(user);
    //        }
    //        return Collections.emptyList();
    //    }
    List<VolunteeringApplication> getApplicationsForUser(String userEmail);

//    VolunteeringApplication save(VolunteeringApplication application);

    //List<VolunteeringApplication> getApplicationsForUser(String userEmail);


}

