package com.example.jobportal.service;

import com.example.jobportal.model.*;
import com.example.jobportal.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public Application apply(User applicant, Job job, String coverLetter) {
        return repo.findByApplicantAndJob(applicant, job).orElseGet(() -> {
            Application a = new Application();
            a.setApplicant(applicant);
            a.setJob(job);
            a.setCoverLetter(coverLetter);
            a.setStatus(ApplicationStatus.SUBMITTED);
            return repo.save(a);
        });
    }

    public List<Application> forApplicant(User applicant) {
        return repo.findByApplicant(applicant);
    }
}
