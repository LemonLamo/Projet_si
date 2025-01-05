package com.example.conference.controller;

import com.example.conference.model.Evaluation;
import com.example.conference.model.EvaluationState;
import com.example.conference.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // Retrieve all evaluations
    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    // Retrieve an evaluation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Evaluation evaluation = evaluationService.getEvaluationById(id);
        return ResponseEntity.ok(evaluation);
    }

    // Create a new evaluation
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation createdEvaluation = evaluationService.createEvaluation(evaluation);
        return ResponseEntity.status(201).body(createdEvaluation);
    }

    // Update an existing evaluation
    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(
            @PathVariable Long id,
            @RequestBody Evaluation updatedEvaluation) {
        Evaluation evaluation = evaluationService.updateEvaluation(id, updatedEvaluation);
        return ResponseEntity.ok(evaluation);
    }

    // Delete an evaluation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve evaluations by state
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByState(@PathVariable EvaluationState state) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByState(state);
        return ResponseEntity.ok(evaluations);
    }

    // Retrieve evaluations for a specific submission
    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsBySubmissionId(@PathVariable Long submissionId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsBySubmissionId(submissionId);
        return ResponseEntity.ok(evaluations);
    }

    // Retrieve evaluations performed by a specific evaluator
    @GetMapping("/evaluator/{evaluatorId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByEvaluatorId(@PathVariable Long evaluatorId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByEvaluatorId(evaluatorId);
        return ResponseEntity.ok(evaluations);
    }

    // Retrieve evaluations assigned to a specific editor
    @GetMapping("/editor/{editorId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByEditorId(@PathVariable Long editorId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByEditorId(editorId);
        return ResponseEntity.ok(evaluations);
    }

}

