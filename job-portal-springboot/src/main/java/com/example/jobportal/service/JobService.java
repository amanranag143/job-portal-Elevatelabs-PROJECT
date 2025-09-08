package com.example.jobportal.service;

import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.JobRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository repo;

    public JobService(JobRepository repo) {
        this.repo = repo;
    }

    public Job create(Job job, User employer) {
        job.setEmployer(employer);
        return repo.save(job);
    }

    public List<Job> listAll() {
        return repo.findAll();
    }

    public List<Job> search(String q, String location) {
        Specification<Job> spec = Specification.where(null);
        if (q != null && !q.isBlank()) {
            spec = spec.and((root, cq, cb) -> cb.or(
                    cb.like(cb.lower(root.get("title")), "%" + q.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("description")), "%" + q.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("company")), "%" + q.toLowerCase() + "%")
            ));
        }
        if (location != null && !location.isBlank()) {
            spec = spec.and((root, cq, cb) -> cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%"));
        }
        return repo.findAll(spec);
    }

    public Job get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
