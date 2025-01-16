package com.jobease.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.jobease.model.JobOpportunity;

@Service
public class JobOpportunityService {
    private final SQLiteDataSource db;

    public JobOpportunityService() {
        this.db = new SQLiteDataSource("jdbc:sqlite:jobeaseDB.db");
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
            System.out.println(query);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("Success single");
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
            System.out.println(query);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            System.out.println("success multiple");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //test scrape
    public List<JobOpportunity> scrapeJobs() throws IOException {
        JobOpportunity opportunity = new JobOpportunity();

        // example fron jsoup docs, unrelated to the project
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.printf("%s\n\t%s%n", 
                headline.attr("title"), headline.absUrl("href"));
        }
        
        // returns nothing atm
        return List.of(opportunity);
    }
}
