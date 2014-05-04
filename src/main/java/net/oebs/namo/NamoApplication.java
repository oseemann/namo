package net.oebs.namo;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.sql.SQLException;
import net.oebs.namo.health.TemplateHealthCheck;
import net.oebs.namo.resources.RealmResource;

public class NamoApplication extends Application<NamoConfiguration> {

    private BoneCP connectionPool;

    public static void main(String[] args) throws Exception {
        new NamoApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<NamoConfiguration> bootstrap) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) { }

        BoneCPConfig config;
        config = new BoneCPConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/namo");
        config.setUsername("namo");
        config.setPassword("namo");

        try {
            this.connectionPool = new BoneCP(config);
        } catch (SQLException ex) { }
    }

    @Override
    public void run(NamoConfiguration configuration, Environment environment) {
        final RealmResource realm = new RealmResource(this.connectionPool);

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(realm);
        environment.healthChecks().register("template", healthCheck);
    }
}
