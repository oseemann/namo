package net.oebs.namo.core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "records", catalog = "public")
public class PdnsRecord implements Serializable {

    private Integer id;
    private String name;
    private String type;
    private String content;
    private Integer domainId;
    private final Integer ttl;

    private static final Integer DEFAULT_TTL = 60;

    public PdnsRecord(String name, String type, String content, Integer domainId) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.domainId = domainId;
        ttl = DEFAULT_TTL;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }
}
