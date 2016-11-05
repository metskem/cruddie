package nl.computerhok.cruddie;

import nl.computerhok.cruddie.controller.AppserverController;
import nl.computerhok.cruddie.controller.AppservergroupController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(AppservergroupController.class);
        register(AppserverController.class);
    }
}