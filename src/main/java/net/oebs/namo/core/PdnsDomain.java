package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "domains", catalog = "public")
public class PdnsDomain {

    private Integer id;
    private String name;

    public PdnsDomain() {
    }
    
    public PdnsDomain(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @JsonProperty
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
