package src;

import src.model.*;
import src.model.dao.*;

import java.util.Arrays;
import java.util.HashSet;

public class test {
    public static void main(String[] args) {
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
            company1,
            source1,
            "Java Developer",
            "$60,000 - $80,000",
            "Remote",
            true
        );
        job1.setJobID(101);

        JobOpportunity job2 = new JobOpportunity(
            2,
            "2025-01-15",
            "2025-02-20",
            company1,
            source1,
            "Python Developer",
            "$70,000 - $90,000",
            "New York",
            false
        );
        job2.setJobID(102);

        // Add test data to the database
        JobOpportunityDAO jobDao = new JobOpportunityDAO();
        jobDao.addJob(job1); // Insert a single job
        jobDao.addJobs(Arrays.asList(job1, job2)); // Insert multiple jobs
    }
}
