package com.jobease.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobease.dtos.OpportunityDto;
import com.jobease.service.JobOpportunityService;
import com.jobease.service.JwtService;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobOpportunityService jobOpportunityService;
    private final JwtService jwtService;

    public JobController(JobOpportunityService jobOpportunityService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.jobOpportunityService = jobOpportunityService;
    }

    @PostMapping("/listings")
    public List<OpportunityDto> postListings(@RequestHeader("Authorization") String token) {
        System.out.println("Endpoint hit!");
        String jwt = token.substring(7);
        String email = jwtService.extractUsername(jwt);
        System.out.println(email);
        return jobOpportunityService.scrapeJobs(email);
    };
}