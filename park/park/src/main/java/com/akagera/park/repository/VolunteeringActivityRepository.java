package com.akagera.park.repository;



import com.akagera.park.model.VolunteeringActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface VolunteeringActivityRepository extends JpaRepository<VolunteeringActivity, Long> {
//    List<VolunteeringActivity> getActivitiesByType(String activityType);
//    Optional<VolunteeringActivity> getActivityByName(String activityName);



    }



