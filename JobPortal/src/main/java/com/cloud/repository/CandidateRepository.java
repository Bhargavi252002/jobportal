package com.cloud.repository;
import com.cloud.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {

    @Query(value = "SELECT * FROM candidate WHERE candidate_email = ?1", nativeQuery = true)
    Candidate findByCandidateEmail(String email);
}
