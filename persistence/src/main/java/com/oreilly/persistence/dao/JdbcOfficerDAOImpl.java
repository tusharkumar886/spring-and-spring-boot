package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ConstantConditions")
@Repository
public class JdbcOfficerDAOImpl implements OfficerDAO {
    private final RowMapper<Officer> officerRowMapper =
            ((rs, rowNum) -> new Officer(rs.getInt("id"),
                    Rank.valueOf(rs.getString("rank")),
                    rs.getString("firstName"),
                    rs.getString("lastName")));
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Officer(rs.getInt("id"),
                Rank.valueOf(rs.getString("rank")),
                rs.getString("firstName"),
                rs.getString("lastName"));
    }

    @Override
    public Officer save(Officer officer) {
        return null;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        try (Stream<Officer> stream = jdbcTemplate.queryForStream("select * from officers where id=?",
                officerRowMapper, id)) {
            return stream.findFirst();
        }
    }

    @Override
    public List<Officer> findAll() {
        // JdbcOfficerDAOImpl::mapRow and officerRowMapper can be used interchangeably
        return jdbcTemplate.query("select * from officers;", JdbcOfficerDAOImpl::mapRow);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers",
                Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("DELETE FROM officers WHERE id=?",
                officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT 1 FROM officers WHERE id=?",
                Boolean.class, id);
    }
}
