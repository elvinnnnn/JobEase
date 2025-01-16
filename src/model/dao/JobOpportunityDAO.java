package src.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import src.model.JobOpportunity;

/**
 * The {@code JobOpportunityDAO} class is a data access object. 
 * This class is used for all database operations relating to the JobOpportunity Table in the database.
 */
public class JobOpportunityDAO {
    private SQLiteDataSource db;

    public JobOpportunityDAO() {
        this.db = new SQLiteDataSource("jdbc:sqlite:jobeaseDB");
    }

    public void addJob(JobOpportunity j) {
        try {
            try (Connection con = db.getConnection();
                 Statement st = con.createStatement()) {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("""
                        INSERT INTO JobOpportunity (jobID, listDate, closeDate, companyID, 
                                    companyName, sourceID, jobDescription, salaryRange, location, 
                                    remoteOption) VALUES 
                        """);
                queryBuilder.append(j.toString());
                String query = queryBuilder.toString();
                System.out.println(query);
                st.executeQuery(query);
                System.out.println("Success single");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJobs(List<JobOpportunity> jobs) {
        try {
            try (Connection con = db.getConnection();
                 Statement st = con.createStatement()) {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("""
                        INSERT INTO JobOpportunity (jobID, listDate, closeDate, companyID, 
                                    companyName, sourceID, jobDescription, salaryRange, location, 
                                    remoteOption) VALUES 
                        """);
                // Add all jobs to list
                for (JobOpportunity j : jobs) {
                    queryBuilder.append(j.toString());
                    queryBuilder.append(", ");
                }
                // Remove the last space and comma and replace with semicolon
                queryBuilder.setLength(queryBuilder.length() - 2);
                queryBuilder.append(";");

                String query = queryBuilder.toString();
                System.out.println(query);
                st.executeQuery(query);
                System.out.println("success multiple");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
