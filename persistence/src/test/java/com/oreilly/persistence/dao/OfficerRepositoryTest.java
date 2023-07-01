package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OfficerRepositoryTest {

    @Autowired
    private OfficerRepository repository;

    @Test
    void save() {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        officer = repository.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    void findByIdThatExists() {
        Optional<Officer> officer = repository.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    void findByIdThatDoesNotExist() {
        Optional<Officer> officer = repository.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    void count() {
        assertEquals(5, repository.count());
    }

    @Test
    void findAll() {
        List<String> dbNames = repository.findAll().stream()
                .map(Officer::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames).contains("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    void delete() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = repository.findById(id);
                    assertTrue(officer.isPresent());
                    repository.delete(officer.get());
                });
        assertEquals(0, repository.count());
    }

    @Test
    void existsById() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> assertTrue(repository.existsById(id)));
    }
}