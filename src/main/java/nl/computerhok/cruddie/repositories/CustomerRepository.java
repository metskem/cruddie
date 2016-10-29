package nl.computerhok.cruddie.repositories;

import java.util.List;

import nl.computerhok.cruddie.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}