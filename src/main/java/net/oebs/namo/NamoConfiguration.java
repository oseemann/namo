package net.oebs.namo;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
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
