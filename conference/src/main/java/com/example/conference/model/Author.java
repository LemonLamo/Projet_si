package com.example.conference.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true) // Includes User's attributes in the `toString` method
@Entity
public class Author extends User {

    //getters and setters
    @Getter
    @Setter
    private String writingDomain;

    // Many-to-Many relationship with Submission
    @ManyToMany(mappedBy = "authors")
    @JsonManagedReference
    private Set<Submission> submissions = new HashSet<>();

    // Utility method to get author information
    public String getAuthorInfo() {
        return String.format("Author: %s , Writing Domain: %s", getName(), writingDomain);
    }

    public String getWritingDomain() {
        return writingDomain;
    }

    public void setWritingDomain(java.lang.String writingDomain) {
        this.writingDomain = writingDomain;
    }

    public java.util.Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(java.util.Set<Submission> submissions) {
        this.submissions = submissions;
    }
}

