package com.jobease.model;
import java.util.Arrays;

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

    public void updatePreferences(PreferencesDto preferences) {
        this.jobType = preferences.getJobType();
        this.locations = preferences.getLocation();
        this.experienceLevel = preferences.getExperienceLevel();
        this.remote = preferences.getRemote();
        this.industry = preferences.getIndustry();
        this.distance = preferences.getDistance();
    }

    public String seekUrl() {
        var url = new StringBuilder("https://www.seek.com.au/");

        // Seek does not have options to filter for experience level, remote, and distance.
        // Distance will be ignored for location preference, 
        // and experience level + remote will be concaternated into the search query.

        // handle experience level preference
        if (this.experienceLevel != null) {
            for (var level : this.experienceLevel) {
                switch (level) {
                    case "Entry" -> url.append("-entry-level");
                    case "Associate" -> url.append("-associate");
                    case "Mid-Senior" -> url.append("-mid-senior");
                    case "Director" -> url.append("-director");
                    case "Executive" -> url.append("-executive");
                    default -> {}
                }
            }
        }

        // handle remote preference
        if (Arrays.asList(this.remote).contains("remote")) url.append("remote");

        url.append("-jobs");

        // handle location preference
        // Seek only allows for one location, so first location in the list will be used.
        switch (this.locations[0]) {
            case "Sydney" -> url.append("/in-All-Sydney-NSW");
            case "Melbourne" -> url.append("/in-All-Melbourne-VIC");
            case "Brisbane" -> url.append("/in-All-Brisbane-QLD");
            case "Perth" -> url.append("/in-All-Perth-WA");
            case "Adelaide" -> url.append("/in-All-Adelaide-SA");
            case "Canberra" -> url.append("/in-All-Canberra-ACT");
            case "Hobart" -> url.append("/in-All-Hobart-TAS");
            case "Darwin" -> url.append("/in-All-Darwin-NT");
            default -> {}
        }

        url.append("?");

        // handle industry preference
        // due to the amount of seek classifications, only 
        // IT, Engineering, Finance, Science, Consulting, Trades, and Government will be considered.
        if (this.industry != null) {
            for (var area : this.industry) {
                switch (area) {
                    case "IT" -> url.append("6281%2C");
                    case "Engineering" -> url.append("1209%2C");
                    case "Finance" -> url.append("1203%2C");
                    case "Science" -> url.append("1225%2C");
                    case "Consulting" -> url.append("6076%2C");
                    case "Trades" -> url.append("1223%2C");
                    case "Government" -> url.append("1210%2C");
                    default -> {}
                }
            }
            url.append("&");
        }

        // handle job type preference
        if (this.jobType != null) {
            for (var type : this.jobType) {
                switch (type) {
                    case "Full Time" -> url.append("242%2C");
                    case "Part Time" -> url.append("243%2C");
                    case "Contract/Temp" -> url.append("244%2C");
                    case "Casual/Vacation" -> url.append("245%2C");
                    default -> {}
                }
            }
        }
        System.out.println(url.toString());
        return url.toString();
    }
}
