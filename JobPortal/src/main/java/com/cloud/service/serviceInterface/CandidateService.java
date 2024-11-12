package com.cloud.service.serviceInterface;
import com.cloud.DTO.CandidateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface CandidateService {
    ResponseEntity<String> createCandidate(CandidateDTO candidateDTO);
    ResponseEntity<List<CandidateDTO>> getAllCandidates();
    ResponseEntity<CandidateDTO> getCandidate(UUID candidateId);
   ResponseEntity<String> softDeleteCandidate(UUID candidateId);
   ResponseEntity<String> updateEmial(UUID candidateId,String newMail);

}
