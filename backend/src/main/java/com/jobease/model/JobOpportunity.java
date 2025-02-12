package com.jobease.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class JobOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String listDate;
    private String companyName;
    private String jobUrl;
    private String jobTitle;
    private String salaryRange;
    private String location;
    private String description;
    private boolean hasRemoteOption;

    public JobOpportunity(String listDate, String companyName, String jobUrl, String jobTitle, String salaryRange, String location, String description, boolean hasRemoteOption) {
        this.listDate = listDate;
        this.companyName = companyName;
        this.jobUrl = jobUrl;
        this.jobTitle = jobTitle;
        this.salaryRange = salaryRange;
        this.location = location;
        this.description = description;
        this.hasRemoteOption = hasRemoteOption;
    }

    public JobOpportunity(String jobTitle, String company, String location, String salaryRange) {
        this.jobTitle = jobTitle;
        this.companyName = company;
        this.location = location;
        this.salaryRange = salaryRange;
    }

    public JobOpportunity() {
    }

    public Long getId () {
        return this.id;
    }

    public void setListDate(String date) {
        this.listDate = date;
    }
    public String getListDate() {
        return this.listDate;
    }

    public void setCompanyName(String company) {
        this.companyName = company;
    }
    public String getCompanyName() {
        return this.companyName;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getJobUrl() {
        return this.jobUrl;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getJobTitle() {
        return this.jobTitle;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setHasRemoteOption(boolean isRemote) {
        this.hasRemoteOption = isRemote;
    }
    public boolean getHasRemoteOption() {
        return this.hasRemoteOption;
    }
}