package com.cloud.service.serviceImplementation;
import com.cloud.DTO.CompanyDTO;
import com.cloud.Exception.JobPortalException;
import com.cloud.entity.Companies;
import com.cloud.repository.CompanyRepository;
import com.cloud.service.serviceInterface.CompanyService;
import com.cloud.util.DTOConvertions;
import com.cloud.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    DTOConvertions dtoConvertions=new DTOConvertions();

    public ResponseEntity<String> createCompany(CompanyDTO companyDto)
    {
      Companies companies=new Companies();
        try {
            if (ObjectUtils.isEmpty(companyDto))
            {
                throw new JobPortalException(ServiceConstants.Object_NULL_ERROR);
            }
            if (StringUtils.isEmpty(companyDto.getCompanyName())) {
                throw new JobPortalException("Company Name Is Required") ;
            }
            Companies existingCompany = companyRepository.findByCompanyName(companyDto.getCompanyName());

            if(!ObjectUtils.isEmpty(existingCompany)) {
                throw new JobPortalException(ServiceConstants.NAME_IS_ALREADY_EXISTED) ;
            }
            companies=dtoConvertions.convert(companyDto);
            companyRepository.save(companies);
            companyDto.setMessage(ServiceConstants.COMPANY_SUCCESSFULLY_ADDED );
            return new ResponseEntity<>(companyDto.getMessage(), HttpStatus.OK);
        }
        catch (JobPortalException j){
            j.printStackTrace();
            return new ResponseEntity<>(j.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            companyDto.setMessage(e.getMessage());
            return new ResponseEntity<>(companyDto.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> softDeleteCompany(UUID companyId) {
       CompanyDTO companyDTO=new CompanyDTO();
        try {
           Companies companies=companyRepository.findById(companyId).orElse(null);
            if(!ObjectUtils.isEmpty(companies)){
                companies.setIsCompanyDeleted(true);
                companyRepository.save(companies);
                companyDTO = dtoConvertions.convert(companies);
                companyDTO.setMessage("company soft Deletion successfull....");
                return new ResponseEntity<>(companyDTO.getMessage(),HttpStatus.OK);
            }
            else {
                throw new JobPortalException("company not found");
            }
        }
        catch (JobPortalException j){
            companyDTO.setMessage(j.getMessage());
            return new ResponseEntity<>(companyDTO.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            companyDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
