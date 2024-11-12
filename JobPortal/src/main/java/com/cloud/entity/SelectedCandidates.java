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
import javax.persistence.OneToOne;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SelectedCandidates
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID  selectedId;

    @OneToOne(cascade = CascadeType.ALL)
    private InterviewRounds interviewRounds;


}

