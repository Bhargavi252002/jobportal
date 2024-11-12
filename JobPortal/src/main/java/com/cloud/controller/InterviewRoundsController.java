package com.cloud.controller;
import com.cloud.DTO.InterviewRoundsDTO;
import com.cloud.service.serviceImplementation.InterviewRoundsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/interview")
public class InterviewRoundsController {
    @Autowired
    private InterviewRoundsServiceImplementation interviewRoundsServiceImplementation;
    @PostMapping("/conductInterview/{applicationId}")
    public ResponseEntity<String> conductInterview(@PathVariable UUID applicationId, @RequestParam String writtenTest,@RequestParam String technical,@RequestParam String hr) {
        return interviewRoundsServiceImplementation.conductInterview(applicationId,writtenTest,technical,hr);
    }

    @GetMapping("getAllInterviews")
    public ResponseEntity<List<InterviewRoundsDTO>> getAllInterviews(){
        return interviewRoundsServiceImplementation.getAllInterviewApplications();
    }

    @PutMapping("/updateInterview/{applicationId}")
    public ResponseEntity<String> updateInterview(@PathVariable UUID applicationId, @RequestParam String writtenTest,@RequestParam String technical, @RequestParam String hr, @RequestParam (required=false) LocalDate interviewDate){
        return interviewRoundsServiceImplementation.updateInterview(applicationId, writtenTest,technical,hr,interviewDate);

    }
    @DeleteMapping("/deleteInterview")
    public ResponseEntity<String> deleteInterviews(UUID interviewId){
        return interviewRoundsServiceImplementation.softDeleteInterview(interviewId);
    }
}
