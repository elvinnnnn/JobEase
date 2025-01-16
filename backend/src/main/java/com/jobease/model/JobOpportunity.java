package com.jobease.model;

public class JobOpportunity {
    private int jobID;
    private String listDate;
    private String closeDate;
    private int companyID;
    private String companyName;
    private int jobSourceID;
    private String jobDescription;
    private String salaryRange;
    private String location;
    private boolean hasRemoteOption;

    public JobOpportunity(int jobID, String listDate, String closeDate, int companyID, String companyName, int jobSourceID, String jobDescription, String salaryRange, String location, boolean hasRemoteOption) {
        this.jobID = jobID;
        this.listDate = listDate;
        this.closeDate = closeDate;
        this.companyID = companyID;
        this.companyName = companyName;
        this.jobSourceID = jobSourceID;
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

    public void setCompanyName(String company) {
        this.companyName = company;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
    public int getCompanyID() {
        return this.companyID;
    }

    public void setJobSourceID(int jobSource) {
        this.jobSourceID = jobSource;
    }
    public int getJobSourceID() {
        return this.jobSourceID;
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

    // Formatting to be added into database or compare with database entry
    @Override
    public String toString() {
        StringBuilder jobString = new StringBuilder();
        jobString.append("(");
        jobString.append(jobID).append(", ");
        jobString.append("'").append(listDate).append("', ");
        jobString.append("'").append(closeDate).append("', ");
        jobString.append("'").append(companyID).append("', ");
        jobString.append("'").append(companyName).append("', ");
        jobString.append("'").append(jobSourceID).append("', ");
        jobString.append("'").append(jobDescription).append("', ");
        jobString.append("'").append(salaryRange).append("', ");
        jobString.append("'").append(location).append("', ");
        jobString.append("'").append(hasRemoteOption).append("')");
        return jobString.toString();
    }
}