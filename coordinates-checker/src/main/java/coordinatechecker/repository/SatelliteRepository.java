package coordinatechecker.repository;

import coordinatechecker.model.Satellite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends CrudRepository<Satellite, String> {

    @Query(value="select s from Satellite s where (s.timestamp >= :startTime and s.timestamp < :endTime)")
   List<Satellite> findByTimestamp(long startTime, long endTime);
}
