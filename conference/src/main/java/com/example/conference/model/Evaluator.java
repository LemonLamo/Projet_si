package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Evaluator extends User {
    private String activityDomain;

    // Many-to-Many relationship with Submission through the assignments via Editor
    @ManyToMany
    @JoinTable(
            name = "evaluator_submission",
            joinColumns = @JoinColumn(name = "evaluator_id"),
            inverseJoinColumns = @JoinColumn(name = "submission_id")
    )
    private Set<Submission> assignedSubmissions = new HashSet<>();

    // Method to check if the evaluator is assigned to a specific submission
    public boolean isAssignedToSubmission(Submission submission) {
        return assignedSubmissions.contains(submission);
    }




}
