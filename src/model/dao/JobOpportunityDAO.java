package src.model.dao;

import src.model.JobOpportunity;
import src.model.dao.SQLiteDataSource;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The {@code JobOpportunityDAO} class is a data access object. 
 * This class is used for all database operations relating to the JobOpportunity Table in the database.
 */
public class JobOpportunityDAO {
    private SQLiteDataSource db;

    public JobOpportunityDAO() {
        this.db = new SQLiteDataSource("jdbc:sqlite:jobEaseDB.db");
    }

    public void addJob(JobOpportunity j) {
        try {
            Connection con = db.getConnection();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("""
                    INSERT INTO JobOpportunity (jobID, listDate, closeDate, companyID, 
                                companyName, sourceID, jobDescription, salaryRange, location, 
                                remoteOption) VALUES 
                    """);
            queryBuilder.append(j.toString());
            
            String query = queryBuilder.toString();
            Statement st = con.createStatement();
            st.executeQuery(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJobs(List<JobOpportunity> jobs) {
        try {
            Connection con = db.getConnection();
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
            Statement st = con.createStatement();
            st.executeQuery(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
