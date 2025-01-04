package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
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
    private Editor editor;

    // One-to-Many relationship with Submission
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Submission> submissions;

    public boolean isOpenForSubmissions() {
        return state == ConferenceState.OPEN_FOR_SUBMISSIONS;
    }
}

