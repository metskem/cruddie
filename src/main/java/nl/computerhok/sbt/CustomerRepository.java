package nl.computerhok.sbt;

import java.util.List;

import nl.computerhok.sbt.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}

