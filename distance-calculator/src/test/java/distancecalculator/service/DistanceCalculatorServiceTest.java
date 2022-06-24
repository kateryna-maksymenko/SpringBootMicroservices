package distancecalculator.service;

import distancecalculator.model.CoordinatesDistance;
import distancecalculator.model.Satellite;
import distancecalculator.model.SatellitesMove;
import distancecalculator.service.impl.DistanceCalculatorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorServiceTest {

    private final static String URL = "http://localhost:8081/satellite/satellites/";
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private DistanceCalculatorService service = new DistanceCalculatorServiceImpl();
    @Before
    public void setUp() {
        ReflectionTestUtils.setField(service, "satelliteUrl", URL);
    }

    @Test
    public void getDistancesByDateTimeTest() {
        Satellite satellite = new Satellite(1,"55", "name", 55.01, 60.08, 1609826252L);
        SatellitesMove satellitesMove = new SatellitesMove();
        satellitesMove.setSatellites(Arrays.asList(satellite));
        when(restTemplate.getForObject(URL.concat("2020/12/15/6"), SatellitesMove.class))
          .thenReturn(satellitesMove);

        CoordinatesDistance result = service.getDistancesByDateTime(2020, 12,15,6);

       assertEquals(1, result.getMoves().size());
    }
}
