package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Editor extends User {
    private String activityDomain;

    private String publishingHouseName;

    private int contactNumber;

    public void assignSubmissionToEvaluator(Submission submission, Evaluator evaluator) {
        //logic for assigning a submission later...
        System.out.println("Assigning submission to evaluator...");
    }
}

