package net.oebs.namo.resources;

import net.oebs.namo.core.Realm;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
public class RealmResource {

    SessionFactory sessionFactory;
    
    public RealmResource(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @POST
    @Timed
    public Realm createRealm() {
        Session session = this.sessionFactory.openSession();
        Query q = session.createSQLQuery(
            "INSERT INTO namo.realm VALUES (default) RETURNING realm_id");
        String realmId = (String)q.list().get(0);
        session.close();
        return new Realm(realmId);
    }
}
