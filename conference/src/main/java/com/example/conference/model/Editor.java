package com.example.conference.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Editor extends User {

    @Getter
    @Setter
    private String activityDomain;

    // One-to-Many relationship with Conference
    @OneToMany(mappedBy = "editor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Conference> conferences = new HashSet<>();

    // Utility method to add a conference
    public void addConference(Conference conference) {
        conferences.add(conference);
        conference.setEditor(this);
    }

    // Utility method to remove a conference
    public void removeConference(Conference conference) {
        conferences.remove(conference);
        conference.setEditor(null);
    }

    // Method to assign a submission to an evaluator
    public void assignSubmissionToEvaluator(Submission submission, Evaluator evaluator) {
        // Check if evaluator is already assigned to the submission
        if (!submission.getEvaluators().contains(evaluator)) {
            submission.addEvaluator(evaluator);
        }
    }

    // Method to remove a submission from an evaluator

    public void removeSubmissionFromEvaluator(Submission submission, Evaluator evaluator) {
        // Check if evaluator is assigned to the submission
        if (submission.getEvaluators().contains(evaluator)) {
            submission.removeEvaluator(evaluator);
        }
    }
}



