package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Realm {
    
    @Length(max = 3)
    private String id;

    public Realm() {
        // Jackson deserialization
    }

    public Realm(String realmId) {
        this.id = realmId;
    }

    @JsonProperty
    public String getRealmId() {
        return id;
    }
}