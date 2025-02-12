package com.jobease.model;
import com.jobease.dtos.PreferencesDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String[] jobType;
    private String[] locations;
    private String[] experienceLevel;
    private String[] remote;
    private String[] industry;
    private int distance;

    public Preferences () {
        this.jobType = new String[]{};
        this.locations = new String[]{};
        this.experienceLevel = new String[]{};
        this.remote = new String[]{};
        this.industry = new String[]{};
        this.distance = 20;
    }

    public Preferences(String[] jobType, String[] locations, String[] experienceLevel, String[] remote, String[] industry, int distance) {
        this.jobType = jobType;
        this.locations = locations;
        this.experienceLevel = experienceLevel;
        this.remote = remote;
        this.industry = industry;
        this.distance = distance;
    }

    public Long getId () {
        return this.id;
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

    /**
     * Update the user's preferences with the new preferences
     * @param preferences
     */
    public void updatePreferences(PreferencesDto preferences) {
        this.jobType = preferences.getJobType();
        this.locations = preferences.getLocation();
        this.experienceLevel = preferences.getExperienceLevel();
        this.remote = preferences.getRemote();
        this.industry = preferences.getIndustry();
        this.distance = preferences.getDistance();
    }
}
