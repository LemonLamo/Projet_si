package com.example.conference.repository;

import com.example.conference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by name (case-insensitive)
    List<User> findByName(String name);

    // Find users by surname (case-insensitive)
    List<User> findBySurname(String surname);

    Optional<User> findByEmail(String email);
}
