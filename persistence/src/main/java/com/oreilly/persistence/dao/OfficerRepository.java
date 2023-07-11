package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Integer> {
    List<Officer> findByRank(Rank rank);
    List<Officer> findAllByLastNameLikeAndRank(String lastName, Rank rank);
}
