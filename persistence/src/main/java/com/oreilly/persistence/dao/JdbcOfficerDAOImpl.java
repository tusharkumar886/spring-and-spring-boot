package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ConstantConditions")
@Repository
public class JdbcOfficerDAOImpl implements OfficerDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Officer save(Officer officer) {
        return null;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        try (Stream<Officer> stream=jdbcTemplate.queryForStream("select * from officers where id=?")) {
            return stream.findFirst();
        }
    }

    @Override
    public List<Officer> findAll() {
        return null;
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT 1 FROM officers WHERE id=?", Boolean.class, id);
    }
}
