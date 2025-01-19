package com.example.conference.controller;

import com.example.conference.model.Evaluator;
import com.example.conference.model.Submission;
import com.example.conference.service.EvaluatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluators")
public class EvaluatorController {

    private final EvaluatorService evaluatorService;

    public EvaluatorController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    // Retrieve all evaluators
    @GetMapping
    public ResponseEntity<List<Evaluator>> getAllEvaluators() {
        return ResponseEntity.ok(evaluatorService.getAllEvaluators());
    }

    // Retrieve an evaluator by ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluator> getEvaluatorById(@PathVariable Long id) {
        return ResponseEntity.ok(evaluatorService.getEvaluatorById(id));
    }

    // Retrieve an evaluator by name
    @GetMapping("/name")
    public ResponseEntity<Evaluator> getEvaluatorByName(@RequestParam String name) {
        return evaluatorService.getEvaluatorByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve evaluators by activity domain
    @GetMapping("/domain")
    public ResponseEntity<List<Evaluator>> getEvaluatorsByActivityDomain(@RequestParam String activityDomain) {
        return ResponseEntity.ok(evaluatorService.getEvaluatorsByActivityDomain(activityDomain));
    }

    // Create a new evaluator
    @PostMapping
    public ResponseEntity<Evaluator> createEvaluator(@RequestBody Evaluator evaluator) {
        return ResponseEntity.status(201).body(evaluatorService.createEvaluator(evaluator));
    }

    // Update an existing evaluator
    @PutMapping("/{id}")
    public ResponseEntity<Evaluator> updateEvaluator(@PathVariable Long id, @RequestBody Evaluator updatedEvaluator) {
        return ResponseEntity.ok(evaluatorService.updateEvaluator(id, updatedEvaluator));
    }

    // Delete an evaluator by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluator(@PathVariable Long id) {
        evaluatorService.deleteEvaluator(id);
        return ResponseEntity.noContent().build();
    }

    // Assign an evaluator to a submission
    @PostMapping("/{evaluatorId}/submissions/{submissionId}")
    public ResponseEntity<Submission> assignSubmissionToEvaluator(
            @PathVariable Long evaluatorId,
            @PathVariable Long submissionId) {
        return ResponseEntity.ok(evaluatorService.assignSubmissionToEvaluator(evaluatorId, submissionId));
    }

    // Remove an evaluator from a submission
    @DeleteMapping("/{evaluatorId}/submissions/{submissionId}")
    public ResponseEntity<Submission> removeSubmissionFromEvaluator(
            @PathVariable Long evaluatorId,
            @PathVariable Long submissionId) {
        return ResponseEntity.ok(evaluatorService.removeSubmissionFromEvaluator(evaluatorId, submissionId));
    }
}
