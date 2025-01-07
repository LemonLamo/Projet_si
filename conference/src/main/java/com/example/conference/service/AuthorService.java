package com.example.conference.service;

import com.example.conference.model.Author;
import com.example.conference.model.Submission;
import com.example.conference.repository.AuthorRepository;
import com.example.conference.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final SubmissionRepository submissionRepository;

    public AuthorService(AuthorRepository authorRepository, SubmissionRepository submissionRepository) {
        this.authorRepository = authorRepository;
        this.submissionRepository = submissionRepository;
    }

    // Retrieve all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Retrieve an author by ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
    }

    // Retrieve an author by name
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    // Retrieve authors by writing domain
    public List<Author> getAuthorsByWritingDomain(String writingDomain) {
        return authorRepository.findByWritingDomain(writingDomain);
    }

    // Create a new author
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Update an existing author
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = getAuthorById(id);

        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setSurname(updatedAuthor.getSurname());
        existingAuthor.setEmail(updatedAuthor.getEmail());
        existingAuthor.setYearOfBirth(updatedAuthor.getYearOfBirth());
        existingAuthor.setWritingDomain(updatedAuthor.getWritingDomain());

        return authorRepository.save(existingAuthor);
    }

    // Delete an author by ID
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with ID: " + id);
        }
        authorRepository.deleteById(id);
    }


    // Add an article to a submission for a specific author
    @Transactional
    public Submission addArticleToSubmission(Long authorId, Long submissionId) {
        // Retrieve the author
        Author author = getAuthorById(authorId);

        // Retrieve the submission
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + submissionId));

        // Add the author to the submission
        submission.addAuthor(author);

        // Save the updated submission
        return submissionRepository.save(submission);
    }
}
