package com.example.conference.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JoinTable(
            name = "submission_evaluator",
            joinColumns = @JoinColumn(name = "evaluator_id"),
            inverseJoinColumns = @JoinColumn(name = "submission_id")
    )
    private Set<Submission> assignedSubmissions = new HashSet<>();

    // Method to check if the evaluator is assigned to a specific submission
    public boolean isAssignedToSubmission(Submission submission) {
        return assignedSubmissions.contains(submission);
    }

    public String getActivityDomain() {
        return activityDomain;
    }

    public void setActivityDomain(String activityDomain) {
        this.activityDomain = activityDomain;
    }

    public Set<Submission> getAssignedSubmissions() {
        return assignedSubmissions;
    }

    public void setAssignedSubmissions(Set<com.example.conference.model.Submission> assignedSubmissions) {
        this.assignedSubmissions = assignedSubmissions;
    }
}
