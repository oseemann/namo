package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain", catalog = "namo")
public class Domain implements Serializable {

    private String name;

    public Domain() {
    }

    public Domain(String name) {
        this.name = name;
    }

    @Id
    @JsonProperty
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
