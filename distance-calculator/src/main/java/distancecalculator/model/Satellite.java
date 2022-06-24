package distancecalculator.model;


public class Satellite {

    private Integer sequence;
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private Long timestamp;

    public Satellite(Integer sequence, String id, String name, double latitude, double longitude, Long timestamp) {
        this.sequence = sequence;
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public Satellite() {
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSequence() {
        return sequence;
    }

     public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}
