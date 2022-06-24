package coordinatechecker.service;

import coordinatechecker.model.Satellite;
import coordinatechecker.model.SatellitesMove;
import coordinatechecker.repository.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/satellite")
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository satelliteRepository;
    @Autowired
    public SatelliteServiceImpl(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }


    @RequestMapping("/satellites/{year}/{month}/{day}/{hour}")
    @Override
    public SatellitesMove findSatellitesByPeriod(@PathVariable int year, @PathVariable int month, @PathVariable int day, @PathVariable int hour) {
        long startTime = getTime(year, month, day,hour);
        long endTime = getTime(year, month, day,hour+1);

        List<Satellite> satellites = satelliteRepository.findByTimestamp(startTime,endTime);
        SatellitesMove result = new SatellitesMove(satellites);
        return result;
    }


      private long getTime(int year,int month, int day, int hour){
        LocalDateTime dateTime = LocalDateTime.of(year, month, day,hour,0);
        return Timestamp.valueOf(dateTime).getTime();
    }
}
