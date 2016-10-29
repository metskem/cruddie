package nl.computerhok.cruddie.repositories;

import nl.computerhok.cruddie.entity.Appservergroup;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppservergroupRepository extends PagingAndSortingRepository<Appservergroup, Long> {

    List<Appservergroup> findByName(String name);
    List<Appservergroup> findByStage(Appservergroup.Stage stage);
}