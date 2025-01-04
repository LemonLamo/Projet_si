package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
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
    private Set<Submission> submissions = new HashSet<>();

    // Utility method to get author information
    public String getAuthorInfo() {
        return String.format("Author: %s , Writing Domain: %s", getName(), writingDomain);
    }

}

