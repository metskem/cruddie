package nl.computerhok.cruddie;

import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.entity.Customer;
import nl.computerhok.cruddie.entity.Orderr;
import nl.computerhok.cruddie.repositories.AppservergroupRepository;
import nl.computerhok.cruddie.repositories.CustomerRepository;
import nl.computerhok.cruddie.repositories.OrderrRepository;
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
    public CommandLineRunner customerDataLoader(CustomerRepository customerRepository) {
        int NUM_CUSTOMERS = 999;
        return (args) -> {
            // save a couple of customers
            customerRepository.save(new Customer("Jack", "Bauer", new Date()));
            customerRepository.save(new Customer("Chloe", "O'Brian", new Date()));
            customerRepository.save(new Customer("Kim", "Bauer", new Date()));
            customerRepository.save(new Customer("David", "Palmer", new Date()));
            customerRepository.save(new Customer("Michelle", "Dessler", new Date()));
            customerRepository.save(new Customer("aap", "noot", new Date()));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer cust : customerRepository.findAll()) {
                log.info(cust.toString());
            }

            // fetch an individual customer by ID
            Customer customer = customerRepository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : customerRepository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");

            log.info("saving " + NUM_CUSTOMERS + " random customers");
            for (int i=0;i<NUM_CUSTOMERS;i++) {
                customerRepository.save(new Customer("firstname-" + RandomStringUtils.randomAlphanumeric(10), "lastname-"+RandomStringUtils.randomAlphanumeric(20), new Date()));
            }
            log.info("");
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner orderrDataLoader(OrderrRepository orderrRepository, CustomerRepository customerRepository) {
        return (args) -> {
            // save a couple of customers
            orderrRepository.save(new Orderr("banana",5,new Date(), customerRepository.findOne(1L)));
            orderrRepository.save(new Orderr("staples",500,new Date(), customerRepository.findOne(2L)));
            orderrRepository.save(new Orderr("coffee",500,new Date(),customerRepository.findOne(3L)));
            orderrRepository.save(new Orderr("oliebollen",4711,new Date(),customerRepository.findOne(3L)));

            // fetch all orderrs
            log.info("Orders found with findAll():");
            log.info("-------------------------------");
            for (Orderr order : orderrRepository.findAll()) {
                log.info(order.toString());
            }
            log.info("");

            // fetch an individual order by ID
            Orderr order = orderrRepository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(order.toString());
            log.info("");

            // fetch orders by article
            log.info("Orderr found with findByArticle('banana'):");
            log.info("--------------------------------------------");
            for (Orderr banana: orderrRepository.findByArticle("banana")) {
                log.info(banana.toString());
            }
            log.info("");
        };
    }


    @Bean
    public CommandLineRunner appservergroupDataLoader(AppservergroupRepository appservergroupRepository) {
        return (args) -> {
            // save a couple of ags
            appservergroupRepository.save(new Appservergroup("tl53", Appservergroup.Stage.t));
            appservergroupRepository.save(new Appservergroup("tl99", Appservergroup.Stage.t));
            appservergroupRepository.save(new Appservergroup("al14", Appservergroup.Stage.a));
            appservergroupRepository.save(new Appservergroup("pl02", Appservergroup.Stage.p));

            // fetch all ags
            log.info("Appservergroups found with findAll():");
            log.info("-------------------------------");
            for (Appservergroup appservergroup : appservergroupRepository.findAll()) {
                log.info(appservergroup.toString());
            }
            log.info("");

            // fetch an individual ag by ID
            Appservergroup appservergroup = appservergroupRepository.findOne(1L);
            log.info("Appservergroup found with findOne(1L):");
            log.info("--------------------------------");
            log.info(appservergroup.toString());
            log.info("");

            // fetch ags by stage
            log.info("Appservergroup found with findByStage('t'):");
            log.info("--------------------------------------------");
            for (Appservergroup ag: appservergroupRepository.findByStage(Appservergroup.Stage.t)) {
                log.info(ag.toString());
            }
            log.info("");
        };
    }
}
