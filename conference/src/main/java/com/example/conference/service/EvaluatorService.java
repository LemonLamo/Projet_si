package com.example.conference.service;

import com.example.conference.model.Evaluator;
import com.example.conference.model.Submission;
import com.example.conference.repository.EvaluatorRepository;
import com.example.conference.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluatorService {

    private final EvaluatorRepository evaluatorRepository;
    private final SubmissionRepository submissionRepository;

    public EvaluatorService(EvaluatorRepository evaluatorRepository, SubmissionRepository submissionRepository) {
        this.evaluatorRepository = evaluatorRepository;
        this.submissionRepository = submissionRepository;
    }

    // Retrieve all evaluators
    public List<Evaluator> getAllEvaluators() {
        return evaluatorRepository.findAll();
    }

    // Retrieve an evaluator by ID
    public Evaluator getEvaluatorById(Long id) {
        return evaluatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluator not found with ID: " + id));
    }

    // Retrieve an evaluator by name
    public Optional<Evaluator> getEvaluatorByName(String name) {
        return evaluatorRepository.findByName(name);
    }

    // Retrieve evaluators by activity domain
    public List<Evaluator> getEvaluatorsByActivityDomain(String activityDomain) {
        return evaluatorRepository.findByActivityDomain(activityDomain);
    }

    // Create a new evaluator
    public Evaluator createEvaluator(Evaluator evaluator) {
        return evaluatorRepository.save(evaluator);
    }

    // Update an existing evaluator
    public Evaluator updateEvaluator(Long id, Evaluator updatedEvaluator) {
        Evaluator existingEvaluator = getEvaluatorById(id);

        existingEvaluator.setName(updatedEvaluator.getName());
        existingEvaluator.setSurname(updatedEvaluator.getSurname());
        existingEvaluator.setEmail(updatedEvaluator.getEmail());
        existingEvaluator.setYearOfBirth(updatedEvaluator.getYearOfBirth());
        existingEvaluator.setActivityDomain(updatedEvaluator.getActivityDomain());

        return evaluatorRepository.save(existingEvaluator);
    }

    // Delete an evaluator by ID
    public void deleteEvaluator(Long id) {
        if (!evaluatorRepository.existsById(id)) {
            throw new RuntimeException("Evaluator not found with ID: " + id);
        }
        evaluatorRepository.deleteById(id);
    }

    // Assign an evaluator to a submission
    @Transactional
    public Submission assignSubmissionToEvaluator(Long evaluatorId, Long submissionId) {
        Evaluator evaluator = getEvaluatorById(evaluatorId);
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + submissionId));

        if (!evaluator.getAssignedSubmissions().contains(submission)) {
            evaluator.getAssignedSubmissions().add(submission);
            submission.getEvaluators().add(evaluator);
            submissionRepository.save(submission);
        } else {
            throw new RuntimeException("Evaluator is already assigned to this submission.");
        }

        return submission;
    }

    // Remove an evaluator from a submission
    @Transactional
    public Submission removeSubmissionFromEvaluator(Long evaluatorId, Long submissionId) {
        Evaluator evaluator = getEvaluatorById(evaluatorId);
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + submissionId));

        if (evaluator.getAssignedSubmissions().contains(submission)) {
            evaluator.getAssignedSubmissions().remove(submission);
            submission.getEvaluators().remove(evaluator);
            submissionRepository.save(submission);
        } else {
            throw new RuntimeException("Evaluator was not assigned to this submission.");
        }

        return submission;
    }
}
