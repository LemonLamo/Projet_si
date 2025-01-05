package com.example.conference.controller;

import com.example.conference.model.Submission;
import com.example.conference.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // Retrieve all submissions
    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        List<Submission> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(submissions);
    }

    // Retrieve a submission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        Submission submission = submissionService.getSubmissionById(id);
        return ResponseEntity.ok(submission);
    }

    // Update an existing submission
    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(
            @PathVariable Long id,
            @RequestBody Submission updatedSubmission) {
        Submission submission = submissionService.updateSubmission(id, updatedSubmission);
        return ResponseEntity.ok(submission);
    }

    // Delete a submission by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve submissions by conference ID
    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Submission>> getSubmissionsByConferenceId(@PathVariable Long conferenceId) {
        List<Submission> submissions = submissionService.getSubmissionsByConferenceId(conferenceId);
        return ResponseEntity.ok(submissions);
    }

    // Retrieve submissions by author ID
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAuthorId(@PathVariable Long authorId) {
        List<Submission> submissions = submissionService.getSubmissionsByAuthorId(authorId);
        return ResponseEntity.ok(submissions);
    }

    // Retrieve submissions by evaluator ID
    @GetMapping("/evaluator/{evaluatorId}")
    public ResponseEntity<List<Submission>> getSubmissionsByEvaluatorId(@PathVariable Long evaluatorId) {
        List<Submission> submissions = submissionService.getSubmissionsByEvaluatorId(evaluatorId);
        return ResponseEntity.ok(submissions);
    }

    // Add an author to an existing submission
    @PostMapping("/{submissionId}/add-author/{authorId}")
    public ResponseEntity<Submission> addAuthorToSubmission(
            @PathVariable Long submissionId,
            @PathVariable Long authorId) {
        Submission submission = submissionService.addAuthorToSubmission(submissionId, authorId);
        return ResponseEntity.ok(submission);
    }

    // Assign an evaluator to a submission
    @PostMapping("/{submissionId}/assign-evaluator/{evaluatorId}")
    public ResponseEntity<Submission> assignEvaluatorToSubmission(
            @PathVariable Long submissionId,
            @PathVariable Long evaluatorId) {
        Submission submission = submissionService.assignEvaluatorToSubmission(submissionId, evaluatorId);
        return ResponseEntity.ok(submission);
    }

}

