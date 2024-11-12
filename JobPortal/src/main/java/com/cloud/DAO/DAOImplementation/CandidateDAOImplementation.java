package com.cloud.DAO.DAOImplementation;
import com.cloud.DAO.DAOInterface.CandidateDAO;
import com.cloud.DTO.CandidateDTO;
import com.cloud.Exception.JobPortalException;
import com.cloud.entity.Candidate;
import com.cloud.util.DTOConvertions;
import com.cloud.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.EntityManager;
import java.util.UUID;
@Repository
public class CandidateDAOImplementation implements CandidateDAO
{

    @Autowired
    private EntityManager entityManager;
    DTOConvertions dtoConvertions = new DTOConvertions();

    @Override
    @Transactional
    public ResponseEntity<String> updateCandidateEmail(UUID candidateId, String newEmail) {
        CandidateDTO candidateDTO=new CandidateDTO();
        try {
            Candidate candidate = entityManager.find(Candidate.class, candidateId);
            if (!ObjectUtils.isEmpty(candidate)) {
                candidate.setCandidateEmail(newEmail);
                entityManager.merge(candidate);
                candidateDTO = dtoConvertions.convert(candidate);
                candidateDTO.setMessage("CANDIDATE UPDATE SUCCESSFULLY.....");
                return new ResponseEntity<>(candidateDTO.getMessage(), HttpStatus.OK);
            } else {
              throw new JobPortalException(ServiceConstants.CANDIDATES_NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            candidateDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
