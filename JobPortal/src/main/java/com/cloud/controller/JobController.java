package com.cloud.controller;
import com.cloud.DTO.JobDTO;
import com.cloud.service.serviceImplementation.JobServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobServiceImplementation jobServiceImplementation;

    @PostMapping("/saveJob")
    public ResponseEntity<String> SaveJobs(@RequestBody JobDTO jobDTO){
        return jobServiceImplementation.createJob(jobDTO);
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<JobDTO>> getJobs(){
        return jobServiceImplementation.getAllJobs();
    }

    @GetMapping("/getAllJobsByRole")
    public ResponseEntity<List<JobDTO>> getAllJObsByRole(String jobRole){
        return jobServiceImplementation.searchJobsByRole(jobRole);
    }
    @DeleteMapping("/deleteJob/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable UUID jobId){
        return jobServiceImplementation.deleteJob(jobId);
    }

}
