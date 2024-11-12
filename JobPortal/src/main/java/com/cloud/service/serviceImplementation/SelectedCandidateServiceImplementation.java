package com.cloud.service.serviceImplementation;
import com.cloud.DTO.SelectedCandidatesDTO;
import com.cloud.Exception.JobPortalException;
import com.cloud.entity.Candidate;
import com.cloud.entity.InterviewRounds;
import com.cloud.entity.Jobs;
import com.cloud.entity.SelectedCandidates;
import com.cloud.repository.InterviewRoundsRepository;
import com.cloud.repository.JobRepository;
import com.cloud.repository.SelectedCandidatesRepository;
import com.cloud.service.serviceInterface.SelectedCandidatesService;
import com.cloud.util.DTOConvertions;
import com.cloud.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SelectedCandidateServiceImplementation implements SelectedCandidatesService
{
    @Autowired
    private SelectedCandidatesRepository selectedCandidatesRepository ;

    @Autowired
    private InterviewRoundsRepository interviewRoundsRepository;
    @Autowired
    private JobRepository jobRepository;

    DTOConvertions dtoConvertions=new DTOConvertions();

    @Override
    public ResponseEntity<String> selectedCandidate(UUID interviewId) {
        SelectedCandidatesDTO selectedCandidatesDTO=new SelectedCandidatesDTO();
        try{
        InterviewRounds interviewRounds =interviewRoundsRepository.findById(interviewId).orElse(null);
            if(ObjectUtils.isEmpty((interviewRounds))){
                throw  new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }
                 int jobOpenings=interviewRounds.getApplication().getJobs().getNoOfOpenings();
            if (jobOpenings <= 0) {
                return new ResponseEntity<>("No job openings available.", HttpStatus.BAD_REQUEST);
            }
            SelectedCandidates existingSelection = selectedCandidatesRepository.findByInterviewRounds(interviewRounds);
            if (existingSelection != null) {
                return new ResponseEntity<>("Candidate already selected for this interview.", HttpStatus.BAD_REQUEST);
            }
                if(Objects.equals(interviewRounds.getWrittenTest(), "pass")
                && Objects.equals(interviewRounds.getTechnical(), "pass")
                && Objects.equals(interviewRounds.getHr(), "pass")) {

                   selectedCandidatesDTO.setInterviewRounds(interviewRounds);
                    SelectedCandidates selectedCandidates = dtoConvertions.convert(selectedCandidatesDTO);
                    selectedCandidatesRepository.save(selectedCandidates);
                    interviewRounds.getApplication().getJobs().setNoOfOpenings(jobOpenings - 1);
                    jobRepository.save(interviewRounds.getApplication().getJobs());

                    jobOpenings = interviewRounds.getApplication().getJobs().getNoOfOpenings();
                    if (jobOpenings <= 0) {
                        return new ResponseEntity<>("Job openings are filled. Better luck next time.", HttpStatus.OK);
                    }

                    return new ResponseEntity<>("Candidates selected successfully.", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Candidate did not pass all interview rounds.", HttpStatus.BAD_REQUEST);
                }
        }
        catch (JobPortalException j){

            return new ResponseEntity<>(j.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            selectedCandidatesDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(selectedCandidatesDTO.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SelectedCandidatesDTO>> getAllSelectedCandidates(){
        List<SelectedCandidatesDTO> selectedCandidatesDTOS=null;
        try{
            List<SelectedCandidates> selectedCandidates = selectedCandidatesRepository.findAll();
            if (!CollectionUtils.isEmpty(selectedCandidates)) {
                selectedCandidatesDTOS = selectedCandidates.stream()
                        .map(dtoConvertions::convert)
                        .collect(Collectors.toList());
                selectedCandidatesDTOS.get(0).setMessage("JOBS FOUND SUCCESSFULLY");
                return new ResponseEntity<>(selectedCandidatesDTOS, HttpStatus.OK);
            } else {
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }

        }
        catch (JobPortalException j) {
            selectedCandidatesDTOS.get(0).setMessage(j.getMessage());
            return new ResponseEntity<>(selectedCandidatesDTOS, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            selectedCandidatesDTOS.get(0).setMessage(e.getMessage());
            return new ResponseEntity<>(selectedCandidatesDTOS,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<SelectedCandidatesDTO> getCandidateWithJobDetails(UUID selectedid){
        SelectedCandidatesDTO selectedCandidatesDTO=new SelectedCandidatesDTO();
        try{
            SelectedCandidates selectedCandidates=selectedCandidatesRepository.findById(selectedid).orElse(null);
            if(ObjectUtils.isEmpty(selectedCandidates)){
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }
            Candidate candidate=selectedCandidates.getInterviewRounds().getApplication().getCandidate();
            Jobs jobs =selectedCandidates.getInterviewRounds().getApplication().getJobs();

            selectedCandidatesDTO.setSelectedCandadiateId(selectedCandidates.getSelectedId());
            selectedCandidatesDTO.setCandidateName(candidate.getCandidateName());
            selectedCandidatesDTO.setCandidateEmail(candidate.getCandidateEmail());
            selectedCandidatesDTO.setJobTitle(jobs.getJobRole());
            selectedCandidatesDTO.setJobId(jobs.getJobId());
            selectedCandidatesDTO.setCandidateId(candidate.getCandidateId());
            selectedCandidatesDTO.setMessage("You are selected for the Job");

            return new ResponseEntity<>(selectedCandidatesDTO,HttpStatus.OK);
        } catch (JobPortalException j) {

            selectedCandidatesDTO.setMessage(j.getMessage());
           return new ResponseEntity<>(selectedCandidatesDTO,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {

            selectedCandidatesDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(selectedCandidatesDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
