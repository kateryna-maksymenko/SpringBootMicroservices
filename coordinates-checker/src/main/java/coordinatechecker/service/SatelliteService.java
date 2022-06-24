package coordinatechecker.service;

import coordinatechecker.model.SatellitesMove;

public interface SatelliteService {

    SatellitesMove findSatellitesByPeriod(int year, int month, int day, int hour);
}
