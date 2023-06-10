package com.akagera.park.services.impl;

import com.akagera.park.model.VolunteeringActivity;
import com.akagera.park.repository.VolunteeringActivityRepository;
import com.akagera.park.services.VolunteeringActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class VolunteeringActivityServiceImpl implements VolunteeringActivityService {

        @Autowired
        private VolunteeringActivityRepository volunteeringActivityRepository;

    @Override
    public VolunteeringActivity createActivity(VolunteeringActivity volunteeringActivity) {
        return volunteeringActivityRepository.save(volunteeringActivity);

    }

    @Override
    public List<VolunteeringActivity> getAllActivities() {
        return volunteeringActivityRepository.findAll() ;
    }



    @Override
    public VolunteeringActivity getActivityById(Long actId) {
        Optional<VolunteeringActivity> optional = volunteeringActivityRepository.findById(actId);
        VolunteeringActivity volunteer = null;
        if (optional.isPresent()){
            volunteer= optional.get();
        }else {
            throw new RuntimeException("Volunteer not found for id ::" +actId);
        }
        return volunteer; }


    @Override
    public void deleteActivity(Long id) {

        volunteeringActivityRepository.deleteById(id);
    }

    @Override
    public Page<VolunteeringActivity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.volunteeringActivityRepository.findAll(pageable);
    }

}


