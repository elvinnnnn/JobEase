package com.jobease.model;
import java.util.List;

public class JobOpportunity {
    private Long id;
    private String listDate;
    private String closeDate;
    private String companyName;
    private String jobDescription;
    private List<Integer> salaryRange;
    private String location;
    private boolean isRemote;

    public JobOpportunity() {
    }

    public JobOpportunity(String listDate, String closeDate, String companyName, String jobDescription, List<Integer> salaryRange, String location, boolean isRemote) {
        this.listDate = listDate;
        this.closeDate = closeDate;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.salaryRange = salaryRange;
        this.location = location;
        this.isRemote = isRemote;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<Integer> getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(List<Integer> salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean remote) {
        isRemote = remote;
    }
}