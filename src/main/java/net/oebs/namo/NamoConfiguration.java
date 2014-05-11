package net.oebs.namo;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class NamoConfiguration extends Configuration {

    @NotEmpty
    private String dbPass;

    @NotEmpty
    private String dbHost;

    @NotEmpty
    private String dbName;

    @NotEmpty
    private String dbUser;

    @NotEmpty
    private String dbPort = "5432";

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty
    public String getDbName() {
        return this.dbName;
    }

    @JsonProperty
    public void setDbName(String name) {
        this.dbName = name;
    }

    @JsonProperty
    public String getDbHost() {
        return this.dbHost;
    }

    @JsonProperty
    public void setDbHost(String host) {
        this.dbHost = host;
    }

    @JsonProperty
    public String getDbPort() {
        return this.dbPort;
    }

    @JsonProperty
    public void setDbPort(String port) {
        this.dbPort = port;
    }

    @JsonProperty
    public String getDbUser() {
        return this.dbUser;
    }

    @JsonProperty
    public void setDbUser(String user) {
        this.dbUser = user;
    }

    @JsonProperty
    public String getDbPass() {
        return this.dbPass;
    }

    @JsonProperty
    public void setDbPass(String passwd) {
        this.dbPass = passwd;
    }
}
