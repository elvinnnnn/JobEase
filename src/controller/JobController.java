package src.controller;
import java.util.ArrayList;
import java.util.List;
import src.model.JobOpportunity;
import src.model.dao.JobOpportunityDAO;
import src.controller.SearchCondition;

public class JobController {
    // CREATE DATABASE OPERATIONS
    private JobOpportunityDAO jobDAO;

    // Constructor
    public void JobController() {
        
    }
    /*
     * Add a job opportunity to database
     */
    public boolean addJobOpportunity(JobOpportunity j) {
        return jobDAO.addJob(j);
    }
    
    /*
     * Add a list of job opportunities to the database
     */
    public boolean addJobOpportunities(List<JobOpportunity> jobs) {
        return jobDAO.addAllJobs(jobs);
    }

    // READ DATABASE OPERATIONS

    /* 
     * Returns all job opportunities in database
     */
    public List<JobOpportunity> getAllJobs() {

        return jobDAO.getAllJobs();
    }

    /*
     * Returns all job opportunities aligned with search condition
     */

    public List<JobOpportunity> getAllJobs(SearchCondition con) {
        return jobDAO.getAllJobs(con);
    }
}
