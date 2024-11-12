package com.cloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InterviewRounds {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID interviewId;
    private String writtenTest;
    private String Technical;
    private String Hr;
    private boolean isInterviewDeleted=false;
    private LocalDate interviewDate;


    @ManyToOne(cascade = CascadeType.ALL)
    private Applications application;

    @OneToOne(cascade = CascadeType.ALL )
    private SelectedCandidates selectedCandidates;

}
