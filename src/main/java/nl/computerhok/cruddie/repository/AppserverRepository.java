package nl.computerhok.cruddie.repository;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.entity.Appservergroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppserverRepository extends PagingAndSortingRepository<Appserver, Long> {

    Appserver findByHostname(@Param("hostname") String hostname);

    List<Appserver> findByAppservergroupStage(@Param("stage")Appservergroup.Stage stage);
    List<Appserver> findByAppservergroupName(@Param("name") String name);

}