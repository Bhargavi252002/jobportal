package com.cloud.DTO;
import com.cloud.entity.InterviewRounds;
import com.cloud.util.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectedCandidatesDTO extends ResponseDTO
{
    private UUID selectedCandadiateId;
    private boolean isSelectedCandidate=true;
    private InterviewRounds interviewRounds;
    private UUID jobId;
    private UUID candidateId;
    private String candidateName;
    private String candidateEmail;
    private String jobTitle;

}
