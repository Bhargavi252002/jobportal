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
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Applications {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID applicationId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Jobs jobs;

    private Boolean shortListedCandidate=false;
    private boolean isApplicationDeleted=false;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "application")
    private Set<InterviewRounds> interviewRounds;

}
