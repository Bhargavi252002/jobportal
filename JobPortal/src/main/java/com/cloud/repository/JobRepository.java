package com.cloud.repository;
import com.cloud.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface JobRepository extends JpaRepository<Jobs, UUID> {

    @Query(value = "SELECT * FROM jobs j WHERE LOWER(j.job_role) LIKE LOWER(CONCAT('%', :jobRole, '%'))", nativeQuery = true)
    List<Jobs> searchByJobRole(String jobRole);
}
