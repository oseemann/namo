package net.oebs.namo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.oebs.namo.health.TemplateHealthCheck;
import net.oebs.namo.resources.RealmResource;

public class NamoApplication extends Application<NamoConfiguration> {
    public static void main(String[] args) throws Exception {
        new NamoApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<NamoConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(NamoConfiguration configuration, Environment environment) {
        final RealmResource realm = new RealmResource();
        
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
                
        environment.jersey().register(realm);
        environment.healthChecks().register("template", healthCheck);
    }
}