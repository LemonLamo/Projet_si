package com.example.conference.repository;

import com.example.conference.model.Conference;
import com.example.conference.model.ConferenceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    // Find conferences by title
    List<Conference> findByTitle(String title);

    // Find conferences by theme
    List<Conference> findByTheme(String theme);

    // Find conferences by state
    List<Conference> findByState(ConferenceState state);

    // Find conferences by start date range
    List<Conference> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Find conferences by editor ID
    List<Conference> findByEditorId(Long editorId);

    // Custom query: Find all conferences with more than a certain number of submissions
    @Query("SELECT c FROM Conference c WHERE SIZE(c.submissions) > :count")
    List<Conference> findConferencesWithSubmissionsGreaterThan(@Param("count") int count);

    // Custom query: Find conferences starting after a specific date
    @Query("SELECT c FROM Conference c WHERE c.startDate > :date")
    List<Conference> findConferencesStartingAfter(@Param("date") LocalDate date);
}
