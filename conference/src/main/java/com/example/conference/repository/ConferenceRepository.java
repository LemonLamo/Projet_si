package com.example.conference.repository;
import com.example.conference.model.Conference;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ConferenceRepository implements JpaRepository<Conference, Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends Conference> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Conference> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Conference> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Conference getOne(Long aLong) {
        return null;
    }

    @Override
    public Conference getById(Long aLong) {
        return null;
    }

    @Override
    public Conference getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Conference> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Conference> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Conference> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Conference> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Conference> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Conference> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Conference, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Conference> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Conference> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Conference> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Conference> findAll() {
        return List.of();
    }

    @Override
    public List<Conference> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Conference entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Conference> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Conference> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Conference> findAll(Pageable pageable) {
        return null;
    }
}
