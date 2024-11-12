package com.cloud.controller;
import com.cloud.DTO.CandidateDTO;
import com.cloud.service.serviceImplementation.CandidateServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateServiceImplementation candidateServiceImplementation;

    @PostMapping("/saveCandidate")
    public ResponseEntity<String> saveCandidate(@RequestBody CandidateDTO candidateDTO){
        return candidateServiceImplementation.createCandidate(candidateDTO);
    }
    @GetMapping("/getAllCandidates")
    public ResponseEntity<List<CandidateDTO>> getCandidates(){
        return candidateServiceImplementation.getAllCandidates();
    }
    @GetMapping("/getCandidate/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable UUID candidateId){
        return candidateServiceImplementation.getCandidate(candidateId);
    }
    @PatchMapping("/updateEmail/{candidateId}/{newEmail}")
    public ResponseEntity<String> updateMail(@PathVariable UUID candidateId, @PathVariable String newEmail){
        return candidateServiceImplementation.updateEmial(candidateId,newEmail);

    }
    @DeleteMapping("deleteCandidate/{candidateId}")
    public ResponseEntity<String> DeleteCandidate(@PathVariable UUID candidateId) {
        return candidateServiceImplementation.softDeleteCandidate(candidateId);
    }
}
