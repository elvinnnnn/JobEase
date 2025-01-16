package src.model;

public class JobSource {
    private final int sourceID;
    private String sourceName;
    private String dateAdded;

    public JobSource(int sourceID, String sourceName, String dateAdded) {
        this.sourceID = sourceID;
        this.sourceName = sourceName;
        this.dateAdded = dateAdded;
    }

    public int getSourceID() {
        return this.sourceID;
    }

    public void setSourceName(String name) {
        this.sourceName = name;
    }
    public String getSourceName(String name) {
        return this.sourceName;
    }

    public void setDateAdded(String date) {
        this.dateAdded = date;
    }
    public String getDateAdded() {
        return this.dateAdded;
    }
}
