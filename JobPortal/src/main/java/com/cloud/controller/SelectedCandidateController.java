package com.cloud.controller;
import com.cloud.DTO.SelectedCandidatesDTO;
import com.cloud.service.serviceInterface.SelectedCandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/selected")
public class SelectedCandidateController {

    @Autowired
    private SelectedCandidatesService selectedCandidatesService;


    @PostMapping("/selectCandidate/{interviewId}")
    public ResponseEntity<String> selectCandidate(@PathVariable UUID interviewId) {
        return selectedCandidatesService.selectedCandidate(interviewId);
    }
    @GetMapping("getselectedCandidate/{selectedId}")
    public ResponseEntity<SelectedCandidatesDTO> getSelectedCandidate(@PathVariable UUID selectedId) {
        return selectedCandidatesService.getCandidateWithJobDetails(selectedId);
    }
    @GetMapping("getAllSelectedCandidates")
    public ResponseEntity<List<SelectedCandidatesDTO>> getAllSelectedList(){
        return selectedCandidatesService.getAllSelectedCandidates();
    }
}
