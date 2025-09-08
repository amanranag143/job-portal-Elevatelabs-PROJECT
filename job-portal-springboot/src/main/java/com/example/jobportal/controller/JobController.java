package com.example.jobportal.controller;

import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final UserService userService;

    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q,
                       @RequestParam(required = false) String location,
                       Model model) {
        List<Job> jobs = (q != null || location != null) ? jobService.search(q, location) : jobService.listAll();
        model.addAttribute("jobs", jobs);
        model.addAttribute("q", q);
        model.addAttribute("location", location);
        return "jobs/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/create";
    }

    @PostMapping
    public String save(@ModelAttribute Job job,
                       @AuthenticationPrincipal UserDetails principal) {
        User employer = userService.findByEmail(principal.getUsername());
        jobService.create(job, employer);
        return "redirect:/jobs?posted";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Job job = jobService.get(id);
        if (job == null) return "redirect:/jobs";
        model.addAttribute("job", job);
        return "jobs/details";
    }
}
