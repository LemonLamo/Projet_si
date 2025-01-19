package com.example.conference.service;

import com.example.conference.model.Conference;
import com.example.conference.model.Editor;
import com.example.conference.model.Evaluator;
import com.example.conference.model.Submission;
import com.example.conference.repository.ConferenceRepository;
import com.example.conference.repository.EditorRepository;
import com.example.conference.repository.EvaluatorRepository;
import com.example.conference.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorService {

    private final EditorRepository editorRepository;
    private final ConferenceRepository conferenceRepository;
    private final SubmissionRepository submissionRepository;
    private final EvaluatorRepository evaluatorRepository;

    public EditorService(EditorRepository editorRepository,
                         ConferenceRepository conferenceRepository,
                         SubmissionRepository submissionRepository,
                         EvaluatorRepository evaluatorRepository) {
        this.editorRepository = editorRepository;
        this.conferenceRepository = conferenceRepository;
        this.submissionRepository = submissionRepository;
        this.evaluatorRepository = evaluatorRepository;
    }

    // Retrieve all editors
    public List<Editor> getAllEditors() {
        return editorRepository.findAll();
    }

    // Retrieve an editor by ID
    public Editor getEditorById(Long id) {
        return editorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editor not found with ID: " + id));
    }

    // Retrieve an editor by name
    public Optional<Editor> getEditorByName(String name) {
        return editorRepository.findByName(name);
    }

    // Retrieve editors by activity domain
    public List<Editor> getEditorsByActivityDomain(String activityDomain) {
        return editorRepository.findByActivityDomain(activityDomain);
    }

    // Create a new editor
    public Editor createEditor(Editor editor) {
        return editorRepository.save(editor);
    }

    // Update an existing editor
    public Editor updateEditor(Long id, Editor updatedEditor) {
        Editor existingEditor = getEditorById(id);

        existingEditor.setName(updatedEditor.getName());
        existingEditor.setSurname(updatedEditor.getSurname());
        existingEditor.setEmail(updatedEditor.getEmail());
        existingEditor.setYearOfBirth(updatedEditor.getYearOfBirth());
        existingEditor.setActivityDomain(updatedEditor.getActivityDomain());

        return editorRepository.save(existingEditor);
    }

    // Delete an editor by ID
    public void deleteEditor(Long id) {
        if (!editorRepository.existsById(id)) {
            throw new RuntimeException("Editor not found with ID: " + id);
        }
        editorRepository.deleteById(id);
    }

    // Add a conference to an editor
    @Transactional
    public Conference addConference(Long editorId, Conference conference) {
        Editor editor = getEditorById(editorId);
        conference.setEditor(editor);
        return conferenceRepository.save(conference);
    }

    // Assign a submission to an evaluator
    @Transactional
    public Submission assignSubmissionToEvaluator(Long submissionId, Long evaluatorId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + submissionId));

        Evaluator evaluator = evaluatorRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found with ID: " + evaluatorId));

        if (!submission.getEvaluators().contains(evaluator)) {
            submission.addEvaluator(evaluator);
            return submissionRepository.save(submission);
        }

        throw new RuntimeException("Evaluator is already assigned to this submission.");
    }

    // Remove a submission from an evaluator
    @Transactional
    public Submission removeSubmissionFromEvaluator(Long submissionId, Long evaluatorId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + submissionId));

        Evaluator evaluator = evaluatorRepository.findById(evaluatorId)
                .orElseThrow(() -> new RuntimeException("Evaluator not found with ID: " + evaluatorId));

        if (submission.getEvaluators().contains(evaluator)) {
            submission.removeEvaluator(evaluator);
            return submissionRepository.save(submission);
        }

        throw new RuntimeException("Evaluator was not assigned to this submission.");
    }
}
