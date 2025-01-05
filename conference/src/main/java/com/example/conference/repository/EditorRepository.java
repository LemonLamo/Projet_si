package com.example.conference.repository;

import com.example.conference.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {

    Optional<Editor> findByName(String name);

    List<Editor> findByActivityDomain(String activityDomain);

}

