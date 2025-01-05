package com.example.conference.repository;

import com.example.conference.model.Evaluator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluatorRepository extends JpaRepository<Evaluator, Long> {

    Optional<Evaluator> findByName(String name);


    List<Evaluator> findByActivityDomain(String activityDomain);

}
