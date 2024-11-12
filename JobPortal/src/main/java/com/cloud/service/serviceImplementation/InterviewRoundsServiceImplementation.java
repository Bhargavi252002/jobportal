package com.cloud.service.serviceImplementation;
import com.cloud.DTO.InterviewRoundsDTO;
import com.cloud.Exception.JobPortalException;
import com.cloud.entity.Applications;
import com.cloud.entity.InterviewRounds;
import com.cloud.repository.ApplicationRepository;
import com.cloud.repository.InterviewRoundsRepository;
import com.cloud.service.serviceInterface.InterviewRoundsService;
import com.cloud.util.DTOConvertions;
import com.cloud.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterviewRoundsServiceImplementation implements InterviewRoundsService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InterviewRoundsRepository interviewRoundsRepository;

    DTOConvertions dtoConvertions=new DTOConvertions();


    @Transactional
    public ResponseEntity<String> conductInterview(UUID applicationId, String writtenTest,String technical,String hr) {
         String pass="pass";
         String fail="fail";
        try {
            if (!((pass.equalsIgnoreCase(writtenTest) || fail.equalsIgnoreCase(writtenTest)) &&
                    (pass.equalsIgnoreCase(technical) || fail.equalsIgnoreCase(technical)) &&
                    (pass.equalsIgnoreCase(hr) || fail.equalsIgnoreCase(hr)))) {
                return new ResponseEntity<>("Each interview result should be either 'pass' or 'fail'.", HttpStatus.BAD_REQUEST);
            }
            Applications application = applicationRepository.findById(applicationId).orElse(null);
            if (ObjectUtils.isEmpty(application)) {
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }
            if (!application.getShortListedCandidate()) {
                return new ResponseEntity<>("Candidate is not shortlisted,Cannot conduct interview.", HttpStatus.BAD_REQUEST);
            }
            List<InterviewRounds> existingInterviewRound = interviewRoundsRepository.findByApplication(application);
            if (!CollectionUtils.isEmpty(existingInterviewRound)) {
                return new ResponseEntity<>("Interview has already been conducted for this application.", HttpStatus.BAD_REQUEST);
            }
            InterviewRounds interviewRounds = new InterviewRounds();
            interviewRounds.setWrittenTest(writtenTest);
            interviewRounds.setTechnical(technical);
            interviewRounds.setHr(hr);
            interviewRounds.setApplication(application);
            interviewRoundsRepository.save(interviewRounds);
            return new ResponseEntity<>("Interview conducted successfully for the shortlisted candidate.", HttpStatus.OK);
        } catch (JobPortalException j) {
            return new ResponseEntity<>(j.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<InterviewRoundsDTO>> getAllInterviewApplications(){
        List<InterviewRoundsDTO> interviewRoundsDTO=null;
        try{
           List<InterviewRounds> interviewRounds=interviewRoundsRepository.findAll();
            if(!CollectionUtils.isEmpty(interviewRounds)) {
                interviewRoundsDTO = interviewRounds.stream()
                        .map(dtoConvertions::convert)
                        .collect(Collectors.toList());
                interviewRoundsDTO.get(0).setMessage("Interview Applications Found successfully");
                return new ResponseEntity<>(interviewRoundsDTO, HttpStatus.OK);
            }
            else {
                throw new JobPortalException("Interview Applications Not Found");
            }

        }
        catch (JobPortalException j) {
            interviewRoundsDTO.get(0).setMessage(j.getMessage());
            return new ResponseEntity<>(interviewRoundsDTO, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            interviewRoundsDTO.get(0).setMessage(e.getMessage());
            return new ResponseEntity<>(interviewRoundsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> updateInterview(UUID applicationId, String writtenTest, String technical, String hr, LocalDate interviewDate) {
        String pass="pass";
        String fail="fail";
        try {

            if (!((pass.equalsIgnoreCase(writtenTest) || fail.equalsIgnoreCase(writtenTest)) &&
                    (pass.equalsIgnoreCase(technical) || fail.equalsIgnoreCase(technical)) &&
                    (pass.equalsIgnoreCase(hr) || fail.equalsIgnoreCase(hr)))) {
                return new ResponseEntity<>("Each interview result should be either 'pass' or 'fail'.", HttpStatus.BAD_REQUEST);
            }

            Applications application = applicationRepository.findById(applicationId).orElse(null);
            if (ObjectUtils.isEmpty(application)) {
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR ) ;
            }

            if (!application.getShortListedCandidate()) {
                return new ResponseEntity<>("Candidate is not shortlisted. Cannot update interview", HttpStatus.BAD_REQUEST);
            }

            List<InterviewRounds> existingInterviews = interviewRoundsRepository.findByApplication(application);
            if (existingInterviews == null || existingInterviews.isEmpty()) {
                return new ResponseEntity<>("No interview found for this candidate to update", HttpStatus.BAD_REQUEST);
            }
            InterviewRounds interviewRounds = existingInterviews.get(0);
            interviewRounds.setWrittenTest(writtenTest);
            interviewRounds.setTechnical(technical);
            interviewRounds.setHr(hr);

            if (interviewDate != null) {
                interviewRounds.setInterviewDate(interviewDate);

            }
            interviewRoundsRepository.save(interviewRounds);
            return new ResponseEntity<>("Interview updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    public ResponseEntity<String> softDeleteInterview(UUID interviewId) {
       InterviewRoundsDTO interviewRoundsDTO=new InterviewRoundsDTO();
        try {
            InterviewRounds interviewRounds=interviewRoundsRepository.findById(interviewId).orElse(null);
            if(!ObjectUtils.isEmpty(interviewRounds)){
                interviewRounds.setInterviewDeleted(true);
                interviewRoundsRepository.save(interviewRounds);
                interviewRoundsDTO = dtoConvertions.convert(interviewRounds);
                interviewRoundsDTO.setMessage("Interview soft Deletion successfull....");
                return new ResponseEntity<>(interviewRoundsDTO.getMessage(),HttpStatus.OK);
            }
            else {
                throw new JobPortalException("Interview rounds not found");
            }
        }
        catch (JobPortalException j){
            interviewRoundsDTO.setMessage(j.getMessage());
            return new ResponseEntity<>(interviewRoundsDTO.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            interviewRoundsDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
