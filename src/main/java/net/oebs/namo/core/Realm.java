package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "realm", catalog = "namo")
public class Realm {
    
    private String realmId;

    public Realm() {
    }

    public Realm(String realmId) {
        this.realmId = realmId;
    }

    @Id
    @JsonProperty
    public String getRealmId() {
        return realmId;
    }
}