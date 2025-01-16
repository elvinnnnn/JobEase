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
    public JobController() {
        this.jobDAO = new JobOpportunityDAO();
    }
    /*
     * Add a job opportunity to database
     */
    public void addJobOpportunity(JobOpportunity j) {
        jobDAO.addJob(j);
    }
    
    /*
     * Add a list of job opportunities to the database
     */
    public void addJobOpportunities(List<JobOpportunity> jobs) {
        jobDAO.addJobs(jobs);
    }

    // READ DATABASE OPERATIONS

    /* 
     * Returns all job opportunities in database
     */
    public List<JobOpportunity> getAllJobOpportunities() {

        return jobDAO.getAllJobs();
    }

    /*
     * Returns all job opportunities aligned with search condition
     */

    /*public List<JobOpportunity> getAllJobs(SearchCondition con) {
        return jobDAO.getAllJobs(con);
    }*/

    // DELETE DATABASE OPERATIONS

    public void deleteJobOpportunity(int jobID) {
        jobDAO.deleteJob(jobID);
    }
    public void deleteJobOpportunities(List<Integer> jobIDs) {
        jobDAO.deleteJobs(jobIDs);
    }
    
}
