package com.cloud.util;
import com.cloud.DTO.ApplicationDTO;
import com.cloud.DTO.CandidateDTO;
import com.cloud.DTO.CompanyDTO;
import com.cloud.DTO.InterviewRoundsDTO;
import com.cloud.DTO.JobDTO;
import com.cloud.DTO.SelectedCandidatesDTO;
import com.cloud.entity.Applications;
import com.cloud.entity.Candidate;
import com.cloud.entity.Companies;
import com.cloud.entity.InterviewRounds;
import com.cloud.entity.Jobs;
import com.cloud.entity.SelectedCandidates;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.BeanUtils;

public class DTOConvertions extends ResponseDTO {

    public CandidateDTO convert(Candidate candidate) {
        CandidateDTO candidateDto = new CandidateDTO();
            if (!ObjectUtils.isEmpty(candidate)) {
                BeanUtils.copyProperties(candidate, candidateDto);
            }
        return candidateDto;
        }
        public Candidate convert(CandidateDTO candidateDTO){
        Candidate candidate=new Candidate();
        if(!ObjectUtils.isEmpty(candidateDTO)){
            BeanUtils.copyProperties(candidateDTO,candidate);
        }
        return candidate;
         }

        public JobDTO convert(Jobs jobs) {
           JobDTO jobDTO=new JobDTO();
                if (!ObjectUtils.isEmpty(jobs)) {
                    BeanUtils.copyProperties(jobs,jobDTO);
                }
            return jobDTO;
        }

        public Jobs convert(JobDTO jobDTO){
            Jobs jobs=new Jobs();
            if(!ObjectUtils.isEmpty(jobDTO)){
                BeanUtils.copyProperties(jobDTO,jobs);
            }
            return jobs;
        }

        public CompanyDTO convert(Companies companies) {
           CompanyDTO companyDTO=new CompanyDTO();
            if (!ObjectUtils.isEmpty(companies)) {
                BeanUtils.copyProperties(companies, companyDTO);
            }
            return companyDTO;
        }
    public Companies convert(CompanyDTO companyDto){
        Companies companies=new Companies() ;
        if(!ObjectUtils.isEmpty(companyDto))
        {
            BeanUtils.copyProperties(companyDto,companies);
        }
        return companies;
    }

    public SelectedCandidatesDTO convert(SelectedCandidates selectedCandidates) {
       SelectedCandidatesDTO selectedCandidatesDTO=new SelectedCandidatesDTO();
        if (!ObjectUtils.isEmpty(selectedCandidates)) {
            BeanUtils.copyProperties(selectedCandidates, selectedCandidatesDTO);
        }
        return selectedCandidatesDTO;
    }
    public SelectedCandidates convert(SelectedCandidatesDTO selectedCandidatesDTO){
       SelectedCandidates selectedCandidates=new SelectedCandidates();
        if(!ObjectUtils.isEmpty(selectedCandidatesDTO))
        {
            BeanUtils.copyProperties(selectedCandidatesDTO,selectedCandidates);
        }
        return selectedCandidates;
    }


    public ApplicationDTO convert(Applications applications) {
        ApplicationDTO applicationDTO=new ApplicationDTO();
        if (!ObjectUtils.isEmpty(applications)) {
            BeanUtils.copyProperties(applications, applicationDTO);
        }
        return applicationDTO;
    }
    public Applications convert(ApplicationDTO applicationDTO){
        Applications applications=new Applications();
        if(!ObjectUtils.isEmpty(applicationDTO))
        {
            BeanUtils.copyProperties(applicationDTO,applications);
        }
        return applications;
    }

    public InterviewRoundsDTO convert(InterviewRounds interviewRounds) {
        InterviewRoundsDTO interviewRoundsDTO=new InterviewRoundsDTO();
        if (!ObjectUtils.isEmpty(interviewRounds)) {
            BeanUtils.copyProperties(interviewRounds, interviewRoundsDTO);
        }
        return interviewRoundsDTO;
    }
    public InterviewRounds convert(InterviewRoundsDTO interviewRoundsDTO){
        InterviewRounds interviewRounds=new InterviewRounds();
        if(!ObjectUtils.isEmpty(interviewRoundsDTO))
        {
            BeanUtils.copyProperties(interviewRoundsDTO,interviewRounds);
        }
        return interviewRounds;
    }



}
