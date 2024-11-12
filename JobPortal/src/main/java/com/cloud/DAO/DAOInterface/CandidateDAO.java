package com.cloud.DAO.DAOInterface;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CandidateDAO {

    ResponseEntity<String> updateCandidateEmail(UUID candidateId, String newEmail);
}
