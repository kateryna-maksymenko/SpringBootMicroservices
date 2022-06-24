package coordinatechecker.service;

import coordinatechecker.model.Satellite;
import coordinatechecker.model.SatellitesMove;
import coordinatechecker.repository.SatelliteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SatelliteServiceTest {


    private SatelliteService service;

    private SatelliteRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(SatelliteRepository.class);
        service = new SatelliteServiceImpl(repository);
    }

    @Test
    public void shouldFindSatellitesByPeriod() {
        int year = 2020;
        int month = 12;
        int day = 10;
        int hour = 15;
        Long startTime = calculateTime(year, month, day, hour);
        Long endTime = calculateTime(year, month, day, hour+1);

        Satellite satellite = new Satellite(1, "55", "name", 55.01, 60.001, startTime);
        List<Satellite> satellites = Arrays.asList(satellite);

        Mockito.when(repository.findByTimestamp(startTime, endTime)).thenReturn(satellites);
        SatellitesMove result = service.findSatellitesByPeriod(year,month,day, hour);

        assertNotNull(result);
        assertEquals(1, result.getSatellites().size());

    }

    private long calculateTime(int year,int month, int day, int hour){
        LocalDateTime dateTime = LocalDateTime.of(year, month, day,hour,0);
        return Timestamp.valueOf(dateTime).getTime();
    }
}
