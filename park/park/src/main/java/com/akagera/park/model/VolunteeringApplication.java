package com.akagera.park.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "volunteering_application")
public class VolunteeringApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volunteer_names")
    private String volunteerNames;

    @Column(name = "volunteer_age")
    private int volunteerAge;

    @Column(name = "volunteer_email")
    private String userEmail;

    @Column(name = "volunteer_phone_number")
    private String volunteerPhoneNumber;

    @Column(name = "volunteer_address")
    private String volunteerAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private VolunteeringActivity activity;

    @Column(name = "volunteer_status")
    private String volunteerStatus;

    private String docId;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] volunteerDocument;
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
