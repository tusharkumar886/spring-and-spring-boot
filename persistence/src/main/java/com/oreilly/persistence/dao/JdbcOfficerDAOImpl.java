package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.lang.constant.Constable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ConstantConditions")
@Repository
public class JdbcOfficerDAOImpl implements OfficerDAO {
    private final RowMapper<Officer> officerRowMapper =
            ((rs, rowNum) -> new Officer(rs.getInt("id"),
                    Rank.valueOf(rs.getString("rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name")));
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcOfficerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    private static Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Officer(rs.getInt("id"),
                Rank.valueOf(rs.getString("rank")),
                rs.getString("first_name"),
                rs.getString("last_name"));
    }

    @Override
    public Officer save(Officer officer) {
//        var params = Map.ofEntries(
//                Map.entry("rank", officer.getRank()),
//                Map.entry("first_name", officer.getFirstName()),
//                Map.entry("last_name", officer.getLastName())
//        );
//        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
//                .addValue("rank", officer.getRank())
//                .addValue("first_name", officer.getFirstName())
//                .addValue("last_name", officer.getLastName());
        SqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(officer);
        Integer newId = (Integer) jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
        officer.setId(newId);
        return officer;
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
