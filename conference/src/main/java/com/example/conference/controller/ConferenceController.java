package com.example.conference.controller;

import com.example.conference.model.Conference;
import com.example.conference.model.ConferenceState;
import com.example.conference.service.ConferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    // Retrieve all conferences
    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        List<Conference> conferences = conferenceService.getAllConferences();
        return ResponseEntity.ok(conferences);
    }

    // Retrieve a conference by ID
    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Long id) {
        Conference conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    // Create a new conference
    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference createdConference = conferenceService.createConference(conference);
        return ResponseEntity.status(201).body(createdConference);
    }

    // Update an existing conference
    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(
            @PathVariable Long id,
            @RequestBody Conference updatedConference) {
        Conference conference = conferenceService.updateConference(id, updatedConference);
        return ResponseEntity.ok(conference);
    }

    // Delete a conference by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve conferences by state
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Conference>> getConferencesByState(@PathVariable ConferenceState state) {
        List<Conference> conferences = conferenceService.getConferencesByState(state);
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/editor/{editorId}")
    public ResponseEntity<List<Conference>> getConferencesByEditorId(@PathVariable Long editorId) {
        List<Conference> conferences = conferenceService.getConferencesByEditorId(editorId);
        return ResponseEntity.ok(conferences);
    }
    // Check if a conference is open for submissions
    @GetMapping("/{id}/is-open")
    public ResponseEntity<Boolean> isConferenceOpenForSubmissions(@PathVariable Long id) {
        boolean isOpen = conferenceService.isConferenceOpenForSubmissions(id);
        return ResponseEntity.ok(isOpen);
    }
}
