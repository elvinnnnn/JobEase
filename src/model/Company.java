package src.model;

import java.util.Set;
import java.util.HashSet;

public class Company {
    private final int companyID;
    private String companyName;
    private String industry;
    private Set<String> tags;

    public Company(int companyID, String companyName, String industry, HashSet<String> tags) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.industry = industry;
        this.tags = tags;
    }

    public int getCompanyID() {
        return this.companyID;
    }

    public void setCompanyName(String name) {
        this.companyName = name;
    }
    public String getCompanyName() {
        return this.companyName;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getIndustry() {
        return this.industry;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }
    public void removeTag(String tag) {
        if (tags.contains(tag)) {
            this.tags.remove(tag);
        }
    } 
    public Set<String> getTags() {
        return this.tags;
    }
}
