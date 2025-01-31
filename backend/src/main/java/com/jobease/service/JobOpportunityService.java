package com.jobease.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.jobease.model.JobOpportunity;
import com.jobease.model.Preferences;

@Service
public class JobOpportunityService {
    private final SQLiteDataSource db;
    private final Preferences preferences;

    public JobOpportunityService(Preferences preferences) {
        this.db = new SQLiteDataSource("jdbc:sqlite:jobeaseDB.db");
        this.preferences = preferences;
    }

    public List<JobOpportunity> seekScrape() throws IOException {
        var seekUrl = this.preferences.seekUrl();
        Document doc = Jsoup.connect(seekUrl).get();
        Elements jobTitles = doc.select("a[data-automation=jobTitle]");
        Elements jobCompanies = doc.select("a[data-automation=jobCompany]");
        Elements jobLocations = doc.select("a[data-automation=jobLocation]");
        Elements jobSalaries = doc.select("span[data-automation=jobSalary]");
        for (int i = 0; i < jobTitles.size(); i++) {
            System.out.println(jobTitles.get(i).text());
            System.out.println(jobCompanies.get(i).text());
            System.out.println(jobLocations.get(i).text());
            System.out.println(jobSalaries.get(i).text());
        }
        return null;
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

    public List<JobOpportunity> getAllJobs() {
        List<JobOpportunity> jobList = new ArrayList<>();
        String query = "SELECT * FROM JobOpportunity";

        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                JobOpportunity j = new JobOpportunity(
                    rs.getInt("jobID"), 
                    rs.getString("listDate"), 
                    rs.getString("closeDate"), 
                    rs.getInt("companyID"), 
                    rs.getString("companyName"), 
                    rs.getInt("sourceID"), 
                    rs.getString("jobDescription"), 
                    rs.getString("salaryRange"), 
                    rs.getString("location"),
                    rs.getBoolean("remoteOption")
                );
                jobList.add(j);
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    public void deleteJob(int id) {
        String query = "DELETE FROM JobOpportunity where jobID=" + id;

        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(query);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteJobs(List<Integer> ids) {
        String query = "DELETE FROM JobOpportunity where jobID=";

        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            for (int id : ids) {
                st.executeUpdate(query + id);
            }

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
