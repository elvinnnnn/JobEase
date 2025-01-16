package com.jobease.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobease.model.Company;
import com.jobease.model.JobOpportunity;
import com.jobease.model.JobSource;
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

    @GetMapping("/dbtest")
    public String dbTest() {
        JobController JC = new JobController();
        
        // Create test data
        Company company1 = new Company(
            1,
            "Tech Solutions",
            "IT Services",
            new HashSet<>(Arrays.asList("innovative", "fast-growing"))
        );

        JobSource source1 = new JobSource(1, "LinkedIn", "2025-01-01");

        JobOpportunity job1 = new JobOpportunity(
            1,
            "2025-01-10",
            "2025-02-10",
            company1.getCompanyID(),
            company1.getCompanyName(),
            source1.getSourceID(),
            "Java Developer",
            "$60,000 - $80,000",
            "Remote",
            true
        );

        JobOpportunity job2 = new JobOpportunity(
            2,
            "2025-01-15",
            "2025-02-20",
            company1.getCompanyID(),
            company1.getCompanyName(),
            source1.getSourceID(),
            "Python Developer",
            "$70,000 - $90,000",
            "New York",
            false
        );

        // Add test data to the database
        //JobOpportunityDAO jobDao = new JobOpportunityDAO();
        //JC.addJobOpportunity(job1); // Insert a single job

        jobOpportunityService.addJobs(Arrays.asList(job1, job2)); // Insert multiple jobs

        // Read job opportunities from database
        List<JobOpportunity> list = jobOpportunityService.getAllJobs();
        for (JobOpportunity j : list) {
            System.out.println(j);
        }
        jobOpportunityService.deleteJob(1);
        jobOpportunityService.deleteJob(2); 
        System.out.println("deleted");
        list = jobOpportunityService.getAllJobs();
        for (JobOpportunity j : list) {
            System.out.println(j);
        }
        return "dbtest";
    }
}