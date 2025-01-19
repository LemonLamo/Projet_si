package com.example.conference.controller;

import com.example.conference.model.Conference;
import com.example.conference.model.Editor;
import com.example.conference.model.Submission;
import com.example.conference.service.EditorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/editors")
public class EditorController {

    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    // Retrieve all editors
    @GetMapping
    public ResponseEntity<List<Editor>> getAllEditors() {
        return ResponseEntity.ok(editorService.getAllEditors());
    }

    // Retrieve an editor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Editor> getEditorById(@PathVariable Long id) {
        return ResponseEntity.ok(editorService.getEditorById(id));
    }

    // Retrieve an editor by name
    @GetMapping("/name")
    public ResponseEntity<Editor> getEditorByName(@RequestParam String name) {
        return editorService.getEditorByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve editors by activity domain
    @GetMapping("/domain")
    public ResponseEntity<List<Editor>> getEditorsByActivityDomain(@RequestParam String activityDomain) {
        return ResponseEntity.ok(editorService.getEditorsByActivityDomain(activityDomain));
    }

    // Create a new editor
    @PostMapping
    public ResponseEntity<Editor> createEditor(@RequestBody Editor editor) {
        return ResponseEntity.status(201).body(editorService.createEditor(editor));
    }

    // Update an existing editor
    @PutMapping("/{id}")
    public ResponseEntity<Editor> updateEditor(@PathVariable Long id, @RequestBody Editor updatedEditor) {
        return ResponseEntity.ok(editorService.updateEditor(id, updatedEditor));
    }

    // Delete an editor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditor(@PathVariable Long id) {
        editorService.deleteEditor(id);
        return ResponseEntity.noContent().build();
    }

    // Add a conference to an editor
    @PostMapping("/{editorId}/conferences")
    public ResponseEntity<Conference> addConference(
            @PathVariable Long editorId,
            @RequestBody Conference conference) {
        return ResponseEntity.ok(editorService.addConference(editorId, conference));
    }

    // Assign a submission to an evaluator
    @PostMapping("/{editorId}/submissions/{submissionId}/assign/{evaluatorId}")
    public ResponseEntity<Submission> assignSubmissionToEvaluator(
            @PathVariable Long submissionId,
            @PathVariable Long evaluatorId) {
        return ResponseEntity.ok(editorService.assignSubmissionToEvaluator(submissionId, evaluatorId));
    }

    // Remove a submission from an evaluator
    @DeleteMapping("/{editorId}/submissions/{submissionId}/remove/{evaluatorId}")
    public ResponseEntity<Submission> removeSubmissionFromEvaluator(
            @PathVariable Long submissionId,
            @PathVariable Long evaluatorId) {
        return ResponseEntity.ok(editorService.removeSubmissionFromEvaluator(submissionId, evaluatorId));
    }
}
