package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true) // Includes User's attributes in the `toString` method
@Entity
public class Author extends User {
    private int numberOfWorksCreated;

    private String writingDomain;

    public String getAuthorInfo() {
        return String.format("Author: %s , Writing Domain: %s", getName(), writingDomain);
    }
}
