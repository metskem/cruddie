package nl.computerhok.cruddie;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repositories.AppserverRepository;
import nl.computerhok.cruddie.repositories.AppservergroupRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
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

            // fetch ags by name like
            log.info("Appservergroup found with findByNameLike('tl'):");
            log.info("--------------------------------------------");
            for (Appservergroup ag: appservergroupRepository.findByNameLike("tl%")) {
                log.info(ag.toString());
            }
            log.info("");

        };
    }

    @Bean
    public CommandLineRunner appserverDataLoader(AppserverRepository appserverRepository, AppservergroupRepository appservergroupRepository) {
        int NUM_APPSERVERS = 99;
        return (args) -> {
            // save a couple of ags
            appserverRepository.save(new Appserver("lsrv4711","-Xms500M", Appserver.Location.Best,"metskeh",new Date(),new Date(),appservergroupRepository.findByName("tl53")));
            appserverRepository.save(new Appserver("lsrv4712","-Xms500M -Djavax.net.debug=true", Appserver.Location.Boxtel,"troijenc",new Date(),new Date(),appservergroupRepository.findByName("tl53")));
            appserverRepository.save(new Appserver("lsrv2313","-Xms500M -whatever", Appserver.Location.Boxtel,"troijenc",new Date(),new Date(),appservergroupRepository.findByName("pl02")));

            log.info("saving " + NUM_APPSERVERS + " random appservers");
            for (int i=0;i<NUM_APPSERVERS;i++) {
                appserverRepository.save(new Appserver("lsrv1" + StringUtils.leftPad(String.valueOf(i),3,"0"), "jvm arg xxxx",Appserver.Location.Boxtel,"metskeh", new Date(), new Date(),appservergroupRepository.findByName("pl02")));
            }

            // fetch all appservers with a certain stage
            log.info("Appservers found with findByAppservergroupStage():");
            log.info("-------------------------------");
            for (Appserver appserver : appserverRepository.findByAppservergroupStage(Appservergroup.Stage.t)) {
                log.info(appserver.toString());
            }
            log.info("");

            // fetch an individual appserver by ID
            Appserver appserver = appserverRepository.findOne(1L);
            log.info("Appserver found with findOne(1L):");
            log.info("--------------------------------");
            log.info(appserver.toString());
            log.info("");

            // find appservers by hostname
            log.info("Appservers found with findByHostname(\"lsrv4711\")):");
            log.info("--------------------------------");
            log.info(appserverRepository.findByHostname("lsrv4711").toString());
            log.info("");
        };
    }
}
