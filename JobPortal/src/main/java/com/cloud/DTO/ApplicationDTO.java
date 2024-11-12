package com.cloud.DTO;
import com.cloud.entity.Candidate;
import com.cloud.entity.Jobs;
import com.cloud.util.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO extends ResponseDTO {

    private UUID applicationId;
    private Candidate candidate;
    private Jobs jobs;
    private Boolean shortListedCandidate;
    private boolean isApplicationDeleted;
}
