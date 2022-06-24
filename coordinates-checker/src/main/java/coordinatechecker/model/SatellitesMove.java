package coordinatechecker.model;

import java.util.List;

public final class SatellitesMove {

    private final List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public SatellitesMove(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}
