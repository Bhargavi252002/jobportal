package com.cloud.repository;
import com.cloud.entity.Applications;
import com.cloud.entity.InterviewRounds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface InterviewRoundsRepository extends JpaRepository<InterviewRounds, UUID> {


    @Query("SELECT interview FROM InterviewRounds interview WHERE interview.application = :application")
    List<InterviewRounds> findByApplication(@Param("application") Applications application);
    @Query("select ir from InterviewRounds ir where ir.application=:application and ir.interviewDate = :interviewDate")
    List<InterviewRounds> findByApplicationAndDate(Applications application, LocalDate interviewDate);


}
