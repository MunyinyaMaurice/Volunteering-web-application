

package com.akagera.park.services.impl;


import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.model.VolunteeringApplication;
import com.akagera.park.repository.UserRepo;
import com.akagera.park.repository.VolunteeringActivityRepository;
import com.akagera.park.repository.VolunteeringApplicationRepository;
import com.akagera.park.services.VolunteeringApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VolunteeringApplicationServiceImpl implements VolunteeringApplicationService {
    @Autowired
    private VolunteeringActivityServiceImpl activityService;
    @Autowired
    private VolunteeringApplicationRepository volunteeringApplicationRepository;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private VolunteeringActivityRepository volunteeringActivityRepository;

    public VolunteeringApplicationServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public List<VolunteeringApplication> getApplicationsForActivity() {
        return volunteeringApplicationRepository.findAll();
    }


    @Override
    public VolunteeringApplication downloadFile(String fileId) throws Exception {
        Long id = Long.parseLong(fileId);
        return volunteeringApplicationRepository.findById(id)
                .orElseThrow(() -> new Exception("A file with Id : " + fileId + " could not be found"));
    }


    @Override
    public Optional<VolunteeringApplication> getApplicationByIdUpdate(Long id) {
        return volunteeringApplicationRepository.findById(id);
    }

    @Override
    public void deleteApplication(Long id) {
        volunteeringApplicationRepository.deleteById(id);
    }

    @Override
    public Page<VolunteeringApplication> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.volunteeringApplicationRepository.findAll(pageable);

    }

//    @Override
//    @Transactional
//    public VolunteeringApplication applyForActivityWithFile(VolunteeringApplication volunteeringApplication, MultipartFile file) {
//        // Save the application
//        VolunteeringApplication application = volunteeringApplicationRepository.save(volunteeringApplication);
//
//        // Save the file and associate it with the application
//        VolunteeringApplication attachment = new VolunteeringApplication();
//        attachment.setVolunteerNames(volunteeringApplication.getVolunteerNames());
//        attachment.setVolunteerAge(volunteeringApplication.getVolunteerAge());
//        attachment.setVolunteerEmail(volunteeringApplication.getVolunteerEmail());
//        attachment.setVolunteerPhoneNumber(volunteeringApplication.getVolunteerPhoneNumber());
//        attachment.setVolunteerAddress(volunteeringApplication.getVolunteerAddress());
//
//        attachment.setDocId(UUID.randomUUID().toString());
//        attachment.setFileName(file.getOriginalFilename());
//        attachment.setFileType(file.getContentType());
//        try {
//            attachment.setVolunteerDocument(file.getBytes()); // store the file content as byte array
//            attachment.setContentType(file.getContentType()); // set the content type
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        attachment.setId(application.getId()); // set the application ID to link the attachment to the application
//        volunteeringApplicationRepository.save(attachment);
//
//        return application;
//    }
@Override
@Transactional
public VolunteeringApplication applyForActivityWithFile(VolunteeringApplication volunteeringApplication, Long activityId, MultipartFile file) {

    // Retrieve the activity associated with the application
    VolunteeringActivity activity = volunteeringActivityRepository.findById(activityId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid activity ID"));

    // Associate the activity with the application
    volunteeringApplication.setActivity(activity);

    // Save the application
    VolunteeringApplication application = volunteeringApplicationRepository.save(volunteeringApplication);

    // Save the file and associate it with the application
    VolunteeringApplication attachment = new VolunteeringApplication();
    attachment.setVolunteerNames(volunteeringApplication.getVolunteerNames());
    attachment.setVolunteerAge(volunteeringApplication.getVolunteerAge());
    attachment.setUserEmail(volunteeringApplication.getUserEmail());
    attachment.setVolunteerPhoneNumber(volunteeringApplication.getVolunteerPhoneNumber());
    attachment.setVolunteerAddress(volunteeringApplication.getVolunteerAddress());
    attachment.setActivity(volunteeringApplication.getActivity());
    attachment.setVolunteerStatus(volunteeringApplication.getVolunteerStatus());

    attachment.setDocId(UUID.randomUUID().toString());
    attachment.setFileName(file.getOriginalFilename());
    attachment.setFileType(file.getContentType());
    try {
        attachment.setVolunteerDocument(file.getBytes()); // store the file content as byte array
        attachment.setContentType(file.getContentType()); // set the content type
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    attachment.setId(application.getId()); // set the application ID to link the attachment to the application
    volunteeringApplicationRepository.save(attachment);

    return application;
}



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
@Override
public List<VolunteeringApplication> getApplicationsForUser(String userEmail) {
    return volunteeringApplicationRepository.findByUserEmail(userEmail);
}
    }



