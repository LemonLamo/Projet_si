package com.example.conference.controller;

import com.example.conference.model.Author;
import com.example.conference.model.Submission;
import com.example.conference.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Retrieve all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Retrieve an author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    // Retrieve an author by name
    @GetMapping("/name")
    public ResponseEntity<Author> getAuthorByName(@RequestParam String name) {
        return authorService.getAuthorByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve authors by writing domain
    @GetMapping("/domain")
    public ResponseEntity<List<Author>> getAuthorsByWritingDomain(@RequestParam String writingDomain) {
        List<Author> authors = authorService.getAuthorsByWritingDomain(writingDomain);
        return ResponseEntity.ok(authors);
    }

    // Create a new author
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(201).body(createdAuthor);
    }

    // Update an existing author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @RequestBody Author updatedAuthor) {
        Author author = authorService.updateAuthor(id, updatedAuthor);
        return ResponseEntity.ok(author);
    }

    // Delete an author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    // Add an article to a submission
    @PostMapping("/{authorId}/submissions/{submissionId}")
    public ResponseEntity<Submission> addArticleToSubmission(
            @PathVariable Long authorId,
            @PathVariable Long submissionId) {
        Submission updatedSubmission = authorService.addArticleToSubmission(authorId, submissionId);
        return ResponseEntity.ok(updatedSubmission);
    }
}
