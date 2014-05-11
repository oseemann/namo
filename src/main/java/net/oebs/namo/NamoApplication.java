package net.oebs.namo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import net.oebs.namo.core.Domain;
import net.oebs.namo.core.DomainDAO;
import net.oebs.namo.core.Realm;
import net.oebs.namo.core.RealmDAO;
import net.oebs.namo.core.Subdomain;
import net.oebs.namo.core.SubdomainDAO;
import net.oebs.namo.health.TemplateHealthCheck;
import net.oebs.namo.resources.DomainResource;
import net.oebs.namo.resources.RealmResource;
import net.oebs.namo.resources.SubdomainResource;
import org.hibernate.SessionFactory;

public class NamoApplication extends Application<NamoConfiguration> {

    public static void main(String[] args) throws Exception {
        new NamoApplication().run(args);
    }

    @Override
    public String getName() {
        return "namo";
    }

    @Override
    public void initialize(Bootstrap<NamoConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    private final HibernateBundle<NamoConfiguration> hibernate = new HibernateBundle<NamoConfiguration>(
            Realm.class, Domain.class, Subdomain.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(NamoConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public void run(NamoConfiguration configuration, Environment environment) {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck();

        SessionFactory sf = hibernate.getSessionFactory();
        JerseyEnvironment jersey = environment.jersey();
        RealmDAO realmDAO = new RealmDAO(sf);

        jersey.register(new BasicAuthProvider<Realm>(new NamoAuthenticator(realmDAO), "namo"));
        jersey.register(new RealmResource(realmDAO));
        jersey.register(new DomainResource(new DomainDAO(sf)));
        jersey.register(new SubdomainResource(new SubdomainDAO(sf)));
        environment.healthChecks().register("template", healthCheck);
    }
}
