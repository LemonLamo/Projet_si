package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private float score; // Must be between 1 and 10

    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EvaluationState state; // State of the evaluation (Accepted, Rejected, or In Revision)

    @ManyToOne(optional = false)
    private Submission submission;

    @ManyToOne(optional = false)
    private Evaluator evaluator;

    // Reference to the editor who can view the evaluation
    @ManyToOne(optional = false)
    private Editor editor;

    public String getEvaluationDetails() {
        return String.format("Score: %.1f, Comments: %s, State: %s", score, comments, state);
    }

    // Validation method to ensure score is between 1 and 10
    public void setScore(float score) {
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be between 1 and 10");
        }
        this.score = score;
    }
}
