package com.cloud.service.serviceInterface;
import com.cloud.DTO.SelectedCandidatesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SelectedCandidatesService {
    ResponseEntity<String> selectedCandidate(UUID interviewId);
    ResponseEntity<SelectedCandidatesDTO> getCandidateWithJobDetails(UUID selectedid);
    ResponseEntity<List<SelectedCandidatesDTO>> getAllSelectedCandidates();
}