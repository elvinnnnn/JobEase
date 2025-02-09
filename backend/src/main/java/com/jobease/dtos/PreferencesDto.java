package com.jobease.dtos;

public class PreferencesDto {
    private String[] jobType;
    private String[] locations;
    private String[] experienceLevel;
    private String[] remote;
    private String[] industry;
    private int distance;

    public PreferencesDto () {
    }

    public PreferencesDto(String[] jobType, String[] locations, String[] experienceLevel, String[] remote, String[] industry, int distance) {
        this.jobType = jobType;
        this.locations = locations;
        this.experienceLevel = experienceLevel;
        this.remote = remote;
        this.industry = industry;
        this.distance = distance;
    }

    public String[] getJobType() {
        return this.jobType;
    }

    public void setJobType(String[] jobType) {
        this.jobType = jobType;
    }

    public String[] getLocation() {
        return this.locations;
    }

    public void setLocation(String[] location) {
        this.locations = location;
    }

    public String[] getExperienceLevel() {
        return this.experienceLevel;
    }

    public void setExperienceLevel(String[] experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String[] getRemote() {
        return this.remote;
    }

    public void setRemote(String[] remote) {
        this.remote = remote;
    }

    public String[] getIndustry() {
        return this.industry;
    }

    public void setIndustry(String[] industry) {
        this.industry = industry;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
