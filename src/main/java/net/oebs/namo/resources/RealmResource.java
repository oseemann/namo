package net.oebs.namo.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oebs.namo.core.Realm;
import net.oebs.namo.core.RealmDAO;

@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
public class RealmResource {

    RealmDAO dao;

    public RealmResource(RealmDAO dao) {
        this.dao = dao;
    }

    @POST
    @Timed
    @UnitOfWork
    public Realm createRealm() {
        Realm r = this.dao.create();
        return r;
    }
}
