package com.akagera.park.repository;


import com.akagera.park.model.VolunteeringApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VolunteeringApplicationRepository extends JpaRepository<VolunteeringApplication, Long> {
//    @Query("SELECT new com.exam.Volunteering.Dto.VolunteeringApplicationResponse(v_a.activityName , a.volunteerNames) FROM VolunteeringActivity v_a JOIN v_a.volunteeringApplications a")
//    public List<VolunteeringApplicationResponse> getJoinInformation();
//VolunteeringApplication downloadFile(String fileId) throws Exception;
List<VolunteeringApplication> findByUserEmail(String userEmail);
}
