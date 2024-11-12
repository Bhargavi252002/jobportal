package com.cloud.repository;
import com.cloud.entity.InterviewRounds;
import com.cloud.entity.SelectedCandidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface SelectedCandidatesRepository  extends JpaRepository<SelectedCandidates, UUID>
{
    SelectedCandidates findByInterviewRounds(InterviewRounds interviewRounds);
}
