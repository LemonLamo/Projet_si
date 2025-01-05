package com.example.conference.service;

import com.example.conference.model.Evaluation;
import com.example.conference.model.EvaluationState;
import com.example.conference.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    // Retrieve all evaluations
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    // Retrieve an evaluation by ID
    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with ID: " + id));
    }

    // Create a new evaluation
    public Evaluation createEvaluation(Evaluation evaluation) {
        validateScore(evaluation.getScore());
        return evaluationRepository.save(evaluation);
    }

    // Update an existing evaluation
    public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
        Evaluation existingEvaluation = getEvaluationById(id);

        // Update fields
        existingEvaluation.setScore(updatedEvaluation.getScore());
        existingEvaluation.setComments(updatedEvaluation.getComments());
        existingEvaluation.setState(updatedEvaluation.getState());
        existingEvaluation.setSubmission(updatedEvaluation.getSubmission());
        existingEvaluation.setEvaluator(updatedEvaluation.getEvaluator());
        existingEvaluation.setEditor(updatedEvaluation.getEditor());

        validateScore(existingEvaluation.getScore());
        return evaluationRepository.save(existingEvaluation);
    }

    // Delete an evaluation by ID
    public void deleteEvaluation(Long id) {
        if (!evaluationRepository.existsById(id)) {
            throw new RuntimeException("Evaluation not found with ID: " + id);
        }
        evaluationRepository.deleteById(id);
    }

    // Retrieve evaluations by state
    public List<Evaluation> getEvaluationsByState(EvaluationState state) {
        return evaluationRepository.findByState(state);
    }

    // Retrieve evaluations for a specific submission
    public List<Evaluation> getEvaluationsBySubmissionId(Long submissionId) {
        return evaluationRepository.findBySubmissionId(submissionId);
    }

    // Retrieve evaluations performed by a specific evaluator
    public List<Evaluation> getEvaluationsByEvaluatorId(Long evaluatorId) {
        return evaluationRepository.findByEvaluatorId(evaluatorId);
    }

    // Retrieve evaluations assigned to a specific editor
    public List<Evaluation> getEvaluationsByEditorId(Long editorId) {
        return evaluationRepository.findByEditorId(editorId);
    }

    // Retrieve evaluations with a score above a specified value
    public List<Evaluation> getEvaluationsByScoreGreaterThan(float score) {
        return evaluationRepository.findByScoreGreaterThanEqual(score);
    }

    // Retrieve evaluations with a score below a specified value
    public List<Evaluation> getEvaluationsByScoreLessThan(float score) {
        return evaluationRepository.findByScoreLessThan(score);
    }

    // Validate that the score is within the range 1-10
    private void validateScore(float score) {
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be between 1 and 10");
        }
    }
}
