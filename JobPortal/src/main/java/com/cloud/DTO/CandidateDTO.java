package com.cloud.DTO;
import com.cloud.util.ResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDTO extends ResponseDTO {
        private UUID candidateId;
        private String candidateName;
        private String candidateEmail;
        private String graduation;
        private double percentage;
        private Integer passedOutYear;
        private Set<String> skills = new HashSet<>();
        private Boolean isDeleted=false;
//        private List<Applications> applications;
}
