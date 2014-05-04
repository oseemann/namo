package net.oebs.namo.resources;

import net.oebs.namo.core.Realm;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
public class RealmResource {

    @GET
    @Timed
    public Realm getRealm(@QueryParam("realm_id") Optional<String> realmId) {
        return new Realm("foobar97");
    }
}