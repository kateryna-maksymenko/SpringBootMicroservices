package distancecalculator.service.impl;

import distancecalculator.model.CoordinatesDistance;
import distancecalculator.model.Move;
import distancecalculator.model.SatellitesMove;
import distancecalculator.service.DistanceCalculatorService;
import distancecalculator.util.DistanceCalculatorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iss")
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

    private static final double NGTI_LATITUDE = 51.925201;
    private static final double NGTI_LONGITUDE = 4.473634;
    public static final String SLASH = "/";
    @Value("${satellite.url}")
    private String satelliteUrl;
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/distances/{year}/{month}/{day}/{hour}")
    @Override
    public CoordinatesDistance getDistancesByDateTime(@PathVariable int year,@PathVariable int month,@PathVariable int day,@PathVariable int hour) {
        String satelliteFilledUrl = createUrl(year, month, day, hour);
        SatellitesMove satellitesMove = restTemplate.getForObject(satelliteFilledUrl, SatellitesMove.class);

        CoordinatesDistance coordinatesDistance = new CoordinatesDistance();
        if(!CollectionUtils.isEmpty(satellitesMove.getSatellites())) {
            List<Move> moves = satellitesMove.getSatellites().stream()
                    .map(item -> new Move(item.getTimestamp(), DistanceCalculatorHelper.calculateDistance(item.getLatitude(), item.getLongitude(), NGTI_LATITUDE, NGTI_LONGITUDE))).collect(Collectors.toList());
            coordinatesDistance.setMoves(moves);
        }
        return coordinatesDistance;
    }


    private String createUrl(int year, int month, int day, int hour) {
        StringBuilder stringBuilder = new StringBuilder(satelliteUrl);
        stringBuilder.append(year).append(SLASH);
        stringBuilder.append(month).append(SLASH);
        stringBuilder.append(day).append(SLASH);
        stringBuilder.append(hour);
        return stringBuilder.toString();
    }
}
