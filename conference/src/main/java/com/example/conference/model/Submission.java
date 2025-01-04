package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String documentPDF; // Path to the PDF file

    @ManyToOne(optional = false)
    private Author author;

    @ManyToOne(optional = false)
    private Conference conference;

    //to do : add the relation with the author where he adds the article
    //to do : add the relation with the conference where the article is submitted
    //to do : add the relation with the reviewer where the article is reviewed


}

