package com.example.conference.repository;

import com.example.conference.model.Evaluation;
import com.example.conference.model.EvaluationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Find evaluations by state (Accepted, Rejected, In Revision)
    List<Evaluation> findByState(EvaluationState state);

    // Find evaluations by submission ID
    List<Evaluation> findBySubmissionId(Long submissionId);

    // Find evaluations by evaluator ID
    List<Evaluation> findByEvaluatorId(Long evaluatorId);

    // Find evaluations by editor ID
    List<Evaluation> findByEditorId(Long editorId);

    // Find evaluations with a score greater than or equal to a specified value
    List<Evaluation> findByScoreGreaterThanEqual(float score);

    // Find evaluations with a score less than a specified value
    List<Evaluation> findByScoreLessThan(float score);

}
