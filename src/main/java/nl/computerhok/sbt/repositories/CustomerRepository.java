package nl.computerhok.sbt.repositories;

import java.util.List;

import nl.computerhok.sbt.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}