package com.jobease.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobease.model.JobOpportunity;
import com.jobease.service.JobOpportunityService;


@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobOpportunityService jobOpportunityService;

    @GetMapping
    public String getAllJobOpportunities() throws IOException{
        List<JobOpportunity> jobOpportunities = jobOpportunityService.scrapeJobs();
        return "job opportunities";
    }
}
