package net.oebs.namo.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import net.oebs.namo.core.DomainDAO;

@Path("/domain")
@Produces(MediaType.APPLICATION_JSON)
public class DomainResource {

    DomainDAO dao;

    public DomainResource(DomainDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List getDomain(@QueryParam("domain_id") Optional<String> domainId) {
        return dao.getAll();
    }
}
