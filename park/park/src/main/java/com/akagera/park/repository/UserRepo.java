package com.akagera.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akagera.park.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);



}

