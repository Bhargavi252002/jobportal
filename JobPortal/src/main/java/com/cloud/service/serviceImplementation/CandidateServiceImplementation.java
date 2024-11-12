package com.cloud.service.serviceImplementation;
import com.cloud.DAO.DAOImplementation.CandidateDAOImplementation;
import com.cloud.DTO.CandidateDTO;
import com.cloud.Exception.JobPortalException;
import com.cloud.entity.Candidate;
import com.cloud.repository.CandidateRepository;
import com.cloud.service.serviceInterface.CandidateService;
import com.cloud.util.DTOConvertions;
import com.cloud.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImplementation implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateDAOImplementation candidateDAOImplementation;

    DTOConvertions dtoConvertions = new DTOConvertions();

    @Override
    public ResponseEntity<String> createCandidate(CandidateDTO candidateDto) {
        try {
            if (!ObjectUtils.isEmpty(candidateDto)) {
                if (StringUtils.isEmpty(candidateDto.getCandidateName())) {
                    throw new JobPortalException(ServiceConstants.CANDIDATE_NAME_REQUIRED);
                }
                if (StringUtils.isEmpty(candidateDto.getCandidateEmail())) {
                    throw new JobPortalException(ServiceConstants.CANDIDATE_EMAIL_REQUIRED);
                }
                if (StringUtils.isEmpty(candidateDto.getGraduation())) {
                    throw new JobPortalException(ServiceConstants.CANDIDATE_GRADUATION_REQUIRED);
                }
                Candidate candidateEmail = candidateRepository.findByCandidateEmail(candidateDto.getCandidateEmail());
                if (ObjectUtils.isEmpty(candidateEmail)) {
                    candidateDto.setMessage("CANDIDATE HAS ALREADY THERE.");
                    return new ResponseEntity<>(candidateDto.getMessage(), HttpStatus.BAD_REQUEST);
                }
                Candidate candidate = dtoConvertions.convert(candidateDto);
                candidateRepository.save(candidate);
                candidateDto.setMessage(ServiceConstants.SAVED_SUCCESSFULLY);
                return new ResponseEntity<>(candidateDto.getMessage(), HttpStatus.CREATED);
            } else {
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }
        }
        catch (JobPortalException j){

            return new ResponseEntity<>(j.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {

            candidateDto.setMessage(e.getMessage());
            return new ResponseEntity<>(candidateDto.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<CandidateDTO>>  getAllCandidates() {
        List<CandidateDTO> candidateDTOs = null;
        try {
            List<Candidate> candidates = candidateRepository.findAll();
            if(!CollectionUtils.isEmpty(candidates)) {
                candidateDTOs = candidates.stream()
                        .map(dtoConvertions::convert)
                        .collect(Collectors.toList());
                candidateDTOs.get(0).setMessage("CANDIDATES FOUND SUCCESSFULLY");
                return new ResponseEntity<>(candidateDTOs, HttpStatus.OK);
            }
            else {
                throw new JobPortalException(ServiceConstants.CANDIDATES_NOT_FOUND);
            }
        }
        catch (JobPortalException j){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            candidateDTOs.get(0).setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CandidateDTO> getCandidate(UUID candidateId) {
       CandidateDTO candidateDTO=new CandidateDTO();
        try {
           Candidate candidate=candidateRepository.findById(candidateId).orElse(null);
            if(!ObjectUtils.isEmpty(candidate)) {
                candidateDTO = dtoConvertions.convert(candidate);
                candidateDTO.setMessage("CANDIDATE FOUND SUCCESSFULLY");
                return new ResponseEntity<>(candidateDTO, HttpStatus.OK);
            }
            else {
                throw new JobPortalException(ServiceConstants.CANDIDATES_NOT_FOUND);
            }
        }
        catch (JobPortalException j){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            candidateDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> updateEmial(UUID candidateId, String newMail) {
       return candidateDAOImplementation.updateCandidateEmail(candidateId,newMail);
    }

    public ResponseEntity<String> softDeleteCandidate(UUID candidateId) {
        CandidateDTO candidateDTO = new CandidateDTO();

        try {
            Candidate candidate=candidateRepository.findById(candidateId).orElse(null);
            if(!ObjectUtils.isEmpty(candidate)){
                candidate.setIsDeleted(true);
                candidateRepository.save(candidate);
                candidateDTO = dtoConvertions.convert(candidate);
                candidateDTO.setMessage("Candidate soft Deletion successfull....");
                return new ResponseEntity<>(candidateDTO.getMessage(),HttpStatus.OK);
            }
            else {
              throw new JobPortalException(ServiceConstants.CANDIDATES_NOT_FOUND);
            }
        }
        catch (JobPortalException j){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
            candidateDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}




