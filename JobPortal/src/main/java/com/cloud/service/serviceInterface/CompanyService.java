package com.cloud.service.serviceInterface;
import com.cloud.DTO.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CompanyService {
    ResponseEntity<String> createCompany(CompanyDTO companyDto);
    ResponseEntity<String> softDeleteCompany(UUID companyId);
}
