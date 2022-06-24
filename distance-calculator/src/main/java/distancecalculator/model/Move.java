package distancecalculator.model;

public final class Move {

    private final long timestamp;

    private final double distance;

    public Move(long timestamp, double distance) {
        this.timestamp = timestamp;
        this.distance = distance;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getDistance() {
        return distance;
    }

}
