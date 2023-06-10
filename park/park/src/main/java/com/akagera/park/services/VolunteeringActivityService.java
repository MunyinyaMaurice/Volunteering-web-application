package com.akagera.park.services;




import com.akagera.park.model.VolunteeringActivity;
import org.springframework.data.domain.Page;

import java.util.List;


    public interface VolunteeringActivityService {
        VolunteeringActivity createActivity(VolunteeringActivity volunteeringActivity);
        List<VolunteeringActivity> getAllActivities();
        VolunteeringActivity getActivityById(Long actId);
        void deleteActivity(Long id);
        Page<VolunteeringActivity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}


