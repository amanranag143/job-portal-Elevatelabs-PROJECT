package com.example.jobportal.controller;

import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
import com.example.jobportal.service.ApplicationService;
import com.example.jobportal.service.JobService;
import com.example.jobportal.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService, JobService jobService, UserService userService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping("/apply/{jobId}")
    public String apply(@PathVariable Long jobId,
                        @RequestParam(required = false) String coverLetter,
                        @AuthenticationPrincipal UserDetails principal) {
        Job job = jobService.get(jobId);
        if (job == null) return "redirect:/jobs";
        User applicant = userService.findByEmail(principal.getUsername());
        applicationService.apply(applicant, job, coverLetter);
        return "redirect:/applications/my";
    }

    @GetMapping("/my")
    public String myApplications(@AuthenticationPrincipal UserDetails principal, Model model) {
        User me = userService.findByEmail(principal.getUsername());
        model.addAttribute("applications", applicationService.forApplicant(me));
        return "applications/my";
    }
}
