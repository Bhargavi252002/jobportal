package com.cloud.DTO;
import com.cloud.entity.Applications;
import com.cloud.entity.Companies;
import com.cloud.util.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO extends ResponseDTO {
        private UUID jobId;
        private String jobRole;
        private Integer noOfOpenings;
        private double minimumPercentage;
        private Integer requiredPassedOutYear;
        private Set<String> requiredGraduation;
        private Set<String> reqskills;
        private Boolean isJobDeleted;
        private List<Applications> application;
        private Companies companies;
    }
