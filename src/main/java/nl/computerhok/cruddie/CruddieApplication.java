package nl.computerhok.cruddie;

import nl.computerhok.cruddie.entity.Appserver;
import nl.computerhok.cruddie.entity.Appservergroup;
import nl.computerhok.cruddie.repository.AppserverRepository;
import nl.computerhok.cruddie.repository.AppservergroupRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

@SpringBootApplication
public class CruddieApplication extends WebMvcConfigurerAdapter{
    public static final int NUM_APPSERVERS = 5000;
    private static final Logger log = LoggerFactory.getLogger(CruddieApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CruddieApplication.class, args);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //
        // for easy developing/testing static content , without having to springboot:run every time
        // REMOVE When creating a final runnable jar of course !!!
        //
        registry.addResourceHandler("/html/**").addResourceLocations("file:///home/metskem/workspace/cruddie/src/main/resources/static/html/").setCachePeriod(0);
        registry.addResourceHandler("/css/**").addResourceLocations("file:///home/metskem/workspace/cruddie/src/main/resources/static/css/").setCachePeriod(0);
        registry.addResourceHandler("/js/**").addResourceLocations("file:///home/metskem/workspace/cruddie/src/main/resources/static/js/").setCachePeriod(0);
    }

    @Bean
    public CommandLineRunner dataLoader(AppserverRepository appserverRepository, AppservergroupRepository appservergroupRepository) {
        return (args) -> {
//
//            // during tests, data is loaded by spring itself, see src/test/resources/data.sql
//
//            appservergroupRepository.save(new Appservergroup("random", Appservergroup.Stage.t,"metskeh"));
//
//            // fetch all ags
//            log.info("Appservergroups found with findAll():");
//            log.info("-------------------------------");
//            for (Appservergroup appservergroup : appservergroupRepository.findAll()) {
//                log.info(appservergroup.toString());
//            }
//            log.info("");
//
//            // fetch ags by stage
//            log.info("Appservergroup found with findByStage('t'):");
//            log.info("--------------------------------------------");
//            for (Appservergroup ag: appservergroupRepository.findByStage(Appservergroup.Stage.t)) {
//                log.info(ag.toString());
//            }
//            log.info("");
//
//            // fetch ags by name like
//            log.info("Appservergroup found with findByNameLike('tl'):");
//            log.info("--------------------------------------------");
//            int numFound=0;
//            for (Appservergroup ag: appservergroupRepository.findByNameLike("tl%")) {
//                numFound++;
//            }
//            log.info("found " + numFound + " servers");
//            log.info("");
//
//            // data is loaded by spring itself, see src/test/resources/data.sql
//
            log.info("saving " + NUM_APPSERVERS + " random appservers");
            for (int i=0;i<NUM_APPSERVERS;i++) {
                if (appserverRepository.save(new Appserver("lsrv1" + StringUtils.leftPad(String.valueOf(i),3,"0"), "jvm arg xxxx yyyyyy zzzzz aaaaaaaaa bbbbbbbbbbbbb nnnnnnnnnnnnnnnnnnnnnnn 1 2 3 4 5 6 7 8 90 llll",Appserver.Location.Boxtel,appservergroupRepository.findByName("pl02"),"metskeh")) == null) {
                    log.error("save failed for appserver");
                    return;
                }
            }
//
//            // fetch all appservers with a certain stage
//            log.info("Appservers found with findByAppservergroupStage(t):");
//            log.info("-------------------------------");
//            numFound=0;
//            for (Appserver appserver : appserverRepository.findByAppservergroupStage(Appservergroup.Stage.t)) {
//                numFound++;
//            }
//            log.info("found " + numFound + " servers");
//            log.info("");
//
//            // fetch an individual appserver by ID
//            Appserver appserver = appserverRepository.findOne(1L);
//            log.info("Appserver found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(appserver.toString());
//            log.info("");
//
//            // find appservers by hostname
//            log.info("Appservers found with findByHostname(\"lsrv1000\")):");
//            log.info("--------------------------------");
//            log.info(appserverRepository.findByHostname("lsrv1000").toString());
//            log.info("");
        };
    }
}
