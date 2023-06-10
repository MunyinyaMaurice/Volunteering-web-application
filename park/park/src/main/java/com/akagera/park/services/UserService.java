package com.akagera.park.services;

import com.akagera.park.model.User;
import com.akagera.park.model.VolunteeringApplication;

import java.util.List;


public interface UserService {

    void saveUser(User user);

    static void updatePassword(User user, String newPassword) {
    }

    List<Object> isUserPresent(User user);

    User findUserByEmail(String email);
    List<VolunteeringApplication> getApplicationsForUser(String volunteerEmail);

    void createPasswordResetTokenForUser(User user, String token);

    String getRole(String email);
}