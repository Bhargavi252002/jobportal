package com.cloud.DTO;
import com.cloud.entity.Applications;
import com.cloud.util.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class InterviewRoundsDTO extends ResponseDTO {

    private UUID interviewId;
    private String writtenTest;
    private String Technical;
    private String Hr;
    private boolean isInterviewDeleted=false;
    private LocalDate interviewDate;
    private Applications application;
}
