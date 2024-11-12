package com.cloud.service.serviceInterface;
import com.cloud.DTO.JobDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface JobService {

    ResponseEntity<String> createJob(JobDTO jobDTO);
    ResponseEntity<List<JobDTO>> getAllJobs();
//    JobDTO getJob(UUID jobId);
    ResponseEntity<List<JobDTO>>searchJobsByRole(String jobRole);
    ResponseEntity<String> deleteJob(UUID jobId);
}
