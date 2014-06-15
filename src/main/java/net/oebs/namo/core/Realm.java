package net.oebs.namo.core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "X1",
            query = "SELECT realmId, realmSecret, NULL AS validAuth "
            + " FROM namo.create_realm()",
            resultClass = Realm.class),
    @NamedNativeQuery(name = "X2",
            query = "SELECT realm_id as realmId, NULL AS realmSecret, "
            + "secret_crypt = crypt(:secret, secret_crypt) AS validAuth "
            + "FROM namo.realm WHERE realm_id = :id",
            resultClass = Realm.class)
})
@Table(name = "realm", catalog = "namo")
public class Realm implements Serializable {

    @Column(name = "realm_id")
    private String realmId;

    private String realmSecret;
    private Boolean validAuth;

    public Realm() {
    }

    public Realm(String realmId, String realmSecret) {
        this.realmId = realmId;
        this.realmSecret = realmSecret;
    }

    public Realm(String realmId) {
        this.realmId = realmId;
    }

    @Id
    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getRealmSecret() {
        return realmSecret;
    }

    public void setRealmSecret(String realmSecret) {
        this.realmSecret = realmSecret;
    }

    public Boolean getValidAuth() {
        return validAuth;
    }

    public void setValidAuth(Boolean validAuth) {
        this.validAuth = validAuth;
    }

    public int hashCode() {
        return new HashCodeBuilder(7, 101).
                append(realmId).
                append(realmSecret).
                append(validAuth).
                toHashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Realm)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Realm rhs = (Realm) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                append(realmId, rhs.realmId).
                append(realmSecret, rhs.realmSecret).
                append(validAuth, rhs.validAuth).
                isEquals();
    }
}
