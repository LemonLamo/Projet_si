package com.example.conference.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.List;


@Getter
@Setter
@ToString
@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String theme;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ConferenceState state;

    // Many-to-One relationship with Editor
    @ManyToOne
    @JoinColumn(name = "editor_id", nullable = false)
    @JsonBackReference
    private Editor editor;

    // One-to-Many relationship with Submission
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Submission> submissions;

    public boolean isOpenForSubmissions() {
        return state == ConferenceState.OPEN_FOR_SUBMISSIONS;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public void setTheme(java.lang.String theme) {
        this.theme = theme;
    }

    public void setStartDate(java.time.LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(java.time.LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setState(ConferenceState state) {
        this.state = state;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public void setSubmissions(java.util.List<Submission> submissions) {
        this.submissions = submissions;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTheme() {
        return theme;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ConferenceState getState() {
        return state;
    }

    public Editor getEditor() {
        return editor;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }
}

