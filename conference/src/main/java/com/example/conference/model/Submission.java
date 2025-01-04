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
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String articleTitle;

    @Column(length = 500)
    private String summary;

    @Column(nullable = false)
    private String pdfDocumentPath;

    // Many-to-Many relationship with Author
    @ManyToMany
    @JoinTable(
            name = "submission_author",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    // Utility method to add an author
    public void addAuthor(Author author) {
        authors.add(author);
        author.getSubmissions().add(this); // Ensure bidirectional relationship consistency
    }

    // Utility method to remove an author
    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getSubmissions().remove(this); // Ensure bidirectional relationship consistency
    }

    public String getSubmissionInfo() {
        return String.format("Submission: %s (%d authors)", articleTitle, authors.size());
    }


    // Many-to-One relationship with Conference
    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    // Many-to-Many relationship with Evaluator
    @ManyToMany
    @JoinTable(
            name = "submission_evaluator",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluator_id")
    )
    private Set<Evaluator> evaluators = new HashSet<>();

    // Utility method to add an evaluator
    public void addEvaluator(Evaluator evaluator) {
        evaluators.add(evaluator);
    }
    //utility method to remove an evaluator
    public void removeEvaluator(Evaluator evaluator) {
        evaluators.remove(evaluator);
    }
}


//to do : add the relation with the author where he adds the article
//to do : add the relation with the conference where the article is submitted
//to do : add the relation with the reviewer where the article is reviewed

