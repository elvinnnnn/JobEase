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

        // Handle experience level preference
        boolean hasExpOrRemote = false;
        if (this.experienceLevel != null && this.experienceLevel.length > 0) {
            hasExpOrRemote = true;
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

        // Handle remote preference
        if (this.remote != null && Arrays.asList(this.remote).contains("remote")) {
            hasExpOrRemote = true;
            url.append("-remote");
        }

        if (hasExpOrRemote) {
            url.append("-jobs");
        } else url.append("jobs");

        // Handle location preference
        if (this.locations != null && this.locations.length > 0) {
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
        }

        // Handle industry preference
        boolean hasIndustry = false;
        if (this.industry != null && this.industry.length > 0) {
            for (var area : this.industry) {
                switch (area) {
                    case "IT" -> { url.append("6281%2C"); hasIndustry = true; }
                    case "Engineering" -> { url.append("1209%2C"); hasIndustry = true; }
                    case "Finance" -> { url.append("1203%2C"); hasIndustry = true; }
                    case "Science" -> { url.append("1225%2C"); hasIndustry = true; }
                    case "Consulting" -> { url.append("6076%2C"); hasIndustry = true; }
                    case "Trades" -> { url.append("1223%2C"); hasIndustry = true; }
                    case "Government" -> { url.append("1210%2C"); hasIndustry = true; }
                    default -> {}
                }
            }
            if (hasIndustry) url.append("&");
        }

        // Handle job type preference
        boolean hasJobType = false;
        if (this.jobType != null && this.jobType.length > 0) {
            for (var type : this.jobType) {
                switch (type) {
                    case "Full Time" -> { url.append("242%2C"); hasJobType = true; }
                    case "Part Time" -> { url.append("243%2C"); hasJobType = true; }
                    case "Contract/Temp" -> { url.append("244%2C"); hasJobType = true; }
                    case "Casual/Vacation" -> { url.append("245%2C"); hasJobType = true; }
                    default -> {}
                }
            }
        }

        // Remove trailing "&" or "?" if no parameters were added
        if (!hasIndustry && !hasJobType) {
            int lastCharIndex = url.length() - 1;
            if (url.charAt(lastCharIndex) == '?' || url.charAt(lastCharIndex) == '&') {
                url.deleteCharAt(lastCharIndex);
            }
        }

        System.out.println(url.toString());
        return url.toString();
    }
}
