package com.example.conference.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.Set;
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
    @JsonBackReference
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
    @JsonBackReference
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    // Many-to-Many relationship with Evaluator
    @ManyToMany
    @JsonBackReference
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

    public Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(java.lang.String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }

    public String getPdfDocumentPath() {
        return pdfDocumentPath;
    }

    public void setPdfDocumentPath(java.lang.String pdfDocumentPath) {
        this.pdfDocumentPath = pdfDocumentPath;
    }

    public java.util.Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(java.util.Set<Author> authors) {
        this.authors = authors;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public java.util.Set<Evaluator> getEvaluators() {
        return evaluators;
    }

    public void setEvaluators(java.util.Set<Evaluator> evaluators) {
        this.evaluators = evaluators;
    }
}


//to do : add the relation with the author where he adds the article
//to do : add the relation with the conference where the article is submitted
//to do : add the relation with the reviewer where the article is reviewed

