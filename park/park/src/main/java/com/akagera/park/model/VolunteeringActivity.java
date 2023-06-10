package com.akagera.park.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    //@Builder
    @ToString
    @Entity
    @Table(name = "volunteering_activity")
    public class VolunteeringActivity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long actId;

        @Column(name = "activity_Name")
        private String activityName;

        @Column(name = "activity_type")
        private String activityType;

        @Column(name = "activity_description")
        private String activityDescription;

//        @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//        @CreationTimestamp
        private LocalDateTime activityTime;

        @Column(name = "activity_address")
        private String activityAddress;

        @Column(name = "activity_photo_url")
        private String activityPhotoUrl;
    @OneToMany(targetEntity = VolunteeringApplication.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="Activity_Application_fk",referencedColumnName = "actId")
    private List<VolunteeringApplication> volunteeringApplications;

        // getters and setters
    }


