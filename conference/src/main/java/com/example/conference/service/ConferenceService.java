package com.example.conference.service;

import com.example.conference.model.Conference;
import com.example.conference.model.ConferenceState;
import com.example.conference.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    // Retrieve all conferences
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    // Retrieve a conference by ID
    public Conference getConferenceById(Long id) {
        return conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found with ID: " + id));
    }

    // Create a new conference
    public Conference createConference(Conference conference) {
        if (conference.getStartDate().isAfter(conference.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        return conferenceRepository.save(conference);
    }

    // Update an existing conference
    public Conference updateConference(Long id, Conference updatedConference) {
        Conference existingConference = getConferenceById(id);

        // Update the fields
        existingConference.setTitle(updatedConference.getTitle());
        existingConference.setTheme(updatedConference.getTheme());
        existingConference.setStartDate(updatedConference.getStartDate());
        existingConference.setEndDate(updatedConference.getEndDate());
        existingConference.setState(updatedConference.getState());
        existingConference.setEditor(updatedConference.getEditor());

        return conferenceRepository.save(existingConference);
    }

    // Delete a conference
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new RuntimeException("Conference not found with ID: " + id);
        }
        conferenceRepository.deleteById(id);
    }



    // Retrieve conferences within a specific date range
    public List<Conference> getConferencesByDateRange(LocalDate startDate, LocalDate endDate) {
        return conferenceRepository.findByStartDateBetween(startDate, endDate);
    }

    // Retrieve conferences created by a specific editor
    public List<Conference> getConferencesByEditorId(Long editorId) {
        return conferenceRepository.findByEditorId(editorId);
    }

    // Retrieve conferences with more than a specific number of submissions
    public List<Conference> getConferencesWithSubmissionsGreaterThan(int submissionCount) {
        return conferenceRepository.findConferencesWithSubmissionsGreaterThan(submissionCount);
    }

    // Retrieve conferences starting after a specific date
    public List<Conference> getConferencesStartingAfter(LocalDate date) {
        return conferenceRepository.findConferencesStartingAfter(date);
    }

    // Check if a conference is open for submissions
    public boolean isConferenceOpenForSubmissions(Long id) {
        Conference conference = getConferenceById(id);
        return conference.isOpenForSubmissions();
    }
}

