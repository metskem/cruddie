package nl.computerhok.cruddie.repositories;

import nl.computerhok.cruddie.entity.Appservergroup;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryLookupStrategy;

import java.util.List;

@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public interface AppservergroupRepository extends PagingAndSortingRepository<Appservergroup, Long> {

    Appservergroup findByName(String name);

    List<Appservergroup> findByStage(@Param("stage") Appservergroup.Stage stage);
    List<Appservergroup> findByNameLike(@Param("name") String name);
}