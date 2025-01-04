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
    private float score; // Must be between 0 and 10

    private String comments;

    @ManyToOne(optional = false)
    private Submission submission;

    @ManyToOne(optional = false)
    private Evaluator evaluator;

    public String getEvaluationDetails() {
        return String.format("Score: %.1f, Comments: %s", score, comments);
    }
}
