package net.oebs.namo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.oebs.namo.health.TemplateHealthCheck;
import net.oebs.namo.resources.PdnsDomainResource;
import net.oebs.namo.resources.RealmResource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    }

    private Configuration getHibernateConfiguration(NamoConfiguration config) {
        Configuration dbConfig = new Configuration();
        String dbUrl = String.format("jdbc:postgresql://%s:%s/%s",
                config.getDbHost(), config.getDbPort(), config.getDbName());
        dbConfig.setProperty("hibernate.connection.url", dbUrl);
        dbConfig.setProperty("hibernate.connection.username", config.getDbUser());
        dbConfig.setProperty("hibernate.connection.password", config.getDbPass());
        dbConfig.configure();
        return dbConfig;
    }

    @Override
    public void run(NamoConfiguration configuration, Environment environment) {

        Configuration dbConfig = getHibernateConfiguration(configuration);
        SessionFactory dbSessionFactory = dbConfig.buildSessionFactory();

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck();

        environment.jersey().register(new RealmResource(dbSessionFactory));
        environment.jersey().register(new PdnsDomainResource(dbSessionFactory));
        environment.healthChecks().register("template", healthCheck);
    }
}
