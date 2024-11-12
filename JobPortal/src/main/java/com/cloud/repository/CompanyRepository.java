package com.cloud.repository;
import com.cloud.entity.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CompanyRepository extends JpaRepository<Companies, UUID> {

    @Query("select c from Companies c where c.companyName=:companyName")
    Companies findByCompanyName(@Param("companyName") String companyName );
}
