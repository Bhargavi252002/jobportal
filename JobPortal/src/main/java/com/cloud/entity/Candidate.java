package com.cloud.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID candidateId;
    private String candidateName;
    private String candidateEmail;
    private String graduation;
    private double percentage;
    private Integer passedOutYear;
    @ElementCollection
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidateId"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();
//    @Column(columnDefinition ="TEXT")
//    private String skill;
    private Boolean isDeleted=false;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Applications> applications;



}
