package net.oebs.namo.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import net.oebs.namo.core.PdnsDomain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Path("/domain")
@Produces(MediaType.APPLICATION_JSON)
public class PdnsDomainResource {

    SessionFactory sessionFactory;
    
    public PdnsDomainResource(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @GET
    @Timed
    public PdnsDomain getPdnsDomain(@QueryParam("domain_id") Optional<String> domainId) {
        Session session = this.sessionFactory.openSession();
        PdnsDomain x = (PdnsDomain)session.get(PdnsDomain.class, 1);
        session.close();
        return x;
    }
}
