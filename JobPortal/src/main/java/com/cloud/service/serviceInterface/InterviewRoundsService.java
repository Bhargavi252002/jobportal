package com.cloud.service.serviceInterface;
import com.cloud.DTO.InterviewRoundsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface InterviewRoundsService {

    ResponseEntity<String> conductInterview(UUID applicationId, String writtenTest, String technical, String hr);
    ResponseEntity<String> softDeleteInterview(UUID interviewId);
    ResponseEntity<List<InterviewRoundsDTO>> getAllInterviewApplications();
}
