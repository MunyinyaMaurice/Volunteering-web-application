package com.akagera.park.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@NoArgsConstructor
@ToString
public class VolunteeringApplicationResponse {

    private String volunteerNames;


    private String activityName;

    public VolunteeringApplicationResponse(String volunteerNames, String activityName) {
        this.volunteerNames = volunteerNames;
        this.activityName = activityName;
    }
}
