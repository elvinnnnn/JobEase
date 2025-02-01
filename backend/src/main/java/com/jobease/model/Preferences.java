package com.jobease.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String[] jobType;
    private String location;
    private String[] experienceLevel;
    private boolean remote;
    private String[] industry;
    private int distance;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Preferences(String[] jobType, String location, String[] experienceLevel, boolean remote, String[] industry, int distance) {
        this.jobType = jobType;
        this.location = location;
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

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getExperienceLevel() {
        return this.experienceLevel;
    }

    public void setExperienceLevel(String[] experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public boolean getRemote() {
        return this.remote;
    }

    public void setRemote(boolean remote) {
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

    public void updatePreferences(String id, Preferences preferences) {

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
        if (this.remote) url.append("remote");

        url.append("-jobs");

        // handle location preference
        switch (this.location) {
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
