package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Editor extends User {

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
}


