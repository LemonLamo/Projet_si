package com.example.conference.repository;

import com.example.conference.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // Find submissions by title (case-insensitive)
    List<Submission> findByArticleTitleContainingIgnoreCase(String articleTitle);

    // Find submissions associated with a specific conference
    List<Submission> findByConferenceId(Long conferenceId);

    // Find submissions linked to a specific author
    @Query("SELECT s FROM Submission s JOIN s.authors a WHERE a.id = :authorId")
    List<Submission> findByAuthorId(@Param("authorId") Long authorId);

    // Find submissions assigned to a specific evaluator
    @Query("SELECT s FROM Submission s JOIN s.evaluators e WHERE e.id = :evaluatorId")
    List<Submission> findByEvaluatorId(@Param("evaluatorId") Long evaluatorId);

    // Find submissions with more than a specified number of authors
    @Query("SELECT s FROM Submission s WHERE SIZE(s.authors) > :authorCount")
    List<Submission> findByAuthorCountGreaterThan(@Param("authorCount") int authorCount);

    // Find submissions with more than a specified number of evaluators
    @Query("SELECT s FROM Submission s WHERE SIZE(s.evaluators) > :evaluatorCount")
    List<Submission> findByEvaluatorCountGreaterThan(@Param("evaluatorCount") int evaluatorCount);

    // Find submissions with a specific evaluator in a specific conference
    @Query("SELECT s FROM Submission s WHERE s.conference.id = :conferenceId AND :evaluatorId MEMBER OF s.evaluators")
    List<Submission> findByConferenceAndEvaluator(@Param("conferenceId") Long conferenceId, @Param("evaluatorId") Long evaluatorId);
}
