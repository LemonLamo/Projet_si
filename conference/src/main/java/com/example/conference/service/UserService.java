package com.example.conference.service;

import com.example.conference.model.User;
import com.example.conference.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Retrieve a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Create a new user
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);

        // Update fields
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setYearOfBirth(updatedUser.getYearOfBirth());

        return userRepository.save(existingUser);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Retrieve users by name (case-insensitive)
    public List<User> getUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    // Retrieve users by surname (case-insensitive)
    public List<User> getUsersBySurname(String surname) {
        return userRepository.findBySurnameContainingIgnoreCase(surname);
    }

}
