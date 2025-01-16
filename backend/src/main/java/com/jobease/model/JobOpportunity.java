package com.jobease.model;

public class JobOpportunity {
    private int jobID;
    private String listDate;
    private String closeDate;
    private Company company;
    private JobSource jobSource;
    private String jobDescription;
    private String salaryRange;
    private String location;
    private boolean hasRemoteOption;

    public JobOpportunity(int jobID, String listDate, String closeDate, Company company, JobSource jobSource, String jobDescription, String salaryRange, String location, boolean hasRemoteOption) {
        this.jobID = jobID;
        this.listDate = listDate;
        this.closeDate = closeDate;
        this.company = company;
        this.jobSource = jobSource;
        this.jobDescription = jobDescription;
        this.salaryRange = salaryRange;
        this.location = location;
        this.hasRemoteOption = hasRemoteOption;
    }

    public JobOpportunity() {

    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
    public int getJobID() {
        return this.jobID;
    }

    public void setListDate(String date) {
        this.listDate = date;
    }
    public String getListDate() {
        return this.listDate;
    }

    public void setCloseDate(String date) {
        this.closeDate = date;
    }
    public String getCloseDate() {
        return this.closeDate;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return this.company;
    }

    public void setJobSource(JobSource jobSource) {
        this.jobSource = jobSource;
    }
    public JobSource getJobSource() {
        return this.jobSource;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }
    public String getSalaryRange() {
        return this.salaryRange;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return this.location;
    }

    public void setHasRemoteOption(boolean isRemote) {
        this.hasRemoteOption = isRemote;
    }
    public boolean getHasRemoteOption() {
        return this.hasRemoteOption;
    }
}