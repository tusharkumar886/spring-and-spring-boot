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
class JdbcOfficerDAOImplTest {

    @Autowired
    OfficerDAO officerDAO;

    @Test
    void save() {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        officer = officerDAO.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    void findByIdThatExists() {
        Optional<Officer> officer = officerDAO.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    void findByIdThatDoesNotExist() {
        Optional<Officer> officer = officerDAO.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    void count() {
        assertEquals(5, officerDAO.count());
    }

    @Test
    void findAll() {
        List<String> dbNames = officerDAO.findAll().stream()
                .map(Officer::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames).contains("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    void delete() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = officerDAO.findById(id);
                    assertTrue(officer.isPresent());
                    officerDAO.delete(officer.get());
                });
        assertEquals(0, officerDAO.count());
    }

    @Test
    void existsById() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> assertTrue(officerDAO.existsById(id)));
    }
}