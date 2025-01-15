package src;

import src.model.*;
import src.model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;

public class test {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:jobeaseDB");
        Statement st = conn.createStatement();
        st.executeQuery("""
                INSERT INTO JobOpportunity (jobID, listDate, closeDate, companyID,
                companyName, sourceID, jobDescription, salaryRange, location,
                remoteOption) VALUES
        (1, '2025-01-10', '2025-02-10', '1', 'Tech Solutions', '1', 'Java Developer', '$60,000 - $80,000', 'Remote', 'true')
        ;
                """);
        
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

        // Add test data to the database
        JobOpportunityDAO jobDao = new JobOpportunityDAO();
        //jobDao.addJob(job1); // Insert a single job

        //jobDao.addJobs(Arrays.asList(job1, job2)); // Insert multiple jobs
    }
}
