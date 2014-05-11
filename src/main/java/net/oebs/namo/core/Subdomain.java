package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subdomain", catalog = "namo")
public class Subdomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subdomain_id;

    private String name;

    private String domain;

    @Column(name = "realm_id")
    private String realmId;

    public Subdomain() {
    }

    @JsonProperty
    public Integer getSubdomainId() {
        return subdomain_id;
    }

    public void setSubdomainId(Integer id) {
        this.subdomain_id = id;
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getDomain() {
        return this.name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty
    public String getRealmId() {
        return this.realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }
}
