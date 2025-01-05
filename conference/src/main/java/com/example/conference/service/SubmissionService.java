package com.example.conference.service;

import com.example.conference.model.Author;
import com.example.conference.model.Conference;
import com.example.conference.model.Evaluator;
import com.example.conference.model.Submission;
import com.example.conference.repository.ConferenceRepository;
import com.example.conference.repository.SubmissionRepository;
import com.example.conference.repository.AuthorRepository;
import com.example.conference.repository.EvaluatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository; // Repository for Submission
    private final ConferenceRepository conferenceRepository; // Repository for Conference
    private final AuthorRepository authorRepository; // Repository for Author
    private final EvaluatorRepository evaluatorRepository; // Repository for Evaluator

    public SubmissionService(SubmissionRepository submissionRepository,
                             ConferenceRepository conferenceRepository,
                             AuthorRepository authorRepository,
                             EvaluatorRepository evaluatorRepository) {
        this.submissionRepository = submissionRepository;
        this.conferenceRepository = conferenceRepository;
        this.authorRepository = authorRepository;
        this.evaluatorRepository = evaluatorRepository;
    }

    // Retrieve all submissions
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    // Retrieve a submission by ID
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + id));
    }

    // Create a new submission and link it to an author and a conference
    public Submission createSubmission(Long authorId, Long conferenceId, Submission submission) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + authorId));
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + conferenceId));

        // Link the submission to the author and conference
        submission.getAuthors().add(author);
        submission.setConference(conference);
        submissionRepository.save(submission);

        // Ensure bidirectional consistency
        author.getSubmissions().add(submission);
        return submission;
    }

    // Assign an evaluator to a submission
    public Submission assignEvaluatorToSubmission(Long submissionId, Long evaluatorId) {
        Submission submission = getSubmissionById(submissionId);
        Evaluator evaluator = evaluatorRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found with ID: " + evaluatorId));

        // Link the evaluator to the submission
        submission.getEvaluators().add(evaluator);
        submissionRepository.save(submission);

        return submission;
    }

    // Update an existing submission
    public Submission updateSubmission(Long id, Submission updatedSubmission) {
        Submission existingSubmission = getSubmissionById(id);

        // Update fields
        existingSubmission.setArticleTitle(updatedSubmission.getArticleTitle());
        existingSubmission.setSummary(updatedSubmission.getSummary());
        existingSubmission.setPdfDocumentPath(updatedSubmission.getPdfDocumentPath());

        return submissionRepository.save(existingSubmission);
    }

    // Delete a submission by ID
    public void deleteSubmission(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new RuntimeException("Submission not found with ID: " + id);
        }
        submissionRepository.deleteById(id);
    }

    // Retrieve submissions by conference ID
    public List<Submission> getSubmissionsByConferenceId(Long conferenceId) {
        return submissionRepository.findByConferenceId(conferenceId);
    }

    // Retrieve submissions by author ID
    public List<Submission> getSubmissionsByAuthorId(Long authorId) {
        return submissionRepository.findByAuthorId(authorId);
    }

    // Retrieve submissions assigned to an evaluator
    public List<Submission> getSubmissionsByEvaluatorId(Long evaluatorId) {
        return submissionRepository.findByEvaluatorId(evaluatorId);
    }

    // Add an author to an existing submission
    public Submission addAuthorToSubmission(Long submissionId, Long authorId) {
        Submission submission = getSubmissionById(submissionId);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + authorId));

        // Link the author to the submission
        submission.getAuthors().add(author);
        author.getSubmissions().add(submission); // Ensure bidirectional consistency
        return submissionRepository.save(submission);
    }
}
