package com.cloud.service.serviceInterface;
import com.cloud.DTO.ApplicationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ApplicationService {
    ResponseEntity<String> applyForJob(String candidateEmail, UUID jobId);
    ResponseEntity<String> shortlistCandidateIfEligible(UUID applicationId);
    ResponseEntity<String> shortlistCandidate( UUID applicationId);
    ResponseEntity<List<ApplicationDTO>> getAllApplications();

}
