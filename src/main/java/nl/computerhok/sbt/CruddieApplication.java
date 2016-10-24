package nl.computerhok.sbt;

import nl.computerhok.sbt.model.Customer;
import nl.computerhok.sbt.model.Orderr;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class CruddieApplication {
    private static final Logger log = LoggerFactory.getLogger(CruddieApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CruddieApplication.class, args);
    }

    @Bean
    public CommandLineRunner customerDataLoader(CustomerRepository repository) {
        int NUM_CUSTOMERS = 99;
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer", new Date()));
            repository.save(new Customer("Chloe", "O'Brian", new Date()));
            repository.save(new Customer("Kim", "Bauer", new Date()));
            repository.save(new Customer("David", "Palmer", new Date()));
            repository.save(new Customer("Michelle", "Dessler", new Date()));
            repository.save(new Customer("aap", "noot", new Date()));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer cust : repository.findAll()) {
                log.info(cust.toString());
            }

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");

            log.info("saving " + NUM_CUSTOMERS + " random customers");
            for (int i=0;i<NUM_CUSTOMERS;i++) {
                repository.save(new Customer("firstname-" + RandomStringUtils.randomAlphanumeric(10), "lastname-"+RandomStringUtils.randomAlphanumeric(20), new Date()));
            }
            log.info("");
            log.info("");
        };
    }


    @Bean
    public CommandLineRunner orderrDataLoader(OrderrRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Orderr("banana",5,new Date()));
            repository.save(new Orderr("staples",500,new Date()));
            repository.save(new Orderr("coffee",500,new Date()));

            // fetch all orders
            log.info("Orders found with findAll():");
            log.info("-------------------------------");
            for (Orderr order : repository.findAll()) {
                log.info(order.toString());
            }
            log.info("");

            // fetch an individual order by ID
            Orderr order = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(order.toString());
            log.info("");

            // fetch orders by article
            log.info("Orderr found with findByArticle('banana'):");
            log.info("--------------------------------------------");
            for (Orderr banana: repository.findByArticle("banana")) {
                log.info(banana.toString());
            }
            log.info("");
        };
    }

}
