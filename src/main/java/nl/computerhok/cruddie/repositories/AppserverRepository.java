package nl.computerhok.cruddie.repositories;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppserverRepository extends PagingAndSortingRepository<Appserver, Long> {

    List<Customer> findByHostname(String hostname);
}