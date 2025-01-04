package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Evaluator extends User {
    private String activityDomain;

    @Enumerated(EnumType.STRING)
    private EvaluationSector sector;

    public String evaluateSubmission(Submission submission) {
        return "Evaluating submission: " + submission.getArticleTitle();
    }
}
