package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a Realm record only during its initial creation.
 *
 * The realmSecret is unique to this class and is only accessible this one time,
 * it cannot be retrieved. Hence a separate class from Realm.
 *
 * The realmSecret is randomly generated in the database, but it's not stored
 * anywhere, except a salted digest of it. Once the realmSecret is communicated
 * to the user, it's out of our system.
 *
 */
@Entity
public class RealmCreation implements Serializable {

    private String realmId;
    private String realmSecret;

    public RealmCreation() {
    }

    public RealmCreation(String realmId, String realmSecret) {
        this.realmId = realmId;
        this.realmSecret = realmSecret;
    }

    @Id
    @JsonProperty
    public String getRealmId() {
        return realmId;
    }

    @Id
    @JsonProperty
    public String getRealmSecret() {
        return realmSecret;
    }

    public void setRealmSecret(String realmSecret) {
        this.realmSecret = realmSecret;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }
}
