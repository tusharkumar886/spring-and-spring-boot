package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OfficerRepositoryTest {

    @Autowired
    private OfficerRepository repository;

    @Autowired
    private JdbcTemplate template;

    private List<Integer> getIds() {
        return template.query("select id from officers",
                (rs, rowNum) -> rs.getInt("id"));
    }

    @Test
    void save() {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        officer = repository.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    void findByIdThatExists() {
        int id = getIds().get(0);
        Optional<Officer> officer = repository.findById(id);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    void findByIdThatDoesNotExist() {
        assertThat(getIds()).doesNotContain(999);
        Optional<Officer> officer = repository.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    void count() {
        assertEquals(getIds().size(), repository.count());
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
        getIds().forEach(id -> {
            Optional<Officer> officer = repository.findById(id);
            assertTrue(officer.isPresent());
            repository.delete(officer.get());
        });
        assertEquals(0, repository.count());
    }

    @Test
    void existsById() {
        getIds().forEach(id -> assertTrue(repository.existsById(id)));
    }

    @Test
    void findByRank() {
        repository.findByRank(Rank.CAPTAIN).forEach(captain -> assertEquals(Rank.CAPTAIN, captain.getRank()));
    }

    @Test
    void findAllByLastNameLikeAndRank() {
        List<Officer> officers = repository.findAllByLastNameLikeAndRank("%i%", Rank.CAPTAIN);
        assertThat(officers).extracting(Officer::getRank).containsOnly(Rank.CAPTAIN);
        assertThat(officers).extracting(Officer::getLastName).allMatch(n -> n.contains("i"));
        System.out.println(officers);
        assertEquals(3, officers.size());
    }
}