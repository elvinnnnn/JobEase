package src.model;

/**
 * The {@code JobOpportunity} class represents job listings. This is a plain-java object.
 * Use JobOpportunityDAO for data access operations.
 */
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

    public JobOpportunity() {
    }

    public JobOpportunity(JobOpportunity copy) {

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
