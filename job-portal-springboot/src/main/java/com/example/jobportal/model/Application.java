package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "applications",
       uniqueConstraints = @UniqueConstraint(columnNames = {"applicant_id","job_id"}))
@Getter @Setter
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

    @Lob
    private String coverLetter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @CreationTimestamp
    private Instant appliedAt;
}
