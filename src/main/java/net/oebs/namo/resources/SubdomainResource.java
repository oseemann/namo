package net.oebs.namo.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import net.oebs.namo.BackendSyncInterface;
import net.oebs.namo.core.Realm;
import net.oebs.namo.core.Subdomain;
import net.oebs.namo.core.SubdomainDAO;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/subdomain")
@Produces(MediaType.APPLICATION_JSON)
public class SubdomainResource {

    static final Logger log = LoggerFactory.getLogger(SubdomainResource.class);
    SessionFactory sessionFactory;
    SubdomainDAO dao;
    BackendSyncInterface backend;

    public SubdomainResource(SubdomainDAO dao, BackendSyncInterface backend) {
        this.dao = dao;
        this.backend = backend;
    }

    @GET
    @Timed
    public Subdomain getSubdomain(@QueryParam("subdomain_id") Optional<String> subdomainId) {
        return null;
    }

    @POST
    @Timed
    @UnitOfWork
    public Integer claimSubdomain(@Auth Realm realm, String x) {
        log.info("XX: {}", x);
        log.info("Realm: {}", realm.getRealmId());
        Subdomain sub = dao.claim(realm, x);
        backend.wakeup(sub);
        return sub.getSubdomainId();
    }
}
