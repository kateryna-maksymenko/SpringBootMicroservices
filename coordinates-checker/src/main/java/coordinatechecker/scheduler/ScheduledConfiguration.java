package coordinatechecker.scheduler;

import coordinatechecker.model.Satellite;
import coordinatechecker.repository.SatelliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class ScheduledConfiguration {

    private static final String ISS_STATION_ID = "25544";
    @Value("${satellite.api}")
    private String satelliteURL;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private SatelliteRepository satelliteRepository;


    @Scheduled(cron ="${cron.expression}")
    public void getISSCoordinates() {
        Satellite satellite =  webClientBuilder.build().get().uri(satelliteURL.concat(ISS_STATION_ID)).retrieve().bodyToMono(Satellite.class).block();
        satellite.setTimestamp(satellite.getTimestamp()*1000);
        satelliteRepository.save(satellite);
    }
}
